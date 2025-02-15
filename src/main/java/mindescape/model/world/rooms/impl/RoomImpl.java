package mindescape.model.world.rooms.impl;

import java.io.Serializable;
import java.net.URL;
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

/**
 * This class implements {@link Room}.
 */
public final class RoomImpl implements Room, Serializable {

    private static final long serialVersionUID = 1L;
    private final Dimensions dimensions;
    private final Set<GameObject> gameObjects = new HashSet<>();
    private final String name;
    private final String source;

    /**
     * Constructor of the class.
     * @param roomFilePath path to the rosom file
     */
    public RoomImpl(final String roomFilePath) {
        final TiledMap room = new FileSystemTiledReader().getMap(roomFilePath);
        this.dimensions = new Dimensions(room.getWidth() * Dimensions.TILE.width(), room.getHeight() * Dimensions.TILE.height());
        this.name = Files.getNameWithoutExtension(roomFilePath);
        this.source = roomFilePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerPresent() {
        return gameObjects.stream().anyMatch(x -> x instanceof Player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGameObject(final GameObject gameObject) {
        if (gameObject.getPosition() != null && isPositionValid(gameObject.getPosition(), gameObject.getDimensions())) {
            gameObjects.add(gameObject);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGameObject(final GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getGameObjects() {
        return Collections.unmodifiableSet(this.gameObjects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPositionValid(final Point2D pos, final Dimensions dim) {
        return pos.x() >= 0 
            && pos.y() >= 0 
            && pos.x() + dim.width() <= this.dimensions.width() 
            && pos.y() + dim.height() <= this.dimensions.height();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RoomImpl{" 
            + "dimensions=" + dimensions 
            + ", gameObjects=" + gameObjects 
            + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * This is a static factory to create the rooms.
     * @return a list of {@link Room} from the files found in resources
     */
    public static List<Room> createRooms() {
        final ObjectsExtractor objectsExtractor = new ObjectsExtractor();
        final List<String> files = listRooms();
        final List<Room> rooms = files.stream()
            .map(RoomImpl::new)
            .collect(Collectors.toList());
        rooms.forEach(room -> {
            objectsExtractor.extractfrom(room.getSource())
                .forEach(room::addGameObject);
        });
        rooms.forEach(room -> {
            objectsExtractor.exstractDoors(room.getSource(), rooms.stream().collect(Collectors.toSet()))
                .forEach(room::addGameObject);
        });
        return rooms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    private static List<String> listRooms() {
        final URL resourceUrl = RoomImpl.class.getClassLoader().getResource("rooms");
        List<String> files = List.of();
        if (resourceUrl != null && "jar".equals(resourceUrl.getProtocol())) {
            files = Arrays.stream(RoomNames.values())
                .map(x -> "rooms/" + x.getName() + ".tmx")
                .toList();
        } else if (resourceUrl != null) {
            files = Arrays.stream(RoomNames.values())
                .map(x -> resourceUrl.getPath() + "/" + x.getName() + ".tmx")
                .toList();
        }
        return files;
    }
}
