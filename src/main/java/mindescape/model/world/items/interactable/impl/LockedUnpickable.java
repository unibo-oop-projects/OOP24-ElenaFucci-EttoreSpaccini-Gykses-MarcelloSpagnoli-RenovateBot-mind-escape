package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents an unpickable object that requires a specific {@link Pickable}
 * item to unlock and rewards the player with another item upon unlocking.
 * <p>
 * This class extends {@link GameObjectImpl} to inherit common properties
 * such as position, name, and dimensions.
 * </p>
 *
 * @see Unpickable
 * @see Pickable
 * @see GameObjectImpl
 */
public class LockedUnpickable extends GameObjectImpl implements Unpickable {

    private final int keyItemId;
    private final Optional<Pickable> reward;
    private boolean unlocked;

    /**
     * Constructs a locked unpickable object.
     *
     * @param name       the name of the unpickable object
     * @param position   the optional position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param keyItemId  the ID of the pickable item required to unlock the object
     * @param reward     the pickable item rewarded after unlocking
     */
    public LockedUnpickable(final String name, final Optional<Point2D> position,
                            final Dimensions dimensions, final int keyItemId,
                            final Optional<Pickable> reward) {
        super(position, name, dimensions);
        this.keyItemId = keyItemId;
        this.reward = reward;
        this.unlocked = false;
    }

    /**
     * Defines the interaction behavior when the player interacts with the object.
     * <p>
     * If the player possesses the required item, the reward is added to their inventory.
     * </p>
     *
     * @param player the player interacting with the object
     */
    @Override
    public void onAction(final Player player) {
        if (player.getInventory().getItems().stream()
                  .map(Pickable::getId)
                  .anyMatch(id -> id.equals(this.keyItemId))
            && player.getInventory().getItems().stream()
                  .noneMatch(item -> item.equals(this.reward))) {
            this.unlocked = true;
            if (reward.isPresent()) {
                player.getInventory().addItems(this.reward.get());
            }
        }
    }

    /**
     * Checks if the item is unlocked.
     *
     * @return {@code true} if the item is unlocked, {@code false} otherwise.
     */
    public boolean isUnlocked() {
        return this.unlocked;
    }
}
