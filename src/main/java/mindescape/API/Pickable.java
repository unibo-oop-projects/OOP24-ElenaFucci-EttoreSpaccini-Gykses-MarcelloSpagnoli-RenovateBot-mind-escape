package mindescape.api;

/**
 * The Pickable interface represents an object that can be picked up in the game.
 * It extends the Interactable interface, indicating that pickable objects can also be interacted with.
 */
public interface Pickable extends Interactable {

    /**
     * Checks if the item has been picked.
     *
     * @return true if the item is picked, false otherwise
     */
    boolean isPicked();
}
