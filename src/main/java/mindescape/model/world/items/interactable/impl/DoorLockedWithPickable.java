package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.AbstractDoorDecorator;
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
public final class DoorLockedWithPickable extends AbstractDoorDecorator {

    private static final long serialVersionUID = 1L;

    private final Door baseDoor;
    private final int keyItemId;
    private boolean unlocked;

    /**
     * Constructs a door locked with a specific pickable item.
     *
     * @param baseDoor   the base door to decorate with the pickable lock
     * @param keyItemId the ID of the item required to unlock the door
     */
    public DoorLockedWithPickable(final Door baseDoor, final int keyItemId) {
        super(baseDoor);
        this.baseDoor = baseDoor;
        this.keyItemId = keyItemId;
        this.unlocked = false;
    }

    /**
     * Handles the interaction with the door.
     * If the player possesses the required item, the door is unlocked and
     * the base door action is triggered.
     *
     * @param player the player interacting with the door
     */
    @Override
    public void onAction(final Player player) {
        if (player.getInventory().getItems().stream()
                .map(Pickable::getId)
                .anyMatch(id -> id.equals(this.keyItemId))) {
            this.unlocked = true;
            this.baseDoor.onAction(player);
        }
    }

    /**
     * Checks if the door is unlocked.
     *
     * @return {@code true} if the door has been unlocked, {@code false} otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return this.unlocked; 
    }
}
