package mindescape.controller.maincontroller.impl;

import java.util.Objects;
import javax.swing.SwingUtilities;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api. LoopController;
import mindescape.controller.core.impl.ControllerBuilderImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.saveload.util.SaveManager;
import mindescape.model.world.api.World;
import mindescape.view.api.MainView;
import mindescape.view.main.MainViewImpl;

/**
 * Implementation of the MainController interface.
 */
public class MainControllerImpl implements MainController {
    private Controller currentController;
    private ControllerMap controllerMap;
    private final MainView mainView;
    private final ControllerBuilder controllerBuilder;
    private String playerName;

    public MainControllerImpl() {
        this.mainView = new MainViewImpl(this);
        this.controllerBuilder = new ControllerBuilderImpl(this);
        this.onStart();
    }

    @Override
    public void setController(final ControllerName controllerName, Enigma enigma) {
        //TODO: check DRY
        // Quit the current controller if it is a LoopController
        if (this.currentController instanceof LoopController) {
            ((LoopController) this.currentController).quit();
        }

        // if the controller is already in the map, set it as the current controller 
        if (this.controllerMap.containsController(controllerName)) {
            this.currentController = this.controllerMap.findController(controllerName);
        } else { // otherwise, build the controller and set it as the current controller
            this.controllerMap = this.buildController(controllerName, enigma);
            this.currentController = this.controllerMap.findController(controllerName);
        }

        this.mainView.setPanel(this.currentController.getPanel());
        this.currentController.start();
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(this.mainView::show);
    }

    @Override
    public Controller getController() {
        return this.currentController;
    }

    @Override
    public void winning() {
        this.mainView.won();
        this.controllerMap.clear();
        this.onStart();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void save() throws IllegalStateException, NullPointerException {
        final var world = this.controllerMap.findController(ControllerName.WORLD).getModel();
        Objects.requireNonNull(world, "World is null.");
        if (world instanceof World) {
            SaveManager.saveGameStatus((World) world);
            this.exit();
        } else {
            throw new IllegalStateException("The current controller is not a World controller.");
        }
    }

    @Override
    public void loadGame(final World world) {
        this.controllerBuilder.buildExistingWorld(world);
        this.controllerMap = this.controllerBuilder.getResult();
        this.setController(ControllerName.WORLD, null);
    }

    private void onStart() {
        this.controllerBuilder.buildMenu();
        this.controllerMap = this.controllerBuilder.getResult();
        this.setController(ControllerName.MENU, null);
    }

    private ControllerMap buildController(final ControllerName name, Enigma enigma) {
        if (!this.controllerMap.containsController(name)) {
            switch (name) {
                case MENU:
                    this.controllerBuilder.buildMenu();
                    break;
                case INVENTORY:
                    this.controllerBuilder.buildInventory((World) this.controllerMap.findController(ControllerName.WORLD).getModel());
                    break;
                case LOAD:
                    this.controllerBuilder.buildLoad();
                    break;
                case CAESAR_CIPHER:
                    this.controllerBuilder.buildComputer(enigma);
                    break;
                case WORLD:
                    this.controllerBuilder.buildNewWorld(this.playerName);
                    break;
                case WARDROBE:
                    this.controllerBuilder.buildWardrobe(enigma);
                    break;
                case CALENDAR:
                    this.controllerBuilder.buildCalendar(enigma);
                    break;
                case PUZZLE:
                    this.controllerBuilder.buildPuzzle(enigma);
                    break;
                case DRAWER:
                    this.controllerBuilder.buildDrawer(enigma);
                    break;
                case ENIGMA_FIRST_DOOR:
                    this.controllerBuilder.buildEnigmaFirstDoor(enigma);
                    break;
                case GUIDE:
                    this.controllerBuilder.buildGuide();
                    break;
                default:
                    throw new IllegalArgumentException("Controller not found.");
            }
        }
        return this.controllerBuilder.getResult();
    }

    @Override
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

}
