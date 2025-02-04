package mindescape.model.world.items.api;

import mindescape.model.world.core.api.GameObject;

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
}
