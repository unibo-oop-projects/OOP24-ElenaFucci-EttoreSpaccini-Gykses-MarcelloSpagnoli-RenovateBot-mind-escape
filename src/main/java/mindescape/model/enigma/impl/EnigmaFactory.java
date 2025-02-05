package mindescape.model.enigma.impl;

import mindescape.model.enigma.api.Enigma;

/**
 * This class is used to instatiate the enigmas.
 */
public class EnigmaFactory {
    //aggiungere man mano
    public Enigma getEnigma(String name) {
        switch (name) {
            case "EnigmaDoorFirstRoom":
                return new EnigmaDoorFirstRoomImpl(name);
            default:
                return null;
        }
    }
}
