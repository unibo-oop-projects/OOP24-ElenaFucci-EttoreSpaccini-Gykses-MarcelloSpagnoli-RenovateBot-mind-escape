package mindescape.model.world.player.api;

import mindescape.model.world.items.interactable.api.Interactable;

/**
 * Represents a player in the game who can move and interact with objects.
 * This interface extends the Movable interface, indicating that a player
 * can perform movement-related actions.
 * @see Movable
 * @see Interactable
 */
public interface Player extends Movable {

    /**
     * Interacts with an object.
     * @param interactable the object to interact with.
     * @return true if the interaction was successful, false otherwise.
     */
    boolean interact(Interactable interactable);

}
