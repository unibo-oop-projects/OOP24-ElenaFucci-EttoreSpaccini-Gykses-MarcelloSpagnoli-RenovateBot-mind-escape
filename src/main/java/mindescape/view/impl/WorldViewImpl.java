package mindescape.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
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
import org.tiledreader.TiledTileset;

import mindescape.controller.api.Controller;
import mindescape.controller.api.UserInput;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.world.core.api.Dimensions;
import mindescape.view.api.View;

public class WorldViewImpl extends JPanel implements View {

    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT,
        KeyEvent.VK_E, UserInput.INTERACT,
        KeyEvent.VK_I, UserInput.INVENTORY
    );

    private final WorldController controller;

    public WorldViewImpl(WorldController controller, Map<Integer, BufferedImage> tileset) {
        this.controller = controller;
        this.setPreferredSize(getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        TiledMap map = new FileSystemTiledReader().getMap("string");
        List<TiledTileLayer> tileLayers = getTileLayers(map);
        for (TiledTileLayer layer : tileLayers) {
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    TiledTile tile = layer.getTile(x, y);
                    if (tile != null) {
                        int tileDim = (int) Dimensions.TILE.height();
                        g.drawImage(getTileImage(tile), x * tileDim, y* tileDim, this);
                    }
                }
            }
        }
    }

    private BufferedImage getTileImage(TiledTile tile) {
        try {
            TiledTileset tileset = tile.getTileset();
            BufferedImage tilesetImage = ImageIO.read(new File(tileset.getImage().getSource()));
            Point2D pos = getTilePosition(tile, tileset.getHeight(), tileset.getWidth());
            BufferedImage tileImage = tilesetImage.getSubimage(
                (int) pos.getX(), (int) pos.getX(),
                (int) Dimensions.TILE.height(), (int) Dimensions.TILE.width());
            return tileImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } 
    }

    private Point2D getTilePosition(TiledTile tile, int TilesetHeight, int TileSetWidth) {
        return new Point2D.Double(tile.getID() % TileSetWidth, tile.getID() / TilesetHeight);
    }
    
    private List<TiledTileLayer> getTileLayers(TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledTileLayer)
            .map(layer -> (TiledTileLayer) layer)
            .toList();
    }


    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}
