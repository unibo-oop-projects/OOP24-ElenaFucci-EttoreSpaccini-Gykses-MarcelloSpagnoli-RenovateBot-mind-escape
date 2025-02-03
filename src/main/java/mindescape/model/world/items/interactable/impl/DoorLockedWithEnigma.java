package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.player.api.Player;

/**
 * Represents a door that requires solving an {@link Enigma} to unlock.
 * <p>
 * This class acts as a decorator for a base {@link Door}, adding an enigma-based
 * locking mechanism. It extends {@link GameObjectImpl} to inherit common
 * properties like position, name, and dimensions.
 * </p>
 *
 * @see Door
 * @see Enigma
 * @see GameObjectImpl
 */
public class DoorLockedWithEnigma extends GameObjectImpl implements Door {

    private final Door baseDoor;
    private final Enigma enigma;

    /**
     * Constructs a door that is locked with an enigma.
     *
     * @param baseDoor the base door to decorate with the enigma lock
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
     * The door can only be used if the enigma is solved. If unlocked, the
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
    private boolean isUnlocked() {
        return this.enigma.isSolved();
    }
}
