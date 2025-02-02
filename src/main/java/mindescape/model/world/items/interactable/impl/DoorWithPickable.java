package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.rooms.api.Room;

public class DoorWithPickable extends GameObjectImpl implements Door {

    private final Door baseDoor; 
    private final Pickable pickable;
    private boolean unlocked; 

    public DoorWithPickable(final Door baseDoor, final Pickable pickable) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.baseDoor = baseDoor; 
        this.pickable = pickable; 
        this.unlocked = false; 
    }

    @Override
    public void onAction() {
        if (!this.unlocked && this.pickable.isPicked()) {
            baseDoor.onAction(); 
        }
    }

    @Override
    public void switchRooms() {
        if (this.unlocked) {
            this.baseDoor.switchRooms();
        }
    }

    @Override
    public Room getDestinationRoom() {
        return this.baseDoor.getDestinationRoom(); 
    }

    @Override
    public boolean isUnlocked() {
        return baseDoor.isUnlocked(); 
    }    
}
