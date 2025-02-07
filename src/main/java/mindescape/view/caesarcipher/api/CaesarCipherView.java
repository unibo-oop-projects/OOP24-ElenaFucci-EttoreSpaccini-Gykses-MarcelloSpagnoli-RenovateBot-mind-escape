package mindescape.view.caesarcipher.api;

import mindescape.view.api.View;

/**
 * The {@code CaesarCipherView} interface extends {@code View} to define methods for displaying
 * results in the Caesar Cipher enigma view.
 */
public interface CaesarCipherView extends View {

    /**
     * Displays the result of the decryption attempt.
     *
     * @param result the decrypted text or an error message
     */
    void showResult(String result); 
}
