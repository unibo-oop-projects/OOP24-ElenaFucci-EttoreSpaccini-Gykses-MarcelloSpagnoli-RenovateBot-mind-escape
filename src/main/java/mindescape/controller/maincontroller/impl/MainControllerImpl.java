package mindescape.controller.maincontroller.impl;

import java.util.Map;

import javax.swing.SwingUtilities;

import mindescape.controller.api.Controller;
import mindescape.controller.api.LoopController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.view.impl.MainViewImpl;
import mindescape.view.api.MainView;


public class MainControllerImpl implements MainController {
    
    private Controller currentController;
    private final Map<String, Controller> controllers;
    private final MainView mainView;

    public MainControllerImpl() {
        System.out.println("Initializing MainControllerImpl...");
        this.currentController = new MenuController(this);
        this.mainView = new MainViewImpl(this);
        this.controllers = Map.of(currentController.getName(), currentController);
        this.setController(currentController);
        System.out.println("MainControllerImpl initialized.");
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
        SwingUtilities.invokeLater(() -> {
            this.mainView.show();
        });
    }

    @Override
    public Controller getController() {
        return this.currentController;
    }

    @Override
    public Controller findController(final String name) {
        return this.controllers.get(name);
    }

    @Override
    public void switchToGame() {
        this.setController(this.findController("World"));
    }
}
