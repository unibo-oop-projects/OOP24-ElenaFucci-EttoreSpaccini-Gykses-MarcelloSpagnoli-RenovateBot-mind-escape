package mindescape.model.world.rooms.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObjectLayer;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.rooms.api.Room;

/**
 * Utility class to extract and create objects from a room file.
 */
public final class ObjectsExtractor {

    private final TiledObjectMapper mapper = new TiledObjectMapper();

    /**
     * Return the set of GameObject of the room.
     * @param roomPath path to xml file describing the room
     * @return set of game objects
     */
    Set<GameObject> extractfrom(final String roomPath) {
        final Set<GameObject> gameObjects = new HashSet<>();
        final TiledMap map = new FileSystemTiledReader().getMap(roomPath);
        final List<TiledObjectLayer> layers = getObjectLayers(map)
            .stream().filter(layer -> !"Doors".equals(layer.getName()))
            .toList();
        for (final TiledObjectLayer layer : layers) {
            layer.getObjects().forEach(object -> {
                final GameObject gameObject = mapper.getGameObject(object);
                gameObjects.add(gameObject);
            });
        }
        return gameObjects;
    }

    /**
     * This method gives the doors gives a path to one room file and a set of existing rooms.
     * @param roomPath path to xml file describing the room
     * @param rooms set of existing rooms
     * @return set of doors of the room
     */
    Set<GameObject> exstractDoors(final String roomPath, final Set<Room> rooms) {
        final Set<GameObject> doors = new HashSet<>();
        final TiledMap map = new FileSystemTiledReader().getMap(roomPath);
        final List<TiledObjectLayer> doorLayers = getObjectLayers(map)
            .stream().filter(layer -> "Doors".equals(layer.getName()))
            .toList();
        for (final TiledObjectLayer doorLayer : doorLayers) {
            doorLayer.getObjects().forEach(object -> {
                final Room dest = rooms.stream()
                    .filter(room -> room.getName().equals(object.getProperties().get("Destination")))
                    .findFirst().get();
                final GameObject door = mapper.getDoor(dest, object);
                doors.add(door);
            });
        }
        return doors;
    }

    private List<TiledObjectLayer> getObjectLayers(final TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledObjectLayer)
            .map(layer -> (TiledObjectLayer) layer)
            .collect(Collectors.toList());
    }

}


