package mindescape.model.world.items.interactable.api;

/**
 * The Pickable interface represents an object that can be picked up in the game. 
 * It extends the Interactable interface, indicating that pickable objects can 
 * also be interacted with.
 */
public interface Pickable extends Interactable {

    /**
     * Returns a description of the pickable item.
     *
     * @return a string representing the item's description.
     */
    String getDescription();

    /**
     * Returns the unique identifier of the pickable item.
     *
     * @return {@code int} representing the item's ID.
     */
    int getId();
}
