package mindescape.model.enigma.enigmapassword.impl;

import java.io.Serializable;
import mindescape.model.enigma.enigmapassword.api.EnigmaPasswordModel;

/**
 * Represents a password-based enigma required to unlock a door in the first room.
 * <p>
 * The enigma is solved by providing the correct password.
 * </p>
 */
public final class EnigmaPasswordModelImpl implements EnigmaPasswordModel, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Indicates whether the enigma has been solved.
     */
    private boolean solved;
    private final String name;
    private final String password;

    /**
     * Constructs a new {@code EnigmaPasswordModelImpl} with an initial unsolved state.
     *
     * @param name     the name of the enigma
     * @param password the password required to solve the enigma
     */
    public EnigmaPasswordModelImpl(final String name, final String password) {
        this.solved = false;
        this.name = name;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Object value) {
        if (value instanceof String && value.equals(this.password)) {
            this.solved = true;
        }
        return this.isSolved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }
}
