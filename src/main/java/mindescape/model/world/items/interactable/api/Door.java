package mindescape.model.world.items.interactable.api;

import mindescape.model.world.rooms.api.Room;

/**
 * Represents a door in the game world.
 * A door is an unpickable object that connects two rooms and may require
 * a key or an enigma to be unlocked.
 */
public interface Door extends Unpickable {

    /**
     * Switches the player's current room to the connected destination room.
     * This method is called when the player successfully interacts with the door.
     */
    void switchRooms();

    Room getDestinationRoom(); 
}