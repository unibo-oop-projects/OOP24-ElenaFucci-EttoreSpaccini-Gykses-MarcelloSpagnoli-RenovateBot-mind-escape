package mindescape.model.world.items.interactable.api;

import mindescape.model.world.items.api.Unmovable;

/**
 * Represents an object that can be interacted with in the game.
 * This interface extends the {@link Unmovable} interface, indicating that
 * the object cannot be moved but can be interacted with.
 *
 * <p>Classes implementing this interface should provide an implementation
 * for the {@code onAction} method, which defines the behavior when an
 * action is performed on the object.</p>
 *
 * @see Unmovable
 */
public interface Interactable extends Unmovable {

    /**
     * Performs an action on the interactable object.
     */
    void onAction();
}
