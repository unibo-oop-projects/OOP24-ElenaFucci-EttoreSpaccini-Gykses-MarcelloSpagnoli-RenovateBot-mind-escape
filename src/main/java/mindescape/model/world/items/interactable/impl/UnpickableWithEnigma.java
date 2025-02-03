package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents an unpickable object that requires solving an {@link Enigma}
 * to unlock and may provide a {@link Pickable} reward upon successful resolution.
 * <p>
 * This class extends {@link GameObjectImpl} to inherit common properties
 * such as position, name, and dimensions.
 * </p>
 *
 * @see Unpickable
 * @see Pickable
 * @see Enigma
 * @see GameObjectImpl
 */
public class UnpickableWithEnigma extends GameObjectImpl implements Unpickable {

    private final Optional<Pickable> reward;
    private final Enigma enigma;

    /**
     * Constructs an unpickable object that requires solving an enigma to unlock.
     *
     * @param name       the name of the unpickable object
     * @param position   the optional position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param enigma     the enigma required to unlock the object
     * @param reward     an optional {@link Pickable} item rewarded upon solving the enigma
     */
    public UnpickableWithEnigma(final String name, final Optional<Point2D> position,
                                final Dimensions dimensions, final Enigma enigma,
                                final Optional<Pickable> reward) {
        super(position, name, dimensions);
        this.reward = reward;
        this.enigma = enigma;
    }

    /**
     * Defines the interaction behavior when the player interacts with the object.
     * <p>
     * If the enigma is solved and a reward is present, the reward is added to
     * the player's inventory.
     * </p>
     *
     * @param player the player interacting with the object
     */
    @Override
    public void onAction(final Player player) {
        if (this.isUnlocked() && this.reward.isPresent()) {
            player.getInventory().addItems(this.reward.get());
        }
    }

    /**
     * Checks if the enigma has been solved, unlocking the object.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise
     */
    private boolean isUnlocked() {
        return this.enigma.isSolved();
    }
}