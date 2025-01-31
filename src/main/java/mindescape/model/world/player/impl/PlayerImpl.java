package mindescape.model.world.player.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.api.Interactable;
import mindescape.model.world.player.api.Player;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.impl.InventoryImpl;

public class PlayerImpl extends GameObjectImpl implements Player{

    private final Inventory inventory = new InventoryImpl();

    public PlayerImpl(Optional<Point2D> position, String name, Dimensions dimensions) {
            super(position, name, dimensions);
            //TODO Auto-generated constructor stub
    }

    @Override
    public void move(Movement movement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
        //controlli da definire (con marci) e poi fai somme 
    }

    @Override
    public boolean interact(Interactable interactable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

}
