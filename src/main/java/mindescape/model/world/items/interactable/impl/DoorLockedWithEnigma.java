package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.UnpickableWithEnigma;
import mindescape.model.world.player.api.Player;

/**
 * Represents a door that requires solving an {@link Enigma} to unlock.
 * <p>
 * Acts as a decorator for a base {@link Door}, adding an enigma-based locking 
 * mechanism. Extends {@link GameObjectImpl} to inherit properties like position, 
 * name, and dimensions.
 * </p>
 *
 * @see Door
 * @see Enigma
 * @see GameObjectImpl
 */
public class DoorLockedWithEnigma extends GameObjectImpl implements Door, UnpickableWithEnigma {

    private final Door baseDoor;
    private final Enigma enigma;

    /**
     * Constructs a door locked with an enigma.
     *
     * @param baseDoor the base door decorated with the enigma lock
     * @param enigma   the enigma that must be solved to unlock the door
     */
    public DoorLockedWithEnigma(final Door baseDoor, final Enigma enigma) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.enigma = enigma;
        this.baseDoor = baseDoor;
    }

    /**
     * Defines the interaction behavior when the player interacts with the door.
     * <p>
     * The door can be used only if the enigma is solved. If unlocked, the 
     * interaction is delegated to the base door.
     * </p>
     *
     * @param player the player interacting with the door
     */
    @Override
    public void onAction(final Player player) {
        if (this.isUnlocked()) {
            this.baseDoor.onAction(player);
        }
    }

    /**
     * Checks if the door is unlocked.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise
     */
    @Override
    public boolean isUnlocked() {
        return this.enigma.isSolved();
    }

    /**
     * Retrieves the enigma associated with this door.
     *
     * @return the {@link Enigma} linked to this door
     */
    @Override
    public Enigma getEnigma() {
        return this.enigma; 
    }
}
