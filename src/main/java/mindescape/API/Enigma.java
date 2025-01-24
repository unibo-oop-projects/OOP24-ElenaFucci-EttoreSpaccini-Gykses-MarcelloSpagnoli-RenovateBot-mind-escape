package mindescape.api;

/**
 * Represents an enigma that can be solved.
 */
public interface Enigma {

    /**
     * Checks if the enigma has been solved.
     *
     * @return true if the enigma is solved, false otherwise.
     */
    boolean isSolved();

    /**
     * Initializes the enigma. This method should set up any necessary state or 
     * configurations required for the enigma to function properly.
     */
    void init();
}
