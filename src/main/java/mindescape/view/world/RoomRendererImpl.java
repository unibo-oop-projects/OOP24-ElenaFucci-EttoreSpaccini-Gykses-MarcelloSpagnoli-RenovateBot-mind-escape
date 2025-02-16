package mindescape.view.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.tiledreader.TiledMap;
import org.tiledreader.TiledObject;
import org.tiledreader.TiledObjectLayer;
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileLayer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.MapReader;
import mindescape.view.api.RoomRenderer;
import mindescape.view.utils.ImageTransformer;

/**
 * Class that renders the room.
 */
public final class RoomRendererImpl implements RoomRenderer {

    private static final double ROTATING_ANGLE = -90;
    private static final int TILE_DIMENSION = (int) Dimensions.TILE.width();
    private BufferedImage roomImage;
    private final ImageTransformer transformer = new ImageTransformer();
    private final transient Map<TiledTile, BufferedImage> tilesCache = new HashMap<>();

    /**
     * Constructor for RoomRenderer.
     * @param currentRoom the room to be rendered.
     */
    public RoomRendererImpl(final Room currentRoom) {
        updateRoomImage(currentRoom);
    }

    @Override
    public BufferedImage getRoomImage() {
        final BufferedImage copy = new BufferedImage(roomImage.getWidth(),
            roomImage.getHeight(),
            roomImage.getType());
        final Graphics2D g = copy.createGraphics();
        g.drawImage(roomImage, 0, 0, null);
        g.dispose();
        return copy;
    }

    @Override
    public void updateRoomImage(final Room currentRoom) {
        roomImage = new BufferedImage((int) currentRoom.getDimensions().height(),
            (int) currentRoom.getDimensions().height(), BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics2D finalMap = roomImage.createGraphics();
        final TiledMap map = new MapReader().getMap(currentRoom.getSource());
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

    private BufferedImage getFallbackImage() {
        final BufferedImage image = new BufferedImage(
                TILE_DIMENSION,
                TILE_DIMENSION,
                BufferedImage.TYPE_4BYTE_ABGR
            );
        final Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, TILE_DIMENSION, TILE_DIMENSION);
        g.dispose();
        return image;
    }

    private Point2D getPositionFromId(final TiledTile tile, final int mapWidth) {
        return new Point2D(tile.getID() % mapWidth, (double) tile.getID() / mapWidth);
    }

    private List<TiledTileLayer> getTileLayers(final TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledTileLayer)
            .map(layer -> (TiledTileLayer) layer)
            .toList();
    }

    private void drawLayer(final TiledTileLayer layer, final Graphics2D g, final TiledMap map) {
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
                    g.drawImage(img, x * TILE_DIMENSION, y * TILE_DIMENSION, null);
                }
            }
        }
    }

    private List<TiledObject> getTileObjects(final TiledMap map) {
        final TiledObjectLayer objects =  map.getNonGroupLayers().stream()
            .filter(layer -> "Objects".equals(layer.getName()))
            .map(layer -> (TiledObjectLayer) layer)
            .findFirst()
            .get();
        return objects.getObjects().stream().filter(obj -> obj.getTile() != null).toList();
    }
}

