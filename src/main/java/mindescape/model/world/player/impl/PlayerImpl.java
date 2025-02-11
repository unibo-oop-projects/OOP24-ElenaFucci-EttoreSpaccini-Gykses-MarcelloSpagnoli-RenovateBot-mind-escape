package mindescape.model.world.player.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.impl.InventoryImpl;
import mindescape.model.world.items.interactable.api.Interactable;


public class PlayerImpl extends GameObjectImpl implements Player{

    private final Inventory inventory = new InventoryImpl();
    private Room currentRoom;


    /**
     * Constructs a new PlayerImpl instance with the specified position, name, dimensions, and current room.
     *
     * @param position    an Optional containing the initial position of the player, or an empty Optional if the position is not specified
     * @param name        the name of the player
     * @param dimensions  the dimensions of the player
     * @param currentRoom the current room the player is in
     */
    public PlayerImpl(Optional<Point2D> position, String name, Dimensions dimensions, Room currentRoom) {
            super(position, name, dimensions);
            this.currentRoom = currentRoom;
    }

    /**
     * Moves the player to a new position based on the given movement.
     *
     * @param movement the movement to be applied to the player's current position
     */
    @Override
    public void move(Movement movement) {
        var currentPosition = getPosition().get();
        var newPosition = new Point2D(currentPosition.x() + movement.getX(), currentPosition.y() + movement.getY()); 
        setPosition(Optional.of(newPosition));
   }

    /**
     * Interacts with the given interactable object.
     *
     * @param interactable the object to interact with
     * @return true if the interaction was successful, false if the interactable is null
     */
    @Override
    public boolean interact(Interactable interactable) {
        if(interactable == null){
            return false;
        }        
        interactable.onAction(this);
        return true;
    }

    /**
     * Sets the current room for the player.
     *
     * @param room the room to set as the current room
     */
    @Override
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;

    }

    /**
     * Retrieves the current room the player is in.
     *
     * @return the current room of the player.
     */
    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Retrieves the inventory of the player.
     *
     * @return the player's inventory
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}
