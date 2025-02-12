package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.items.interactable.api.Pickable;

/**
 * Since rewards are Pickables corresponding to certain Unpickables, this class
 * is used to create and keep a link between the two.
 */
public class RewardFactory {
    //aggiungere man mano  
    public Pickable getReward(final String name) {
        switch (name) {
            case "Code note":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "15674",
                    1);
            case "Calendar note":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "The key is in the routine",
                    2);
            case "Office key":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "A key",
                    2);
            case "Torch":
                return new PickableImpl(null, name, Dimensions.TILE,
                "A torch",
                3);
            case "Key":
                return new PickableImpl(null, name, Dimensions.TILE,
                "The key to the last room",
                4);
            case "Hammer":
                return new PickableImpl(null, name, Dimensions.TILE,
                "An hammer",
                5);
            case "a":
                return new PickableImpl(null, name, null, name, 0);
            default:
                return null;
        }
    }
}
