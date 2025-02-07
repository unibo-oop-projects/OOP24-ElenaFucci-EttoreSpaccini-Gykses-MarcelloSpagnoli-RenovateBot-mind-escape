package mindescape.controller.caesarcipher.api;

import mindescape.controller.core.api.ClickableController;

/**
 * The {@code CaesarCipherController} interface extends {@code ClickableController} to 
 * manage the logic of the Caesar Cipher enigma.
 */
public interface CaesarCipherController extends ClickableController {

    /**
     * Retrieves the encrypted text for the enigma.
     *
     * @return the encrypted text as a {@code String}
     */
    String getEncryptedText(); 
}
