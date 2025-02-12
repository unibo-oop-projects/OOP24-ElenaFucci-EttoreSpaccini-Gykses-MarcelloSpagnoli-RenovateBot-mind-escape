package mindescape.model.world.rooms.impl;

import java.io.File;
import java.io.Serializable;
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
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

public class RoomImpl implements Room, Serializable {

    private final Dimensions dimensions;

    private final Set<GameObject> gameObjects = new HashSet<>();

    private final String name;

    private final String source;

    /*
     * Construction will later be done with a builder
     */
    public RoomImpl(String roomFilePath) {
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
        if (gameObject.getPosition() != null && isPositionValid(gameObject.getPosition(), gameObject.getDimensions())) {
            gameObjects.add(gameObject);
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    @Override
    public Set<GameObject> getGameObjects() {
        return Collections.unmodifiableSet(this.gameObjects);
    }

    public boolean isPositionValid(Point2D pos, Dimensions dim) {
        return pos.x() >= 0 &&
            pos.y() >= 0 && 
            pos.x() + dim.width() <= this.dimensions.width() &&
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

    public static List<Room> createRooms() {
        File resources = new File("src/resources/rooms");
        ObjectsExtractor objectsExtractor = new ObjectsExtractor();
        File[] files = resources.listFiles();
        List<Room> rooms = Arrays.asList(files)
            .stream()
            .map(x -> (Room) new RoomImpl(x.getPath()))
            .toList();
        rooms.forEach(room -> {
            objectsExtractor.extractfrom(room.getSource())
                .forEach(obj -> room.addGameObject(obj));
        });
        rooms.forEach(room -> {
            objectsExtractor.addDoors(room.getSource(), rooms.stream().collect(Collectors.toSet()))
                .forEach(door -> room.addGameObject(door));
        });
        return rooms;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }
}
