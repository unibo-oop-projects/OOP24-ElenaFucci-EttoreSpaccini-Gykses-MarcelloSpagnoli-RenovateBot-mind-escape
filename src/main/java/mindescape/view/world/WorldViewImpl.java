package mindescape.view.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileLayer;

import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.rooms.api.Room;
import mindescape.view.api.View;

public class WorldViewImpl extends JPanel implements View, KeyListener {

    private final WorldController worldController;
    private Room currentRoom;

    public WorldViewImpl() {
        currentRoom = null;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    
}