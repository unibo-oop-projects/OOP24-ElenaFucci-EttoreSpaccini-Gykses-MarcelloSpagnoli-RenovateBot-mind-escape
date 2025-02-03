package mindescape.model.world.rooms.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledLayer;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObjectLayer;
import org.tiledreader.TiledReader;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.api.Pickable;
import mindescape.model.world.rooms.api.Room;

public class RoomImpl implements Room {

    private final Dimensions dimensions;

    private final Set<GameObject> gameObjects = new HashSet<>();

    public RoomImpl(String roomFilePath) {
        TiledReader reader = new FileSystemTiledReader();
        TiledMap room = reader.getMap(roomFilePath);
        this.dimensions = new Dimensions(room.getWidth(), room.getHeight());
        for (TiledLayer layer : room.getNonGroupLayers()) {
            if (layer instanceof TiledLayer) {
                TiledObjectLayer objectLayer = (TiledObjectLayer) layer;
                /**
                 * TODO when gameobj factory is ok
                 */
                //objectLayer.getObjects().stream().forEach(x -> addGameObject(new GameObject()));
            }
        }
        
    }

    //TODO when PlayerImpl will be ready
    @Override
    public boolean isPlayerPresent() {
        return true;
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObject.getPosition().isPresent() && isPositionValid(gameObject.getPosition().get(), gameObject.getDimensions())) {
            gameObjects.add(gameObject);
        }
    }

    @Override
    public void removeGameObject(Pickable gameObject) {
        gameObjects.remove(gameObject);
    }

    @Override
    public Set<GameObject> getGameObjects() {
        return Collections.unmodifiableSet(this.gameObjects);
    }

    public boolean isPositionValid(Point2D pos, Dimensions dim) {
        return pos.x() >= 0 &&
            pos.y() >= 0 && 
            pos.x() + dim.height() <= this.dimensions.height() &&
            pos.y() + dim.height() <= this.dimensions.height();
    }

    @Override
    public String toString() {
        return "RoomImpl{" +
            "dimensions=" + dimensions +
            ", gameObjects=" + gameObjects +
            '}';
    }
}
