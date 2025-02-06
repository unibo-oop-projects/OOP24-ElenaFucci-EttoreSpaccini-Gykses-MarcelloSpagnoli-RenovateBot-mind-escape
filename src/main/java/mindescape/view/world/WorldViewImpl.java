package mindescape.view.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
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

    private final WorldController worldController;
    private Room currentRoom;

    public WorldViewImpl(WorldController worldController) {
        this.worldController = worldController;
        currentRoom = null;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void draw(Room currentRoom) {
        this.currentRoom = currentRoom;
        repaint();
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TiledMap map = new FileSystemTiledReader().getMap(currentRoom.getSource());
        getTileLayers(map).forEach(layer -> drawLayer(layer, g, map));
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
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                var tile = layer.getTile(x, y);
                if (tile != null) {
                    int dim = (int) Dimensions.TILE.height();
                    g.drawImage(getTileImage(tile), x * dim, y * dim, this);
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
            Point2D pos = getPositionFromId(tile, tile.getTileset().getTileWidth(), tile.getTileset().getTileHeight());
            return image.getSubimage(
                (int) pos.getX(),
                (int) pos.getY(),
                (int) Dimensions.TILE.height(),
                (int) Dimensions.TILE.width()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Point2D getPositionFromId(TiledTile tile, int mapWidth, int mapHeight) {
        return new Point2D.Double(tile.getID() % mapHeight, tile.getID() / mapHeight);
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