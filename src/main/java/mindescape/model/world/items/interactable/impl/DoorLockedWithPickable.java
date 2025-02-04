package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents a door that requires a specific {@link Pickable} item to unlock.
 * <p>
 * This class acts as a decorator for a base {@link Door}, adding a mechanism
 * that checks the player's inventory for the required item to unlock the door.
 * It extends {@link GameObjectImpl} to inherit common properties like position,
 * name, and dimensions.
 * </p>
 *
 * @see Door
 * @see Pickable
 * @see GameObjectImpl
 */
public class DoorLockedWithPickable extends GameObjectImpl implements Door {

    private final Door baseDoor;
    private final Pickable unlockedItem;

    /**
     * Constructs a door that is locked with a specific pickable item.
     *
     * @param baseDoor     the base door to decorate with the pickable lock
     * @param unlockedItem the item required to unlock the door
     */
    public DoorLockedWithPickable(final Door baseDoor, final Pickable unlockedItem) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.baseDoor = baseDoor;
        this.unlockedItem = unlockedItem;
    }

    /**
     * Defines the interaction behavior when the player interacts with the door.
     * <p>
     * The door can only be used if the player possesses the required item.
     * If unlocked, the interaction is delegated to the base door.
     * </p>
     *
     * @param player the player interacting with the door
     */
    @Override
    public void onAction(final Player player) {
        if (this.isUnlocked(player)) {
            this.baseDoor.onAction(player);
        }
    }

    /**
     * Checks if the door is unlocked by verifying if the player has the required item.
     *
     * @param player the player whose inventory is checked
     * @return {@code true} if the player has the required item, {@code false} otherwise
     */
    private boolean isUnlocked(final Player player) {
        return player.getInventory().getItems().contains(this.unlockedItem);
    }
}
