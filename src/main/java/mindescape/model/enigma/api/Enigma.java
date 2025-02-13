package mindescape.model.enigma.api;

/**
 * Represents an enigma (puzzle) that can be solved within the game.
 * <p>
 * Classes implementing this interface should define the logic for checking 
 * if the enigma is solved, attempting to solve it, and retrieving its name.
 * </p>
 */
public interface Enigma {

    /**
     * Checks if the enigma has been solved.
     *
     * @return {@code true} if the enigma is solved, {@code false} otherwise
     */
    boolean isSolved();

    /**
     * Attempts to solve the enigma using the provided value.
     *
     * @param value the value used to attempt solving the enigma
     * @return {@code true} if the provided value solves the enigma, {@code false} otherwise
     */
    boolean hit(Object value); 

    /**
     * Retrieves the name of the enigma.
     *
     * @return a string representing the enigma's name
     */
    String getName();
}
