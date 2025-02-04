package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

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

    private final Optional<Pickable> reward;

    /**
     * Constructs an unpickable object with an optional reward.
     *
     * @param name       the name of the unpickable object
     * @param position   the optional position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param reward     an optional {@link Pickable} item rewarded upon interaction
     */
    public UnpickableImpl(final String name, final Optional<Point2D> position,
                          final Dimensions dimensions, final Optional<Pickable> reward) {
        super(position, name, dimensions);
        this.reward = reward;
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
        if (this.reward.isPresent()) {
            player.getInventory().addItems(this.reward.get());
        }
    }
}
