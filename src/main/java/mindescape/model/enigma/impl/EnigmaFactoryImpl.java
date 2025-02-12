package mindescape.model.enigma.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import mindescape.controller.core.api.ControllerName;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;

/**
 * Factory class that creates the enigma objects.
 */
public class EnigmaFactoryImpl implements EnigmaFactory, Serializable {

    private final Set<Enigma> enigmas;

    public EnigmaFactoryImpl() {
        this.enigmas = new HashSet<>();
    }

    @Override
    public Enigma getEnigma(final String name) throws IllegalArgumentException {
        var enigmaName = EnigmaType.getEnigma(name);
        return switch (enigmaName) {
            case ENIGMA_FIRST_DOOR -> this.addEnigma(new EnigmaPasswordModelImpl(name, "Sergio Mattarella"));
            case DRAWER -> this.addEnigma(new EnigmaPasswordModelImpl(name, "1213"));
            case CAESAR_CIPHER -> this.addEnigma(new CaesarCipherModelImpl(name, 3));
            case WARDROBE -> this.addEnigma(new EnigmaPasswordModelImpl(name, "67845"));
            case CALENDAR -> new Calendar();
            case PUZZLE -> {
                try {
                    //TODO: fix with the class loader
                    BufferedImage img = ImageIO.read(new File("src/resources/puzzle/Presidente_Sergio_Mattarella.jpg"));
                    yield addEnigma(new EnigmaPuzzleModelImpl(4, 4, img, name));
                } catch (IOException e) {
                    e.printStackTrace();
                    yield null;
                }
            }
            default -> throw new IllegalArgumentException("Unexpected enigma: " + name);
        };
    }

    private Enigma addEnigma(final Enigma enigma) {
        this.enigmas.add(enigma);
        return enigma;
    }

    @Override
    public Set<Enigma> getEnigmas() {
        return this.enigmas;
    }

    private class Calendar implements Enigma, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isSolved() {
            return false;
        }

        @Override
        public boolean hit(Object value) {
            return false;
        }

        @Override
        public String getName() {
           return ControllerName.CALENDAR.getName();
        }

    }
}
