package mindescape.controller.maincontroller.impl;

import java.util.Map;
import mindescape.controller.api.Controller;
import mindescape.controller.maincontroller.api.ClickableController;
import mindescape.controller.maincontroller.api.LoopController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.view.impl.MainViewImpl;
import mindescape.view.api.MainView;


public class MainControllerImpl implements MainController {
    
    private Controller currentController;
    private final Map<String, Controller> controllers;
    private final MainView mainView;

    public MainControllerImpl() {
        this.currentController = new MenuController(this);
        this.mainView = new MainViewImpl(this);
        //TODO: add map of controllers
        this.controllers = Map.of(currentController.getName(), currentController);
        this.setController(currentController);
    }

    @Override
    public void setController(final Controller controller) {
        // Quit the current controller if it is a LoopController, if so the loop will be stopped
        if (this.currentController instanceof LoopController) {
            ((LoopController) this.currentController).quit();
        }
        this.currentController = controller;
        this.mainView.setPanel(controller.getPanel());
    }

    @Override
    public void start() {
        this.mainView.show();
    }

    @Override
    public Controller getController() {
        return this.currentController;
    }

    @Override
    public Controller findController(final String name) {
        return this.controllers.get(name);
    }
}
