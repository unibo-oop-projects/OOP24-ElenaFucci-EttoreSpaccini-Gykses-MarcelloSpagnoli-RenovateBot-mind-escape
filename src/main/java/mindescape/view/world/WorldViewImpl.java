package mindescape.view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObject;
import org.tiledreader.TiledObjectLayer;
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileLayer;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.view.api.WorldView;
import mindescape.view.utils.ImageTransformer;

/**
 * Implementation of the WorldView.
 */
public class WorldViewImpl extends JPanel implements WorldView, KeyListener {

    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT,
        KeyEvent.VK_E, UserInput.INTERACT,
        KeyEvent.VK_I, UserInput.INVENTORY
    );
    private static final double ROTATING_ANGLE = -90;
    private transient final Map<TiledTile, BufferedImage> tilesCache = new HashMap<>();
    private transient BufferedImage roomImage;
    private double scaling = 1;
    private transient String roomName;
    private transient final PlayerView player;
    private double roomHeight;
    private int objNum;
    private final Map<Integer, Boolean> keyState = new HashMap<>();
    private transient final ImageTransformer transformer = new ImageTransformer();

    /**
     * Constructor for WorldViewImpl.
     *
     * @param worldController the world controller
     * @param currentRoom the current room
     */
    public WorldViewImpl(final WorldController worldController, final Room currentRoom) {
        roomHeight = currentRoom.getDimensions().height();
        roomName = currentRoom.getName();
        updateRoomImage(currentRoom);
        player = new PlayerView(getPlayer(currentRoom).getPosition());
        KEY_MAPPER.forEach((key, value) -> keyState.put(key, false));
        objNum = currentRoom.getGameObjects().size();
        this.setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }

    @Override
    public void draw(final Room currentRoom) {
        if (!roomName.equals(currentRoom.getName()) || objNum != currentRoom.getGameObjects().size()) {
            objNum = currentRoom.getGameObjects().size();
            updateRoomImage(currentRoom);
            roomHeight = currentRoom.getDimensions().height();
            roomName = currentRoom.getName();
        }
        player.setPosition(getPlayer(currentRoom).getPosition());
        repaint();
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        scaling = getScalingFactor();
        final BufferedImage image = transformer.adapt(roomImage, scaling);
        final int offset = (this.getWidth() - image.getWidth()) / 2;
        g.drawImage(image, offset, 0, this);
        player.draw(g, offset, scaling, keyState);
    }

    private void drawLayer(final TiledTileLayer layer, final Graphics g, final TiledMap map) {
        final int dim = (int) Dimensions.TILE.height();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                final TiledTile tile = layer.getTile(x, y);
                if (tile != null) {
                    BufferedImage img = tilesCache.get(tile);
                    if (img == null) {
                        img = getTileImage(tile);
                        img = applyTransformations(img,
                            layer.getTileHorizontalFlip(x, y),
                            layer.getTileDiagonalFlip(x, y)
                        );
                        tilesCache.put(tile, img);
                    }
                    g.drawImage(img, x * dim, y * dim, this);
                }
            }
        }
    }

    private List<TiledTileLayer> getTileLayers(final TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledTileLayer)
            .map(layer -> (TiledTileLayer) layer)
            .toList();
    }

    private BufferedImage getTileImage(final TiledTile tile) {
        try {
            final BufferedImage image = ImageIO.read(new File(tile.getTileset().getImage().getSource()));
            final Point2D pos = getPositionFromId(tile, tile.getTileset().getWidth());
            return image.getSubimage(
                (int) pos.x() * (int) Dimensions.TILE.height(),
                (int) pos.y() * (int) Dimensions.TILE.height(),
                (int) Dimensions.TILE.height(),
                (int) Dimensions.TILE.width()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage applyTransformations(BufferedImage img, final boolean horizontal, final boolean diagonal) {
        if (diagonal) {
            img = transformer.rotateImage(img, ROTATING_ANGLE);
        }
        if (horizontal) {
            img = transformer.flipImageHorizontally(img);
        }
        return img;
    }

    private Point2D getPositionFromId(final TiledTile tile, final int mapWidth) {
        return new Point2D(tile.getID() % mapWidth, tile.getID() / mapWidth);
    }

    private double getScalingFactor() {
        final double tileScaledDim = this.getHeight() / (roomHeight / Dimensions.TILE.height());
        return tileScaledDim / Dimensions.TILE.height();
    }

    private void updateRoomImage(final Room currentRoom) {
        roomImage = new BufferedImage((int) currentRoom.getDimensions().height(),
            (int) currentRoom.getDimensions().height(), BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics2D finalMap = roomImage.createGraphics();
        final TiledMap map = new FileSystemTiledReader().getMap(currentRoom.getSource());
        getTileLayers(map).forEach(layer -> drawLayer(layer, finalMap, map));
        List<TiledObject> tileObjects = getTileObjects(map);
        tileObjects = tileObjects
            .stream()   
            .filter(tObj -> {
                return currentRoom.getGameObjects()
                    .stream()
                    .anyMatch(obj -> obj.getName().equals(tObj.getName()));
            })
            .toList();
        tileObjects.forEach(obj -> drawTileObject(obj, finalMap));
        finalMap.dispose();
    }

    private void drawTileObject(final TiledObject obj, final Graphics2D g) {
        final TiledTile tile = obj.getTile();
        BufferedImage img = getTileImage(tile);
        img = applyTransformations(img,
            obj.getTileXFlip(),
            obj.getTileDFlip());
        g.drawImage(img, (int) obj.getX(), (int) obj.getY(), null);
    }

    private Player getPlayer(final Room currentRoom) {
        return (Player) currentRoom.getGameObjects().stream().filter(x -> x instanceof Player).findAny().get();
    }

    private List<TiledObject> getTileObjects(final TiledMap map) {
        final TiledObjectLayer objects =  map.getNonGroupLayers().stream()
            .filter(layer -> layer.getName().equals("Objects"))
            .map(layer -> (TiledObjectLayer) layer)
            .findFirst()
            .get();
        return objects.getObjects().stream().filter(obj -> obj.getTile() != null).toList();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int pressed = e.getKeyCode();
        if (KEY_MAPPER.containsKey(pressed)) {
            keyState.put(pressed, true);
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        final int released = e.getKeyCode();
        if (KEY_MAPPER.containsKey(released)) {
            keyState.put(released, false);
        }
    }

    @Override
    public Map<Integer, Boolean> getKeyState() {
        return Collections.unmodifiableMap(keyState);
    }

    @Override
    public void clearInput() {
        keyState.clear();
    }
}
