package mindescape.view.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
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
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.core.api.KeyMapper;
import mindescape.controller.core.api.UserInput;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.view.api.WorldView;
import mindescape.view.utils.ImageTransformer;
import mindescape.view.utils.ViewUtils;

/**
 * Implementation of the WorldView.
 */
public final class WorldViewImpl implements WorldView, KeyListener {

    private static final double ROTATING_ANGLE = -90;
    private static final int TILE_DIMENSION = (int) Dimensions.TILE.width();
    private final transient Map<TiledTile, BufferedImage> tilesCache = new HashMap<>();
    private BufferedImage roomImage;
    private String roomName;
    private final transient PlayerView player;
    private double roomHeight;
    private int objNum;
    private final Map<Integer, Boolean> keyState = new HashMap<>();
    private final transient ImageTransformer transformer = new ImageTransformer();
    private final Map<Integer, UserInput> keyMapper = KeyMapper.getKeyMap();
    private final JPanel panel;

    /**
     * Constructor for WorldViewImpl.
     *
     * @param currentRoom the current room
     */
    public WorldViewImpl(final Room currentRoom) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final double scaling = getScalingFactor();
                final BufferedImage image = transformer.adapt(roomImage, scaling);
                final int offset = (this.getWidth() - image.getWidth()) / 2;
                g.drawImage(image, offset, 0, this);
                player.draw(g, offset, scaling, keyState);
            }
        };
        panel.setBackground(ViewUtils.Style.PANEL_COLOR);
        this.roomHeight = currentRoom.getDimensions().height();
        this.roomName = currentRoom.getName();
        updateRoomImage(currentRoom);
        player = new PlayerView(getPlayer(currentRoom).getPosition());
        keyMapper.forEach((key, value) -> keyState.put(key, false));
        objNum = currentRoom.getGameObjects().size();
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
        this.panel.addKeyListener(this);
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
        this.panel.repaint();
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
        justification = "it is not possible to create a copy of this, however nothing is changed by caller")
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    private void drawLayer(final TiledTileLayer layer, final Graphics g, final TiledMap map) {
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
                    g.drawImage(img, x * TILE_DIMENSION, y * TILE_DIMENSION, this.panel);
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

    @SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE",
        justification = "sb gives it on line 142 although is safe")
    private BufferedImage getTileImage(final TiledTile tile) {
        try {
            if (tile.getTileset().getImage() == null) {
                return getFallbackImage();
            }
            final String path = tile.getTileset().getImage().getSource();
            final Path filePath = Path.of(path);
            if (filePath == null || filePath.getFileName() == null) {
                return getFallbackImage();
            }
            final String fileName = filePath.getFileName().toString();
            final InputStream is = WorldViewImpl.class.getClassLoader().getResourceAsStream("tiles/" + fileName);
            if (is == null) {
                return getFallbackImage();
            }
            final BufferedImage image = ImageIO.read(is);
            final Point2D pos = getPositionFromId(tile, tile.getTileset().getWidth());
            return image.getSubimage(
                (int) pos.x() * TILE_DIMENSION,
                (int) pos.y() * TILE_DIMENSION,
                TILE_DIMENSION,
                TILE_DIMENSION
            );
        } catch (IOException | InvalidPathException e) {
            return getFallbackImage();
        }
    }

    private BufferedImage applyTransformations(final BufferedImage img, final boolean horizontal, final boolean diagonal) {
        BufferedImage result = img;
        if (diagonal) {
            result = transformer.rotateImage(result, ROTATING_ANGLE);
        }
        if (horizontal) {
            result = transformer.flipImageHorizontally(result);
        }
        return result;
    }

    private Point2D getPositionFromId(final TiledTile tile, final int mapWidth) {
        return new Point2D(tile.getID() % mapWidth, (double) tile.getID() / mapWidth);
    }

    private double getScalingFactor() {
        final double tileScaledDim = this.panel.getHeight() / (roomHeight / TILE_DIMENSION);
        return tileScaledDim / TILE_DIMENSION;
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
            .filter(layer -> "Objects".equals(layer.getName()))
            .map(layer -> (TiledObjectLayer) layer)
            .findFirst()
            .get();
        return objects.getObjects().stream().filter(obj -> obj.getTile() != null).toList();
    }

    private BufferedImage getFallbackImage() {
        final BufferedImage image = new BufferedImage(
                TILE_DIMENSION,
                TILE_DIMENSION,
                BufferedImage.TYPE_4BYTE_ABGR
            );
        final Graphics g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, TILE_DIMENSION, TILE_DIMENSION);
        g.dispose();
        return image;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int pressed = e.getKeyCode();
        if (keyMapper.containsKey(pressed)) {
            keyState.put(pressed, true);
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        final int released = e.getKeyCode();
        if (keyMapper.containsKey(released)) {
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
