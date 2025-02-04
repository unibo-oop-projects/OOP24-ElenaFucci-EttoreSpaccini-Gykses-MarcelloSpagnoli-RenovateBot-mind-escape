package mindescape.controller.maincontroller.impl;

import mindescape.controller.api.Controller;
import mindescape.controller.maincontroller.api.MainController;

public class MainControllerImpl implements MainController {
    private Controller currentController;
    private boolean running = true;


    public MainControllerImpl(final Controller currentController) {
        this.currentController = currentController;
    }

    @Override
    public void setController(final Controller controller) {
        this.currentController = controller;
    }

    @Override
    public void start() {
        while (running) {
            
        }
        this.currentController.loop();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public Controller getController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getController'");
    }

}
