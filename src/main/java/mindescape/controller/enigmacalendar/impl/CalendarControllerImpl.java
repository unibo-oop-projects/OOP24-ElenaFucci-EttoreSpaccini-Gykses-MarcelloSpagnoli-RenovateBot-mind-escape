package mindescape.controller.enigmacalendar.impl;

import javax.swing.JPanel;

import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmacalendar.api.CalendarController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.enigmacalendar.api.CalendarView;
import mindescape.view.enigmacalendar.impl.CalendarViewImpl;

public class CalendarControllerImpl implements CalendarController {
    private final MainController mainController;
    private final CalendarView view;

    public CalendarControllerImpl(MainController mainController) {
        this.mainController = mainController;
        this.view = new CalendarViewImpl();
    }   

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
    }

    @Override
    public String getName() {
        return "Calendar";
    }

    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD);
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public void start() {
    }

}
