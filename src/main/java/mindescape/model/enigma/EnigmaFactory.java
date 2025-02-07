package mindescape.model.enigma;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;

/**
 * This class is used to instatiate the enigmas.
 */
public class EnigmaFactory {
    //aggiungere man mano
    public Enigma getEnigma(String name) {
        switch (name) {
            case "EnigmaDoorFirstRoom":
                return new EnigmaPasswordModelImpl("EnigmaDoorFirstRoom", "Sergio Mattarella");
            case "Chest":
                return new EnigmaPasswordModelImpl("Chest", "1213");
            default:
                return null;
        }
    }
}
