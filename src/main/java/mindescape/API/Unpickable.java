package mindescape.api;

/**
 * The Unpickable interface represents an object that cannot be picked up.
 */
public interface Unpickable extends Interactable {

    /**
     * Checks if the object is locked.
     *
     * @return true if the object is locked, false otherwise.
     */
    boolean isLocked();

    /**
     * Attempts to unlock the object.
     *
     * @return true if the object was successfully unlocked, false otherwise.
     */
    boolean unlock();
}
