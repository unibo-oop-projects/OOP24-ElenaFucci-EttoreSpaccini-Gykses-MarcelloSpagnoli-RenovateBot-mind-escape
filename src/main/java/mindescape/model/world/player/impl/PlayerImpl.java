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
import mindescape.model.world.items.api.Pickable;
import mindescape.model.world.items.api.Unpickable;


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
        if(interactable == null){
            return false;
        }

        if(interactable.onAction()){
            if(interactable instanceof Pickable){
                Pickable pickableItem = (Pickable) interactable;
                for (Pickable element : inventory.getItems()) {
                    if(element == pickableItem){
                        return false;
                    }                    
                }
                inventory.addItems(pickableItem);
                return true;                
            }

            if(interactable instanceof Unpickable){
                Unpickable unpickableItem = (Unpickable) interactable;
                if(unpickableItem.isLocked()){
                    //controlla se nell'inventario hai l'oggetto per sbloccare l'unpickable, se è cosi metti unlock a true
                }

            


                
            }
            
        }


    }

}
