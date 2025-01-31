package mindescape.model.enigma.impl;

import mindescape.model.enigma.api.Enigma;

/**
 * Represents the enigma required to unlock the door in the first room.
 * <p>
 * The enigma is solved by providing the correct password.
 * </p>
 */
public class EnigmaDoorFirstRoom implements Enigma {

    /**
     * The correct password to solve the enigma.
     */
    private static final String PASSWORD = "Sergio Mattarella"; 

    /**
     * Indicates whether the enigma has been solved.
     */
    private boolean solved;

    /**
     * Constructs a new {@code EnigmaDoorFirstRoom} with an unsolved state.
     */
    public EnigmaDoorFirstRoom() {
        this.solved = false; 
    }

    /**
     * Checks if the enigma has been solved.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise.
     */
    @Override
    public boolean isSolved() {
        return this.solved; 
    }

    /**
     * Attempts to solve the enigma using the provided value.
     *
     * @param value the value used to attempt solving the enigma
     * @return {@code true} if the provided value solves the enigma, {@code false} otherwise.
     */
    @Override
    public boolean hit(Object value) {
        if (value instanceof String && value.equals(PASSWORD)) {
            this.solved = true; 
        }
        return this.isSolved(); 
    }
}
