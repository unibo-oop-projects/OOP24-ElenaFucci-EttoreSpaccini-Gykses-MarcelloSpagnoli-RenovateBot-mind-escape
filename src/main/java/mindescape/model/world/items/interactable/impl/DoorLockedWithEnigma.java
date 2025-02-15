package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.items.interactable.api.AbstractDoorDecorator;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.UnpickableWithEnigma;

/**
 * A concrete implementation of {@link AbstractDoorDecorator} that locks a door with an {@link Enigma}.
 * The door remains locked until the enigma is solved.
 */
public final class DoorLockedWithEnigma extends AbstractDoorDecorator implements UnpickableWithEnigma {

    private static final long serialVersionUID = 1L;

    /**
     * The enigma that must be solved to unlock the door.
     */
    private final Enigma enigma;

    /**
     * Constructs a {@code DoorLockedWithEnigma} by decorating an existing {@link Door} and associating it with an {@link Enigma}.
     *
     * @param baseDoor the base door to be decorated.
     * @param enigma the enigma that must be solved to unlock the door.
     */
    public DoorLockedWithEnigma(final Door baseDoor, final Enigma enigma) {
        super(baseDoor);
        this.enigma = enigma;
    }

    /**
     * Determines whether the door is unlocked.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return this.enigma.isSolved();
    }

    /**
     * Retrieves the enigma associated with this locked door.
     *
     * @return the {@link Enigma} linked to this door.
     */
    @Override
    public Enigma getEnigma() {
       return this.enigma;
    }
}
