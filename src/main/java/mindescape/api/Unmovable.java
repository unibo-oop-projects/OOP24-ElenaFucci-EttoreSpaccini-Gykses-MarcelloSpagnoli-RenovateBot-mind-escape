package mindescape.api;

/**
 * The Unmovable interface represents a game object that cannot be moved.
 * It extends the GameObject interface and provides a method to handle
 * collisions with a player.
 * 
 * <p>Classes implementing this interface should define the behavior that
 * occurs when a player collides with the unmovable object.</p>
 * 
 * @see GameObject
 */
public interface Unmovable extends GameObject {

    /**
     * This method is called when a player collides with this object.
     * 
     * @param player the player that collided with this object
     */
    void onCollision(Player player); 
}