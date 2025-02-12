package mindescape.view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
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
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileLayer;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.view.api.WorldView;

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
    private final Map<TiledTile, BufferedImage> tilesCache = new HashMap<>();
    private BufferedImage roomImage;
    private double scaling = 1;
    private String roomName;
    private PlayerView player;
    private double roomHeight;
    private final Map<Integer, Boolean> keyState = new HashMap<>();

    /**
     * Constructor for WorldViewImpl.
     *
     * @param worldController the world controller
     * @param currentRoom the current room
     */
    public WorldViewImpl(WorldController worldController, Room currentRoom) {
        roomHeight = currentRoom.getDimensions().height();
        roomName = currentRoom.getName();
        updateRoomImage(currentRoom);
        player = new PlayerView(getPlayer(currentRoom).getPosition());
        KEY_MAPPER.forEach((key, value) -> keyState.put(key, false));
        setFocusable(true); // Allows JPanel to receive keyboard input
        requestFocusInWindow(); // Requests focus for JPanel
        addKeyListener(this);
    }

    @Override
    public void draw(Room currentRoom) {
        if (!roomName.equals(currentRoom.getName())) {
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        scaling = getScalingFactor();
        BufferedImage image = adapt(roomImage);
        int offset = (this.getWidth() - image.getWidth()) / 2;
        g.drawImage(image, offset, 0, this);
        player.draw(g, offset, scaling, keyState);
    }

    private void drawLayer(TiledTileLayer layer, Graphics g, TiledMap map) {
        int dim = (int) Dimensions.TILE.height();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                TiledTile tile = layer.getTile(x, y);
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

    private List<TiledTileLayer> getTileLayers(TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledTileLayer)
            .map(layer -> (TiledTileLayer) layer)
            .toList();
    }

    private BufferedImage getTileImage(final TiledTile tile) {
        try {
            BufferedImage image = ImageIO.read(new File(tile.getTileset().getImage().getSource()));
            Point2D pos = getPositionFromId(tile, tile.getTileset().getWidth());
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
            img = rotateImage(img, ROTATING_ANGLE);
        }
        if (horizontal) {
            img = flipImageHorizontally(img);
        }
        return img;
    }

    private BufferedImage adapt(final BufferedImage image) {
        int newWidth = (int) (image.getWidth() * scaling);
        int newHeight = (int) (image.getHeight() * scaling);
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(scaling, scaling);
        g2d.drawImage(image, at, null);
        g2d.dispose();
        return scaledImage;
    }

    private BufferedImage rotateImage(final BufferedImage image, final double angle) {
        int width = image.getWidth();
        int height = image.getHeight();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }

    private BufferedImage flipImageHorizontally(final BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1); // Flip horizontally
        transform.translate(-width, 0); // Move image back to the correct position
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return flippedImage;
    }

    private Point2D getPositionFromId(final TiledTile tile, final int mapWidth) {
        return new Point2D(tile.getID() % mapWidth, tile.getID() / mapWidth);
    }

    private double getScalingFactor() {
        double tileScaledDim = this.getHeight() / (roomHeight / Dimensions.TILE.height());
        return tileScaledDim / Dimensions.TILE.height();
    }

    private void updateRoomImage(final Room currentRoom) {
        roomImage = new BufferedImage((int) currentRoom.getDimensions().height(),
            (int) currentRoom.getDimensions().height(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D finalMap = roomImage.createGraphics();
        TiledMap map = new FileSystemTiledReader().getMap(currentRoom.getSource());
        getTileLayers(map).forEach(layer -> drawLayer(layer, finalMap, map));
        finalMap.dispose();
    }

    private Player getPlayer(final Room currentRoom) {
        return (Player) currentRoom.getGameObjects().stream()
            .filter(x -> x instanceof Player)
            .findAny()
            .get();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        int pressed = e.getKeyCode();
        if (KEY_MAPPER.containsKey(pressed)) {
            keyState.put(pressed, true);
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        int released = e.getKeyCode();
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