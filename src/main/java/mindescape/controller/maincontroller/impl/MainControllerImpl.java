package mindescape.controller.maincontroller.impl;

import java.util.Objects;
import java.util.Optional;
import javax.swing.SwingUtilities;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerFactory;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.impl.ControllerFactoryImpl;
import mindescape.controller.core.impl.ControllerMapImpl;
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
    private final ControllerMap controllerMap;
    private final MainView mainView;
    private final ControllerFactory controllerFactory;
    private String playerName;

    /**
     * Constructor for the MainControllerImpl class.
     */
    public MainControllerImpl() {
        this.mainView = new MainViewImpl(this);
        this.controllerFactory = new ControllerFactoryImpl(this);
        this.controllerMap = new ControllerMapImpl();
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
            this.controllerMap.addController(this.buildController(controllerName, enigma.orElse(null)));
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
        this.controllerMap.addController(this.controllerFactory.buildExistingWorld(world));
        this.setController(ControllerName.WORLD, Optional.empty());
    }

    /**
     * Setups for the game start.
     */
    private void onStart() {
        this.controllerMap.addController(this.controllerFactory.buildMenu());
        this.setController(ControllerName.MENU, Optional.empty());
    }

    /**
     * Builds the controller based on the provided controller name if it is not already in the map.
     *
     * @param name the name of the controller to be built
     * @param enigma the enigma to be set
     * @return the built controller
     */
    private Controller buildController(final ControllerName name, final Enigma enigma) {
        return switch (name) {
            case MENU -> this.controllerFactory.buildMenu();
            case INVENTORY -> this.controllerFactory.buildInventory(
                (World) this.controllerMap.findController(ControllerName.WORLD).getModel()
            );
            case LOAD -> this.controllerFactory.buildLoad();
            case CAESAR_CIPHER -> this.controllerFactory.buildComputer(enigma);
            case WORLD -> this.controllerFactory.buildNewWorld(this.playerName);
            case WARDROBE -> this.controllerFactory.buildWardrobe(enigma);
            case CALENDAR -> this.controllerFactory.buildCalendar(enigma);
            case PUZZLE -> this.controllerFactory.buildPuzzle(enigma);
            case DRAWER -> this.controllerFactory.buildDrawer(enigma);
            case ENIGMA_FIRST_DOOR -> this.controllerFactory.buildEnigmaFirstDoor(enigma);
            case GUIDE -> this.controllerFactory.buildGuide();
            default -> throw new IllegalArgumentException("Controller not found.");
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

}
