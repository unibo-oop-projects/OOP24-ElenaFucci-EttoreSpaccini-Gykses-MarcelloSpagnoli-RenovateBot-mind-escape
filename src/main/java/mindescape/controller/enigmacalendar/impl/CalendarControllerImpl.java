package mindescape.controller.enigmacalendar.impl;

import javax.swing.JPanel;

import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmacalendar.api.CalendarController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.enigmacalendar.api.CalendarView;
import mindescape.view.enigmacalendar.impl.CalendarViewImpl;

/**
 * Implementation of the CalendarController interface.
 * This class is responsible for handling the calendar-related operations
 * and interactions with the CalendarView.
 */
public class CalendarControllerImpl implements CalendarController {
    private final MainController mainController;
    private final CalendarView view;

    /**
     * Constructs a new CalendarControllerImpl with the specified MainController.
     *
     * @param mainController the main controller to be used by this calendar controller
     */
    public CalendarControllerImpl(MainController mainController) {
        this.mainController = mainController;
        this.view = new CalendarViewImpl(this);
    }   

    /**
     * Handles the input provided to the calendar controller.
     *
     * @param input the input object to be processed
     * @throws IllegalArgumentException if the input is invalid
     * @throws NullPointerException if the input is null
     */
    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
    }

    /**
     * Returns the name of the controller.
     *
     * @return a string representing the name of the controller, which is "Calendar".
     */
    @Override
    public String getName() {
        return ControllerName.CALENDAR.getName();
    }

    /**
     * Retrieves the JPanel associated with this controller.
     *
     * @return the JPanel instance managed by this controller.
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    /**
     * Quits the current controller and sets the main controller to the WORLD controller.
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD, null);
    }

    /**
     * Determines if the current state allows saving.
     *
     * @return true if saving is allowed, false otherwise.
     */
    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * Retrieves the model associated with this controller.
     *
     * @return the model instance, or {@code null} if no model is set.
     */
    @Override
    public Model getModel() {
        return null;
    }

    /**
     * Starts the calendar controller.
     * This method is intended to initialize and begin any processes
     * or operations managed by the calendar controller.
     */
    @Override
    public void start() {
    }
}
