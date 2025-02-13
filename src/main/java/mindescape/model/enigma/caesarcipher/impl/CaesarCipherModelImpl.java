package mindescape.model.enigma.caesarcipher.impl;

import java.io.Serializable;

import mindescape.model.enigma.caesarcipher.api.CaesarCipherModel;

/**
 * The {@code CaesarCipherModelImpl} class implements {@code CaesarCipherModel} to provide encryption
 * and decryption functionalities for the Caesar Cipher enigma.
 */
public class CaesarCipherModelImpl implements CaesarCipherModel, Serializable {

    private static final String ENCRYPTED_TEXT = 
        "nhbfrgh wr wkh forvhw lv \"reolylrq\". Frqwdlqv lpsruwdqw lqirupdwlrqv."; 
    private static final long serialVersionUID = 1L;
    private static final int LETTERS_IN_ALPHABET = 26;

    private final int shift;
    private final String name;
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
        this.shift = shift;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean isSolved() {
        return this.solved; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Object value) {
        if (value instanceof String && value.equals(this.decrypt(this.shift))) {
            this.solved = true;
        }
        return this.solved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String decrypt(final int shift) {
        final StringBuilder result = new StringBuilder();
        for (final char c : ENCRYPTED_TEXT.toCharArray()) {
            if (Character.isLetter(c)) {
                final char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append(
                    (char) ((c - base - (shift % LETTERS_IN_ALPHABET) + LETTERS_IN_ALPHABET) % LETTERS_IN_ALPHABET + base)
                );
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEncryptedText() {
        return ENCRYPTED_TEXT; 
    }
}
