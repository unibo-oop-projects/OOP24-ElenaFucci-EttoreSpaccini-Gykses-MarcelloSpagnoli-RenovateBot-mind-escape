package mindescape.controller.maincontroller.impl;

import javax.swing.SwingUtilities;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.impl.ControllerBuilderImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.MainView;
import mindescape.view.main.MainViewImpl;

/**
 * Implementation of the MainController interface.
 */
public class MainControllerImpl implements MainController {
    
    private Controller currentController;
    private final ControllerMap controllerMap;
    private final MainView mainView;

    public MainControllerImpl() {
        this.mainView = new MainViewImpl(this);
        var controllerBuilder = new ControllerBuilderImpl(this);
        controllerBuilder.buildMenu();
        controllerBuilder.buildNewWorld();
        this.controllerMap = controllerBuilder.getResult();
        System.out.println(this.controllerMap);
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
        this.setController(this.controllerMap.findController(ControllerName.WORLD));
    }

    @Override
    public void switchToMenu() {
        this.setController(this.controllerMap.findController(ControllerName.MENU));
    }

    @Override
    public void winning() {
        this.mainView.won();
    }

    @Override
    public void switchToInventory() {
        this.setController(this.controllerMap.findController(ControllerName.INVENTORY));
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void save() {
        // TODO: Implement save method
        // SaveManager.saveGameStatus(this.controllerMap.findController(ControllerName.WORLD).getWorld());
        this.exit();
    }
}
