package mindescape.model.enigma.calendar;

import java.io.Serializable;
import mindescape.controller.core.api.ControllerName;
import mindescape.model.enigma.api.Enigma;

/**
 * Class representing the Calendar enigma.
 */
public class Calendar implements Enigma, Serializable {

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
       return ControllerName.CALENDAR.getName();
    }
}
