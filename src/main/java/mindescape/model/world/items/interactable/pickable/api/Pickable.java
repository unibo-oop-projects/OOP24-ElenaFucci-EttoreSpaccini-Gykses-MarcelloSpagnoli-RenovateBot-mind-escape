package mindescape.model.world.items.interactable.pickable.api;

import mindescape.model.world.items.interactable.api.Interactable;

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

    /**
     * Provides a description of the pickable item.
     *
     * @return a string representing the description of the item.
     */
    String getDescription();
}
