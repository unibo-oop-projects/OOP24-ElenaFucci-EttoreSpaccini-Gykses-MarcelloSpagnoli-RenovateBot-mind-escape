package mindescape.model.world.rooms.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;

import com.google.common.io.Files;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

public class RoomImpl implements Room {

    private final Dimensions dimensions;

    private final Set<GameObject> gameObjects = new HashSet<>();

    private final String name;

    private final String source;

    /*
     * Construction will later be done with a builder
     */
    private RoomImpl(String roomFilePath) {
        TiledMap room = new FileSystemTiledReader().getMap(roomFilePath);
        this.dimensions = new Dimensions(room.getWidth() * Dimensions.TILE.width(), room.getHeight() * Dimensions.TILE.height());
        this.name = Files.getNameWithoutExtension(roomFilePath);
        this.source = roomFilePath;
    }

    @Override
    public boolean isPlayerPresent() {
        return gameObjects.stream().anyMatch(x -> x instanceof Player);
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

    @Override
    public String getName() {
        return this.name;
    }

    public static List<RoomImpl> createRooms() {
        File resources = new File("src/main/java/mindescape/resources/rooms");
        ObjectsExtractor objectsExtractor = new ObjectsExtractor();
        File[] files = resources.listFiles();
        List<RoomImpl> rooms = Arrays.asList(files)
            .stream()
            .map(x -> new RoomImpl(x.getPath()))
            .toList();
        rooms.forEach(room -> {
            objectsExtractor.extractfrom(room.source)
                .forEach(obj -> room.addGameObject(obj));
        });
        rooms.forEach(room -> {
            objectsExtractor.addDoors(room.source, rooms.stream().collect(Collectors.toSet()))
                .forEach(door -> room.addGameObject(door));
        });
        return rooms;
    }
}
