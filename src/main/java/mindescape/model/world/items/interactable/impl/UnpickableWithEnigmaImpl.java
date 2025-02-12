package mindescape.model.world.items.interactable.impl;

import java.io.Serializable;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.items.interactable.api.UnpickableWithEnigma;
import mindescape.model.world.player.api.Player;

/**
 * Represents an unpickable object that requires solving an {@link Enigma} to unlock 
 * and may provide a {@link Pickable} reward upon solving.
 * <p>
 * Extends {@link GameObjectImpl} to inherit common properties like position, name, 
 * and dimensions.
 * </p>
 *
 * @see Unpickable
 * @see Pickable
 * @see Enigma
 * @see GameObjectImpl
 */
public class UnpickableWithEnigmaImpl extends GameObjectImpl implements UnpickableWithEnigma {

    private final static long serialVersionUID = 1L;
    private final Pickable reward;
    private final Enigma enigma;

    /**
     * Constructs an unpickable object that requires solving an enigma to unlock.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param enigma     the enigma required to unlock the object
     * @param reward     a pickable item rewarded after solving the enigma
     */
    public UnpickableWithEnigmaImpl(final String name, final Point2D position,
                                    final Dimensions dimensions, final Enigma enigma,
                                    final Pickable reward) {
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
        if (this.isUnlocked() 
            && this.reward != null) {
            player.getInventory().addItems(this.reward);
        }
    }

    /**
     * Checks if the item is unlocked.
     *
     * @return true if the enigma associated with this item is solved, false otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return this.enigma.isSolved();
    }

    /**
     * Retrieves the enigma associated with this unpickable object.
     *
     * @return the {@link Enigma} linked to this object
     */
    @Override
    public Enigma getEnigma() {
        return this.enigma; 
    }
}
