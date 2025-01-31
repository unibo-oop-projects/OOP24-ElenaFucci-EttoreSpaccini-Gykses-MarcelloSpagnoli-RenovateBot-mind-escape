package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.rooms.api.Room;

public class DoorWithEnigma extends GameObjectImpl implements Door {

    private final Door baseDoor; 
    private final Enigma enigma; 

    public DoorWithEnigma(final Door baseDoor, final Enigma enigma) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.enigma = enigma; 
        this.baseDoor = baseDoor; 
    }

    @Override
    public boolean unlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unlock'");
    }

    @Override
    public void onAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onAction'");
    }

    @Override
    public void switchRooms() {
        if (this.enigma.isSolved()) {
            this.baseDoor.switchRooms();
        }
    }

    @Override
    public Room getDestinationRoom() {
        return this.baseDoor.getDestinationRoom(); 
    }
    
}
