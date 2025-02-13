package mindescape.model.enigma.calendar;

import java.io.Serializable;
import mindescape.model.api.Model;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.api.EnigmaFactory.EnigmaType;

/**
 * Class representing the Calendar enigma.
 */
public class Calendar implements Enigma, Serializable, Model {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolved() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Object value) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
       return EnigmaType.CALENDAR.getName();
    }
}
