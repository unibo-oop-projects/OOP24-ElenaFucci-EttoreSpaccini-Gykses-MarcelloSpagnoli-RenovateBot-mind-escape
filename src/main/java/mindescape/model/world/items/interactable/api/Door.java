package mindescape.model.world.items.interactable.api;

import mindescape.model.world.rooms.api.Room;

/**
 * Represents a door in the game world.
 * <p>
 * A door is an unpickable object that connects two rooms and may require
 * a key or an enigma to be unlocked.
 * </p>
 */
public interface Door extends Unpickable {

    /**
     * Switches the player's current room to the connected destination room.
     * <p>
     * This method is called when the player successfully interacts with the door.
     * </p>
     */
    void switchRooms();

    /**
     * Retrieves the destination room connected by this door.
     *
     * @return the destination room that the door leads to
     */
    Room getDestinationRoom();
}
