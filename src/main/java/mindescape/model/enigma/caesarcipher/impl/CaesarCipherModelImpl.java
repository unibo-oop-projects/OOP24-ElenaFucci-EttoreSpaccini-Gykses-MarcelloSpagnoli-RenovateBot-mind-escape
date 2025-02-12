package mindescape.model.enigma.caesarcipher.impl;

import java.io.Serializable;

import mindescape.model.enigma.caesarcipher.api.CaesarCipherModel;

/**
 * The {@code CaesarCipherModelImpl} class implements {@code CaesarCipherModel} to provide encryption
 * and decryption functionalities for the Caesar Cipher enigma.
 */
public class CaesarCipherModelImpl implements CaesarCipherModel, Serializable {

    private final static transient String ENCRYPTED_TEXT = "kr ilqlwr vwr fliudulr gl phugd"; 

    private final transient String decryptedText; 
    private final transient String name; 
    private boolean solved; 

    /**
     * Constructs a {@code CaesarCipherModelImpl} with a name and a given shift value.
     *
     * @param name  the name of the enigma
     * @param shift the shift value used for decryption
     */
    public CaesarCipherModelImpl(final String name, final int shift) {
        this.name = name;  
        this.solved = false; 
        this.decryptedText = this.decrypt(shift); 
    }

    /**
     * Checks if the enigma has been solved.
     *
     * @return {@code true} if solved, {@code false} otherwise
     */
    @Override
    public boolean isSolved() {
        return this.solved; 
    }

    /**
     * Attempts to solve the enigma by comparing the provided value with the decrypted text.
     *
     * @param value the user-provided solution
     * @return {@code true} if the solution is correct, {@code false} otherwise
     */
    @Override
    public boolean hit(final Object value) {
        if (value instanceof String && value.equals(this.decryptedText)) {
            this.solved = true;
        }
        return this.solved;
    }

    /**
     * Retrieves the name of the enigma.
     *
     * @return the enigma name as a string
     */
    @Override
    public String getName() {
        return this.name; 
    }

    /**
     * Encrypts the predefined text using the given shift value.
     *
     * @param shift the number of positions to shift each letter
     * @return the encrypted text
     */
    @Override
    public String decrypt(final int shift) {
        final StringBuilder result = new StringBuilder();
        for (final char c : ENCRYPTED_TEXT.toCharArray()) {
            if (Character.isLetter(c)) {
                final char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base - (shift%26) + 26) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Retrieves the encrypted text.
     *
     * @return the encrypted text as a string
     */
    @Override
    public String getEncryptedText() {
        return ENCRYPTED_TEXT; 
    }
}
