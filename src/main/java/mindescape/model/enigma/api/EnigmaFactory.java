package mindescape.model.enigma.api;

import java.util.Set;

/**
 * Factory interface for creating Enigma instances.
 * An Enigma is a puzzle or challenge that needs to be solved.
 * This factory provides a method to retrieve an Enigma instance based on its name.
 */
public interface EnigmaFactory {

    /**
     * Enum representing different types of Enigmas.
     * Each enum constant has a corresponding name.
     */
    enum EnigmaType {
        ENIGMA_FIRST_DOOR("EnigmaFirstDoor"),
        DRAWER("Drawer"),
        CAESAR_CIPHER("CaesarCipher"),
        PUZZLE("Puzzle"),
        CALENDAR("Calendar"),
        WARDROBE("Wardrobe");

        private final String name;

        /**
         * Constructor for EnigmaType.
         *
         * @param name the name of the enigma type
         */
        EnigmaType(final String name) {
            this.name = name;
        }

        /**
         * Gets the name of the enigma type.
         *
         * @return the name of the enigma type
         */
        public String getName() {
            return this.name;
        }

        /**
         * Retrieves the EnigmaType based on the provided name.
         *
         * @param name the name of the enigma type
         * @return the corresponding EnigmaType
         * @throws IllegalArgumentException if the enigma type is not found
         */
        public static EnigmaType getEnigma(final String name) {
            for (final var enigmaType : EnigmaType.values()) {
                if (enigmaType.getName().equals(name)) {
                    return enigmaType;
                }
            }
            throw new IllegalArgumentException("Unexpected enigma: " + name);
        }
    }

    /**
     * Retrieves an Enigma instance based on its name.
     *
     * @param name the name of the enigma
     * @return the corresponding Enigma instance
     * @throws IllegalArgumentException if the enigma is not found
     */
    Enigma getEnigma(final String name) throws IllegalArgumentException;

    /**
     * Retrieves all available Enigma instances.
     *
     * @return a set of all Enigma instances
     */
    Set<Enigma> getEnigmas();
}