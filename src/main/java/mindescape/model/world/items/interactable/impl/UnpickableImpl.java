package mindescape.model.world.items.interactable.impl;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents an unpickable object that may provide a {@link Pickable}
 * reward when interacted with.
 * <p>
 * This class extends {@link GameObjectImpl} to inherit common properties
 * such as position, name, and dimensions.
 * </p>
 *
 * @see Unpickable
 * @see Pickable
 * @see GameObjectImpl
 */
public class UnpickableImpl extends GameObjectImpl implements Unpickable {

    private static final long serialVersionUID = 1L;

    private final Pickable reward;
    private boolean unlocked;

    /**
     * Constructs an unpickable object with an optional reward.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param reward     a pickable item rewarded upon interaction
     */
    public UnpickableImpl(final String name, final Point2D position,
                          final Dimensions dimensions, final Pickable reward) {
        super(position, name, dimensions);
        this.reward = reward;
        this.unlocked = false;
    }

    /**
     * Defines the interaction behavior when the player interacts with the object.
     * <p>
     * If a reward is present, it is added to the player's inventory.
     * </p>
     *
     * @param player the player interacting with the object
     */
    @Override
    public void onAction(final Player player) {
        if (this.reward != null
            && player.getInventory().getItems().stream()
                    .noneMatch(item -> item.equals(this.reward))) {
            this.unlocked = true;
            player.getInventory().addItems(this.reward);
        }
    }

    /**
     * Checks if the item is unlocked.
     *
     * @return {@code true} if the item is unlocked, {@code false} otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return this.unlocked; 
    }
}
