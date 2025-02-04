package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents a door that requires a specific {@link Pickable} item to unlock.
 * <p>
 * Acts as a decorator for a base {@link Door}, adding a mechanism to check the
 * player's inventory for the required item. Extends {@link GameObjectImpl} to
 * inherit properties like position, name, and dimensions.
 * </p>
 *
 * @see Door
 * @see Pickable
 * @see GameObjectImpl
 */
public class DoorLockedWithPickable extends GameObjectImpl implements Door {

    private final Door baseDoor;
    private final int keyItem_id;

    /**
     * Constructs a door locked with a specific pickable item.
     *
     * @param baseDoor   the base door to decorate with the pickable lock
     * @param keyItem_id the ID of the item required to unlock the door
     */
    public DoorLockedWithPickable(final Door baseDoor, final int keyItem_id) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.baseDoor = baseDoor;
        this.keyItem_id = keyItem_id;
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
        return player.getInventory().getItems().stream()
                     .map(Pickable::getId)
                     .anyMatch(id -> id.equals(this.keyItem_id));
    }
}
