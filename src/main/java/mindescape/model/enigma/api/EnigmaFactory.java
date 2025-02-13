package mindescape.model.enigma.api;

/**
 * Factory interface for creating Enigma instances.
 * An Enigma is a puzzle or challenge that needs to be solved.
 * This factory provides a method to retrieve an Enigma instance based on its name.
 */
public interface EnigmaFactory {
    /**
     * Enum representing different types of enigmas.
     */
    enum EnigmaType {
        /**
         * Rappresent the enigma of the first door.
         */
        ENIGMA_FIRST_DOOR("EnigmaFirstDoor"),
        /**
         * Rappresent the enigma of the drawer.
         */
        DRAWER("Drawer"),
        /**
         * Rappresent the enigma of the Caesar Cipher.
         */
        CAESAR_CIPHER("CaesarCipher"),
        /**
         * Rappresent the enigma of the puzzle.
         */
        PUZZLE("Puzzle"),
        /**
         * Rappresent the enigma of the calendar.
         */
        CALENDAR("Calendar"),
        /**
         * Rappresent the enigma of the wardrobe.
         */
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
    Enigma getEnigma(String name) throws IllegalArgumentException;
}
