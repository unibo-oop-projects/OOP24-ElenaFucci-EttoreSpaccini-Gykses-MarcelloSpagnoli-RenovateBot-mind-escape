package mindescape.controller.maincontroller.impl;

import java.util.Objects;
import java.util.Optional;
import javax.swing.SwingUtilities;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.impl.ControllerBuilderImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.saveload.util.SaveManager;
import mindescape.model.world.api.World;
import mindescape.view.main.api.MainView;
import mindescape.view.main.impl.MainViewImpl;

/**
 * Implementation of the MainController interface.
 */
public final class MainControllerImpl implements MainController {
    private Controller currentController;
    private ControllerMap controllerMap;
    private final MainView mainView;
    private final ControllerBuilder controllerBuilder;
    private String playerName;

    /**
     * Constructor for the MainControllerImpl class.
     */
    public MainControllerImpl() {
        this.mainView = new MainViewImpl(this);
        this.controllerBuilder = new ControllerBuilderImpl(this);
        this.onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final ControllerName controllerName, final Optional<Enigma> enigma) {
        // Quit the current controller if it is a LoopController
        if (this.currentController instanceof LoopController) {
            ((LoopController) this.currentController).quit();
        }

        // if the controller is already in the map, set it as the current controller 
        if (!this.controllerMap.containsController(controllerName)) {
            this.controllerMap = this.buildController(controllerName, enigma.orElse(null));
        }
        this.currentController = this.controllerMap.findController(controllerName);
        this.mainView.setPanel(this.currentController.getPanel());
        this.currentController.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        SwingUtilities.invokeLater(this.mainView::show);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getController() {
        return this.currentController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void winning() {
        this.mainView.won();
        this.controllerMap.clear();
        this.onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        this.controllerMap.clear();
        this.mainView.close();
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        final var world = this.controllerMap.findController(ControllerName.WORLD).getModel();
        Objects.requireNonNull(world, "World is null.");
        if (world instanceof World) {
            SaveManager.saveGameStatus((World) world);
            this.exit();
        } else {
            throw new IllegalStateException("The current controller is not a World controller.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadGame(final World world) {
        this.controllerBuilder.buildExistingWorld(world);
        this.controllerMap = this.controllerBuilder.getResult();
        this.setController(ControllerName.WORLD, Optional.empty());
    }

    /**
     * Setups for the game start.
     */
    private void onStart() {
        this.controllerBuilder.buildMenu();
        this.controllerMap = this.controllerBuilder.getResult();
        this.setController(ControllerName.MENU, Optional.empty());
    }

    /**
     * Builds the controller based on the provided controller name if it is not already in the map.
     *
     * @param name the name of the controller to be built
     * @param enigma the enigma to be set
     * @return the built controller
     */
    private ControllerMap buildController(final ControllerName name, final Enigma enigma) {
        if (!this.controllerMap.containsController(name)) {
            switch (name) {
                case MENU:
                    this.controllerBuilder.buildMenu();
                    break;
                case INVENTORY:
                    this.controllerBuilder.buildInventory(
                        (World) this.controllerMap
                        .findController(ControllerName.WORLD)
                        .getModel()
                    );
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

}
