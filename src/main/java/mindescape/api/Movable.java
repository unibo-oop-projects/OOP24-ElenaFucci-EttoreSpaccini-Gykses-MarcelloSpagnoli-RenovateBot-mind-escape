package mindescape.api;

/**
 * Represents an object that can move within the game maps.
 * This interface extends the GameObject interface and provides
 * a method to move the object in a specified direction.
 */
public interface Movable extends GameObject {

    /**
     * Move the object in the given direction.
     * @param movement the movement
     */
    void move(Movement movement);

}