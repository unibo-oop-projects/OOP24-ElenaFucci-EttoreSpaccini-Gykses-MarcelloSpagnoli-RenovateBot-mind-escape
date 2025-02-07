package mindescape.model.enigma.api;

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
        CAESAR_CIPHER("CaesarCipher");

        private final String name;
        
        EnigmaType(final String name) {
            this.name = name;
        }
       
        public String getName() {
            return this.name;
        }

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
     * Retrieves an Enigma instance based on the provided name.
     *
     * @param name the name of the Enigma to retrieve
     * @return the Enigma instance corresponding to the specified name
     */
    Enigma getEnigma(final String name) throws IllegalArgumentException;
    
}
