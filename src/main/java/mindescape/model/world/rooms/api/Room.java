package mindescape.model.world.rooms.api;

import java.util.Set;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.api.Pickable;

/**
 * Represents a room of the map.
 */
public interface Room {
    /**
     * Determines if player is in this room.
     * @return true if player is present, false otherwise.
     */
    boolean isPlayerPresent();

    /**
     * Adds an object to the room.
     * @param gameObject the object to add.
     */
    void addGameObject(GameObject gameObject);

    /**
     * Removes and object from the room.
     * @param gameObject the object to remove.
     */
    void removeGameObject(Pickable gameObject);

    /**
     * Returns all the objects of the room.
     * @return the objects of the room as a (?, to decide)
     */
    Set<GameObject> getGameObjects();

    /**
     * Checks if a position is valid based on the bounds of the room.
     * @param pos the position of the object (as the upper left edge)
     * @param dim the dimensions of the object
     * @return if the object is in a valid position
     */
    boolean isPositionValid(Point2D pos, Dimensions dim);
}
