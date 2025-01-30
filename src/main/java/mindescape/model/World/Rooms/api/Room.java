package mindescape.model.World.Rooms.api;

import java.util.Set;
import mindescape.api.GameObject;

/**
 * Represents a room of the map.
 * 
 * 
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
    void removeGameObject(GameObject gameObject);

    /**
     * Returns all the objects of the room
     * @return the objects of the room as a (?, to decide)
     */
    Set<GameObject> getGameObjects();
}
