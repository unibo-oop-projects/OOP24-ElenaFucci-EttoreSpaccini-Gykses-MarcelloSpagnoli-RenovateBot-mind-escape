package mindescape.controller.maincontroller.impl;

import javax.swing.SwingUtilities;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.api.ControllerMap.ControllerName;
import mindescape.controller.core.impl.ControllerBuilderImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.impl.MainViewImpl;
import mindescape.view.api.MainView;

public class MainControllerImpl implements MainController {
    
    private Controller currentController;
    private final ControllerMap controllerMap;
    private final MainView mainView;

    public MainControllerImpl() {
        this.mainView = new MainViewImpl(this);
        var controllerBuilder = new ControllerBuilderImpl(this);
        controllerBuilder.buildMenu();
        this.controllerMap = controllerBuilder.getResult();
        this.setController(this.findController("Menu"));
    }

    @Override
    public void setController(final Controller controller) {
        // Quit the current controller if it is a LoopController
        if (this.currentController instanceof LoopController) {
            ((LoopController) this.currentController).quit();
        }
        this.currentController = controller;
        // this.currentController.start();
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
        return this.controllerMap.findController(ControllerName.fromString(name));
    }

    @Override
    public void switchToGame() {
        this.setController(this.findController("World"));
    }

    @Override
    public void switchToMenu() {
        this.setController(this.findController("Menu"));
    }

    @Override
    public void winning() {
        this.mainView.won();
    }

    @Override
    public void switchToInventory() {
        this.setController(this.findController("Inventory"));
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void save() {
        
        this.exit();
    }

}
