package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.items.interactable.api.Pickable;

/**
 * Since rewards are Pickables corresponding to certain Unpickables, this class
 * is used to create and keep a link between the two.
 */
public class RewardFactory {

    private static final String BED_NOTE_DESC = "3 is the magic number";
    private static final String CANTEEN_NOTE_DESC = "The key is in the routine";
    private static final String OFFICE_KEY_DESC = "A key";
    private static final String KEY_DESC = "The key to the last room";
    private static final String HAMMER_DESC = "An hammer";
    private static final String MESSAGE_DESC = "They tried to erase you, to keep you trapped inside your own mind.\n" 
            + "Every locked door, every puzzle—it's all been their way of testing you, breaking you.\n" 
            + "But you made it through. And now, you're close. So close. Now go, the last door has opened";
    private static final String DUMMY_DESC = "dummy";

    private static final int BED_NOTE_ID = 1;
    private static final int CANTEEN_NOTE_ID = 2;
    private static final int OFFICE_KEY_ID = 3;
    private static final int KEY_ID = 4;
    private static final int HAMMER_ID = 5;
    private static final int MESSAGE_ID = 6;
    private static final int DUMMY_ID = 0;

    /**
     * Returns the reward corresponding to the given name.
     * @param name
     * @return the reward corresponding to the given name as a {@code Pickable} or null if the name is not recognized.
     */
    public Pickable getReward(final String name) {
        switch (name) {
            case "Bed note":
                return new PickableImpl(null, name, Dimensions.TILE, BED_NOTE_DESC, BED_NOTE_ID);
            case "Canteen note":
                return new PickableImpl(null, name, Dimensions.TILE, CANTEEN_NOTE_DESC, CANTEEN_NOTE_ID);
            case "Office key":
                return new PickableImpl(null, name, Dimensions.TILE, OFFICE_KEY_DESC, OFFICE_KEY_ID);
            case "Key":
                return new PickableImpl(null, name, Dimensions.TILE, KEY_DESC, KEY_ID);
            case "Hammer":
                return new PickableImpl(null, name, Dimensions.TILE, HAMMER_DESC, HAMMER_ID);
            case "Message":
                return new PickableImpl(null, name, Dimensions.TILE, MESSAGE_DESC, MESSAGE_ID);
            case "dummy":
                return new PickableImpl(null, name, null, DUMMY_DESC, DUMMY_ID);
            default:
                return null;
        }
    }
}
