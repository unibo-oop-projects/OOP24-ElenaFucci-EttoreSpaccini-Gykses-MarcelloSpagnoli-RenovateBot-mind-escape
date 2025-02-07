package mindescape.view.enigmapassword.api;

import mindescape.view.api.View;

/**
 * The {@code EnigmaPasswordView} interface extends {@code View} to define methods for displaying
 * the result of a password-based enigma.
 */
public interface EnigmaPasswordView extends View {

    /**
     * Displays whether the enigma has been solved.
     *
     * @param solved {@code true} if the enigma is solved, {@code false} otherwise
     */
    void showResult(boolean solved); 
}
