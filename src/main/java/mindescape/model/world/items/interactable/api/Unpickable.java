package mindescape.model.world.items.interactable.api;

/**
 * The Unpickable interface represents an object that cannot be picked up.
 */
public interface Unpickable extends Interactable {

    /**
     * Attempts to unlock the object.
     *
     * @return true if the object was successfully unlocked, false otherwise.
     */
    boolean unlock();
}
