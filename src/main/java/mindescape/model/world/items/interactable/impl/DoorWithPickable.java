package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.rooms.api.Room;

public class DoorWithPickable extends GameObjectImpl implements Door {

    private final Door baseDoor; 
    private final Pickable pickable;
    private boolean unlock; 

    public DoorWithPickable(final Door baseDoor, final Pickable pickable) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.baseDoor = baseDoor; 
        this.pickable = pickable; 
        this.unlock = false; 
    }

    @Override
    public boolean unlock() {
        if (!this.unlock && this.pickable.inInventory()) {
            this.unlock = true; 
        }
        return this.unlock; 
    }

    @Override
    public void onAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onAction'");
    }

    @Override
    public void switchRooms() {
        if (this.unlock) {
            this.baseDoor.switchRooms();
        }
    }

    @Override
    public Room getDestinationRoom() {
        return this.baseDoor.getDestinationRoom(); 
    }    
}
