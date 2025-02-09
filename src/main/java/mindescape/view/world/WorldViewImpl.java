package mindescape.view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import mindescape.view.api.WorldView;

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
    private final WorldController worldController;
    private final Map<TiledTile, BufferedImage> tilesCache = new HashMap<>();
    private Room currentRoom;
    private BufferedImage roomImage;

    public WorldViewImpl(WorldController worldController) {
        this.worldController = worldController;
        currentRoom = RoomImpl.createRooms().stream().filter(x -> x.getName().equals("bedroom")).findAny().get();
        updateRoomImage();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void draw(Room currentRoom) {
        if (this.currentRoom != currentRoom) {
            this.currentRoom = currentRoom;
            updateRoomImage();
            repaint();
        }
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = adapt(roomImage);
        g.drawImage(image, (this.getWidth() - image.getWidth()) / 2, 0, this);
    }

    private void drawPlayer(Graphics g) {
        try {
            GameObject p = currentRoom.getGameObjects().stream().filter(x -> x instanceof Player).findAny().get();
            g.drawRect(
                (int) p.getPosition().get().x(), 
                (int) p.getPosition().get().y(), 
                (int) p.getDimensions().width(), 
                (int) p.getDimensions().height());
        } catch (Exception e) {
            throw new IllegalStateException("The room which is being displayed does not contains the player");
        }
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

    private BufferedImage getTileImage (TiledTile tile) {
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

    private BufferedImage applyTransformations(BufferedImage img, boolean horizontal, boolean diagonal) {
        if (diagonal) {
            img = rotateImage(img, ROTATING_ANGLE);
        }
        if (horizontal) {
            img = flipImageHorizontally(img);
        }
        return img;
    }

    private BufferedImage adapt(BufferedImage image) {
        double scaling = getScalingFactor();
        int newWidth = (int) (image.getWidth() * scaling);
        int newHeight = (int) (image.getHeight() * scaling);
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, image.getType());
    
        Graphics2D g2d = scaledImage.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(scaling, scaling);
        g2d.drawImage(image, at, null);
        g2d.dispose();
        return scaledImage;
    }
    
    private BufferedImage rotateImage(BufferedImage image, double angle) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Creazione di una trasformazione
        AffineTransform transform = new AffineTransform();

        // Imposta il punto di rotazione al centro dell'immagine
        transform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);

        // Creazione di un nuovo BufferedImage per contenere l'immagine ruotata
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Ottenere il contesto grafico della nuova immagine
        Graphics2D g2d = rotatedImage.createGraphics();

        // Applicare la trasformazione e disegnare l'immagine originale
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);

        // Liberare le risorse
        g2d.dispose();

        return rotatedImage;
    }

    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
    
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1); // Specchia orizzontalmente
        transform.translate(-width, 0); // Riporta l'immagine nella posizione giusta
    
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
    
        return flippedImage;
    }

    private Point2D getPositionFromId(TiledTile tile, int mapWidth) {
        return new Point2D(tile.getID() % mapWidth, tile.getID() / mapWidth);
    }

    private double getScalingFactor() {
        double tileScaledDim = this.getHeight() / (double) (currentRoom.getDimensions().height() / Dimensions.TILE.height());
        return tileScaledDim / Dimensions.TILE.height();
    }

    private void updateRoomImage() {
        roomImage = new BufferedImage((int)currentRoom.getDimensions().height(), (int)currentRoom.getDimensions().height(),
            BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D finalMap = roomImage.createGraphics();
        TiledMap map = new FileSystemTiledReader().getMap(currentRoom.getSource());
        getTileLayers(map).forEach(layer -> drawLayer(layer, finalMap, map));
        finalMap.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        worldController.handleInput(KEY_MAPPER.get(pressed));
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }   
}