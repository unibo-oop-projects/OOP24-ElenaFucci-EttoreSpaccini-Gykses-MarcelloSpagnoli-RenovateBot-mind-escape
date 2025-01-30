package mindescape.model.api;

import java.util.Set;

import mindescape.api.GameObject;

/**
 * Represents a room of the map.
 * 
 * 
 */
public interface Room {
    
    
    /**
     * @return whether the player is prensent in the room or not
     */
    boolean isPlayerPresent();

    /**
     * Adds an object to the room
     * @param gameObject
     */
    void addGameObject(GameObject gameObject);

    /**
     * Removes and object from the room
     * @param gameObject
     */
    void removeGameObject(GameObject gameObject);

    /**
     * returns all the objects of the room
     * @return the objects of the room as a (?, to decide)
     */
    Set<GameObject> getGameObjects();
}
