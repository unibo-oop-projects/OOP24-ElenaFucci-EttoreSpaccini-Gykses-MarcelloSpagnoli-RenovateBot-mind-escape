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


    public PlayerImpl(Optional<Point2D> position, String name, Dimensions dimensions, Room currentRoom) {
            super(position, name, dimensions);
            this.currentRoom = currentRoom;
            //TODO Auto-generated constructor stub
    }

    @Override
    public void move(Movement movement) {
        var currentPosition = getPosition().get();
        var newPosition = new Point2D(currentPosition.x() + movement.getX(), currentPosition.y() + movement.getY()); 
        setPosition(Optional.of(newPosition));
   }

    @Override
    public boolean interact(Interactable interactable) {
        if(interactable == null){
            return false;
        }        
        interactable.onAction(this);
        return true;
    }
    @Override
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;

    }

    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}
