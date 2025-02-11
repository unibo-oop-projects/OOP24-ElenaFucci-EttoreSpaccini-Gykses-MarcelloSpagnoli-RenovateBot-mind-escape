package mindescape.model.world.player.api;

import mindescape.model.inventory.api.Inventory;
import mindescape.model.world.items.interactable.api.Interactable;
import mindescape.model.world.rooms.api.Room;

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

    /**
     * Sets the current room for the player.
     *
     * @param room the room to set as the current room
     */
    void setCurrentRoom(Room room);

    /**
     * Retrieves the current room the player is in.
     *
     * @return the current Room object where the player is located.
     */
    Room getCurrentRoom();

    /**
     * Retrieves the player's inventory.
     *
     * @return the inventory of the player
     */
    Inventory getInventory(); 

}
