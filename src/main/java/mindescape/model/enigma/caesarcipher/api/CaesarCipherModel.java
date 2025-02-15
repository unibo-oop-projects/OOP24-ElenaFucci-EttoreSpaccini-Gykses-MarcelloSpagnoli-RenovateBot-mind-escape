package mindescape.model.enigma.caesarcipher.api;

import mindescape.model.enigma.api.Enigma;

/**
 * The {@code CaesarCipherModel} interface extends {@code Enigma} to define the behavior of a
 * Caesar Cipher enigma model.
 */
public interface CaesarCipherModel extends Enigma {

    /**
     * Decrypts a predefined text using the Caesar Cipher algorithm with the specified shift value.
     *
     * @param shift the number of positions to shift each letter in the text
     * @return the decrypted text
     */
    String decrypt(int shift); 

    /**
     * Retrieves the encrypted version of the text used in the enigma.
     *
     * @return the encrypted text
     */
    String getEncryptedText(); 
}
