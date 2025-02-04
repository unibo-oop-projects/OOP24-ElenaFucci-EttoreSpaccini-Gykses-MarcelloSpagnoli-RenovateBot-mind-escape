package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.items.interactable.api.Pickable;

/**
 * Since rewards are Pickables corresponding to certain Unpickables, this class
 * is used to create and keep a link between the two.
 */
public class RewardFactory {
    //aggiungere man mano  
    public Optional<Pickable> getReward(String name) {
        switch (name) {
            case "Code note":
                return Optional.of(new PickableImpl(Optional.empty(), name, Dimensions.TILE, "15674"));
            default:
                return Optional.empty();
        }
    }
}
