package mindescape.model.enigma.api;

public interface Enigma {

    /**
     * Checks if the enigma has been solved.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise.
     */
    boolean isSolved();

    /**
     * Attempts to solve the enigma using the provided value.
     *
     * @param value the value used to attempt solving the enigma
     * @return {@code true} if the provided value solves the enigma, {@code false} otherwise.
     */
    boolean hit(Object value); 
}
