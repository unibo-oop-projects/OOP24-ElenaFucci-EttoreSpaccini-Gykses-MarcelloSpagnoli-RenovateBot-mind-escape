package mindescape.model.enigma.impl;

import mindescape.model.enigma.enigmapassword.EnigmaPasswordModel;

/**
 * Represents the enigma required to unlock the door in the first room.
 * <p>
 * The enigma is solved by providing the correct password.
 * </p>
 *
 * @see EnigmaDoorFirstRoom
 */
public class EnigmaPasswordModelImpl implements EnigmaPasswordModel { 

    /**
     * Indicates whether the enigma has been solved.
     */
    private boolean solved;
    private final String name; 
    private final String password; 

    /**
     * Constructs a new {@code EnigmaDoorFirstRoom} with an unsolved state.
     *
     * @param name the name of the enigma
     */
    public EnigmaPasswordModelImpl(final String name, final String password) {
        this.solved = false;
        this.name = name; 
        this.password = password; 
    }

    /**
     * Checks if the enigma has been solved.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise
     */
    @Override
    public boolean isSolved() {
        return this.solved; 
    }

    /**
     * Attempts to solve the enigma using the provided value.
     * <p>
     * The enigma is solved if the provided value matches the correct password.
     * </p>
     *
     * @param value the value used to attempt solving the enigma
     * @return {@code true} if the provided value solves the enigma, {@code false} otherwise
     */
    @Override
    public boolean hit(Object value) {
        if (value instanceof String && value.equals(this.password)) {
            this.solved = true; 
        }
        return this.isSolved(); 
    }

    /**
     * Retrieves the name of the enigma.
     *
     * @return a string representing the enigma's name
     */
    @Override
    public String getName() {
        return this.name; 
    }
}