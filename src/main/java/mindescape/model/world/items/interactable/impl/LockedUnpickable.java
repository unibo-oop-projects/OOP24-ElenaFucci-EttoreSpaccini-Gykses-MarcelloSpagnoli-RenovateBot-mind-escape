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

    private final Integer keyItem_id;
    private final Pickable reward; 

    /**
     * Constructs a locked unpickable object.
     *
     * @param name       the name of the unpickable object
     * @param position   the optional position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param keyItem    the pickable item required to unlock the object
     * @param reward     the pickable item rewarded after unlocking
     */
    public LockedUnpickable(final String name, final Optional<Point2D> position,
                             final Dimensions dimensions, final Integer keyItem_id,
                             final Pickable reward) {
        super(position, name, dimensions);
        this.keyItem_id = keyItem_id;
        this.reward = reward;
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
        if (this.isUnlocked(player)) {
            player.getInventory().addItems(this.reward);
        }
    }

    /**
     * Checks if the unpickable object is unlocked by verifying if the player
     * has the required item in their inventory.
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
