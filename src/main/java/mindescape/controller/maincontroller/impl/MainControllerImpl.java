package mindescape.controller.maincontroller.impl;

import java.util.Map;
import mindescape.controller.api.Controller;
import mindescape.controller.maincontroller.api.MainController;

public class MainControllerImpl implements MainController {
    
    private Controller currentController;
    private boolean running = true;
    private final Map<String, Controller> controllers = Map.of();

    public MainControllerImpl(final Controller currentController) {
        this.currentController = currentController;
    }

    @Override
    public void setController(final Controller controller) {
        this.currentController = controller;
    }

    @Override
    public void start() {
        this.currentController.loop();
    }

    @Override
    public void stop() {
       this.running = false;
    }

    @Override
    public Controller getController() {
        return this.currentController;
    }

    @Override
    public Controller findController(String name) {
        return this.controllers.get(name);
    }
}
