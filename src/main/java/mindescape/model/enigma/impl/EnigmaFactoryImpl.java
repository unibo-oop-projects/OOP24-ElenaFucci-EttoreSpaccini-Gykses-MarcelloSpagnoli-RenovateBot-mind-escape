package mindescape.model.enigma.impl;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.calendar.Calendar;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;

/**
 * Factory class that creates the enigma objects.
 */
public final class EnigmaFactoryImpl implements EnigmaFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Enigma getEnigma(final String name) {
        final var enigmaName = EnigmaType.getEnigma(name);
        return switch (enigmaName) {
            case ENIGMA_FIRST_DOOR -> new EnigmaPasswordModelImpl(name, "Sergio Mattarella");
            case DRAWER -> new EnigmaPasswordModelImpl(name, "12-13");
            case CAESAR_CIPHER -> new CaesarCipherModelImpl(name, 3);
            case WARDROBE -> new EnigmaPasswordModelImpl(name, "oblivion");
            case CALENDAR -> new Calendar();
            case PUZZLE ->  new EnigmaPuzzleModelImpl(4, 4, name);
            default -> throw new IllegalArgumentException("Unexpected enigma: " + name);
        };
    }
}
