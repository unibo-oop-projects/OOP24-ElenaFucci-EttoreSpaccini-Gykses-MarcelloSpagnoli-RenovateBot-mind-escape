package mindescape.api;

/**
 * Represents an object that can be interacted with in the game.
 * This interface extends the Unmovable interface, indicating that
 * the object cannot be moved but can be interacted with.
 * 
 * <p>Classes implementing this interface should provide the implementation
 * for the {@code onAction} method, which defines the behavior when an
 * action is performed on the object.</p>
 * 
 * @see Unmovable
 */
public interface Interactable extends Unmovable {

    /**
     * This method is called when an action is performed on the interactable object.
     * 
     * @return true if the action was successful, false otherwise.
     */
    boolean onAction();
}
