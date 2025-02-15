package mindescape.model.world.rooms.impl;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.tiledreader.TiledObject;

import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.enigma.impl.EnigmaFactoryImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.InteractableFactory;
import mindescape.model.world.items.interactable.impl.InteractableFactoryImpl;
import mindescape.model.world.items.interactable.impl.RewardFactory;
import mindescape.model.world.items.noninteractable.impl.NonInteractableImpl;
import mindescape.model.world.rooms.api.Room;

/**
 * Utility class to map TiledObjects to GameObjects when creating rooms.
 */
public final class TiledObjectMapper {
    private final InteractableFactory factory = new InteractableFactoryImpl();
    private final EnigmaFactory enigmas = new EnigmaFactoryImpl();
    private final RewardFactory rewards = new RewardFactory();

    private final Map<String, Function<TiledObject, GameObject>> objectMapper = Map.of(
        "NonInteractableImpl", this::createNonInteractable,
        "UnpickableWithEnigma", this::createUnpickableWithEnigma,
        "Pickable", this::createPickable,
        "Unpickable", this::createUnpickable,
        "LockedUnpickable", this::createLockedUnpickable
    );

    private final Map<String, BiFunction<Room, TiledObject, GameObject>> doorMapper = Map.of(
        "DoorLockedWithEnigma", this::createDoorLockedWithEnigma,
        "DoorLockedWithPickable", this::createDoorLockedWithPickable,
        "SimpleDoor", this::createSimpleDoor
    );

    /**
     * Creates a game object given the tiled object.
     * @param obj the tiled object to convert.
     * @return the {@link GameObject} which corresponds to the tiled object.
     */
    public GameObject getGameObject(final TiledObject obj) {
        if (objectMapper.containsKey(obj.getType())) {
            return objectMapper.get(obj.getType()).apply(obj);
        }
        throw new IllegalArgumentException("Object type not recognized: " + obj.getType());
    }

    /**
     * Creates a door given the tiled object and the room of destination.
     * @param dest the room of destination.
     * @param obj the tiled object to convert.
     * @return the {@link GameObject} which corresponds to the tiled object.
     */
    public GameObject getDoor(final Room dest, final TiledObject obj) {
        if (doorMapper.containsKey(obj.getType())) {
            return doorMapper.get(obj.getType()).apply(dest, obj);
        }
        throw new IllegalArgumentException("Door type not recognized: " + obj.getType());
    }


    private GameObject createNonInteractable(final TiledObject obj) {
        return new NonInteractableImpl(
            new Point2D(obj.getX(), obj.getY()), obj.getName(),
            new Dimensions(obj.getWidth(), obj.getHeight())
        );
    }

    private GameObject createUnpickableWithEnigma(final TiledObject obj) {
        return factory.createUnpickableWithEnigma(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            enigmas.getEnigma((String) obj.getProperties().get("Enigma")),
            rewards.getReward((String) obj.getProperties().get("Reward"))
        );
    }

    private GameObject createPickable(final TiledObject obj) {
        return factory.createPickable(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            (String) obj.getProperties().get("Description"),
            (Integer) obj.getProperties().get("ID")
        );
    }

    private GameObject createUnpickable(final TiledObject obj) {
        return factory.createUnpickable(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            rewards.getReward((String) obj.getProperties().get("Reward"))
        );
    }

    private GameObject createLockedUnpickable(final TiledObject obj) {
        return factory.createLockedUnpickable(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            (Integer) obj.getProperties().get("keyItem_id"),
            rewards.getReward((String) obj.getProperties().get("Reward"))
        );
    }

    private GameObject createDoorLockedWithEnigma(final Room dest, final TiledObject obj) {
        final Point2D destPosition = new Point2D(
                                (int) obj.getProperties().get("DestX"), 
                                (int) obj.getProperties().get("DestY"));
        return factory.createDoorLockedWithEnigma(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            enigmas.getEnigma((String) obj.getProperties().get("Enigma")),
            dest, destPosition
        );
    }

    private GameObject createDoorLockedWithPickable(final Room dest, final TiledObject obj) {
        final Point2D destPosition = new Point2D(
                                (int) obj.getProperties().get("DestX"), 
                                (int) obj.getProperties().get("DestY"));
        return factory.createDoorLockedWithPickable(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            (Integer) obj.getProperties().get("keyItem_id"),
            dest, destPosition
        );
    }
    private GameObject createSimpleDoor(final Room dest, final TiledObject obj) {
        final Point2D destPosition = new Point2D(
                                (int) obj.getProperties().get("DestX"), 
                                (int) obj.getProperties().get("DestY"));
        return factory.createSimpleDoor(
            obj.getName(), new Point2D(obj.getX(), obj.getY()),
            new Dimensions(obj.getWidth(), obj.getHeight()),
            dest, destPosition
        );
    }
}
