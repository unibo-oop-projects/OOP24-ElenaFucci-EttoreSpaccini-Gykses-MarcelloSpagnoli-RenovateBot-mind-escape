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
            case "Bed note":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "3 is the magic number",
                    1);
            case "Canteen note":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "The key is in the routine",
                    2);
            case "Office key":
                return new PickableImpl(null, name, Dimensions.TILE,
                    "A key",
                    3);
            case "Key":
                return new PickableImpl(null, name, Dimensions.TILE,
                "The key to the last room",
                4);
            case "Hammer":
                return new PickableImpl(null, name, Dimensions.TILE,
                "An hammer",
                5);
            case "Message":
                return new PickableImpl(null, name, Dimensions.TILE,
                "They tried to erase you, to keep you trapped inside your own mind.\n" +
                "Every locked door, every puzzle—it's all been their way of testing you, breaking you.\n" +
                "But you made it through. And now, you're close. So close." + 
                "Now go, the last door has opened",
                6);
            case "dummy":
                return new PickableImpl(null, name, null, name, 0);
            default:
                return null;
        }
    }
}
