package mindescape.model.enigma.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;

/**
 * Factory class that creates the enigma objects.
 */
public class EnigmaFactoryImpl implements EnigmaFactory {

    @Override
    public Enigma getEnigma(final String name) throws IllegalArgumentException {
        var enigmaName = EnigmaType.getEnigma(name);
        return (Enigma) switch (enigmaName) {
            case ENIGMA_FIRST_DOOR -> new EnigmaPasswordModelImpl(name, "Sergio Mattarella");
            case DRAWER -> new EnigmaPasswordModelImpl(name, "1213");
            case CAESAR_CIPHER -> new CaesarCipherModelImpl(name, 3);
            case CALENDAR -> null;
            case PUZZLE -> {
                try {
                    BufferedImage img = ImageIO.read(new File("src/resources/puzzle/Presidente_Sergio_Mattarella.jpg"));
                    yield new EnigmaPuzzleModelImpl(5, 5, img, name);
                } catch (IOException e) {
                    e.printStackTrace();
                    yield null;
                }
            }
            default -> throw new IllegalArgumentException("Unexpected enigma: " + name);
        };
    }
}
