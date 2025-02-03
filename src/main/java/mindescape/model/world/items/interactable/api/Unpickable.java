package mindescape.model.world.items.interactable.api;

/**
 * The {@code Unpickable} interface represents an {@link Interactable} object that cannot be picked up.
 * It provides a method to check if the object is currently unlocked.
 * <p>
 * This interface is useful for defining objects in the game world that are meant to remain stationary,
 * such as doors, safes, or fixed containers, which may require specific actions or conditions to unlock.
 * </p>
 */
public interface Unpickable extends Interactable {
}