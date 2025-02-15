package mindescape.controller.worldcontroller.impl;

import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.KeyMapper;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.WorldView;
import mindescape.view.world.WorldViewImpl;

/**
 * The controller for the world.
 */
public final class WorldController implements LoopController {

    private final World world;
    private final WorldView worldView;
    private final MainController mainController;
    private final Logger logger = Logger.getLogger(WorldController.class.getName());
    private boolean running = true;
    private static final int FPS = 60; 
    private static final long TIME = 1_000; // 1 second in milliseconds
    private final Map<Integer, UserInput> keyMapper = KeyMapper.getKeyMap();

    /**
     * Constructs a new WorldController with the specified world and the reference to the main controller.
     *
     * @param world the world model to be controlled
     * @param mainController the main controller managing the overall application
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The main controller needs to be exposed to the caller")
    public WorldController(final World world, final MainController mainController) {
        this.world = world;
        this.worldView = new WorldViewImpl(world.getCurrentRoom());
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        switch ((UserInput) input) {
            case UP -> this.world.movePlayer(Movement.UP);
            case DOWN -> this.world.movePlayer(Movement.DOWN);
            case LEFT -> this.world.movePlayer(Movement.LEFT);
            case RIGHT -> this.world.movePlayer(Movement.RIGHT);
            case INTERACT -> interactAction();
            case INVENTORY ->  inventoryAction();
            default -> throw new IllegalArgumentException("Unknown input: " + input);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.running = false;
    }

    /*
     * The loop that runs the game.
     */
    private final class Loop extends Thread {
        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            final long frameTime = TIME / FPS;

            while (running) {
                final long startTime = System.currentTimeMillis();

                if (world.hasWon()) {
                    mainController.winning();
                    quit();
                }

                final long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime < frameTime) {
                    try {
                        sleep(frameTime - elapsedTime);
                    } catch (InterruptedException e) {
                        logger.fine(e.getMessage());
                    }
                }

                movePlayerIfKeyPressed();
                worldView.draw(world.getCurrentRoom());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ControllerName.WORLD.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.worldView.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSave() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The model is returned to the caller")
    public Model getModel() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.running = true;
        new Loop().start();
    }

    private void movePlayerIfKeyPressed() {
        for (final Map.Entry<Integer, Boolean> entry : worldView.getKeyState().entrySet()) {
            if (entry.getValue()) {
                handleInput(keyMapper.get(entry.getKey()));
                if (keyMapper.get(entry.getKey()) == UserInput.INTERACT
                || keyMapper.get(entry.getKey()) == UserInput.INVENTORY) {
                    break;
                }
            }
        }
    }

    private void interactAction() {
        worldView.clearInput();
        this.world.letPlayerInteract().ifPresent(enigma -> 
        this.mainController.setController(ControllerName.fromString(enigma.getName()), Optional.of(enigma)));
    }

    private void inventoryAction() {
        worldView.clearInput();
        this.mainController.setController(ControllerName.INVENTORY, Optional.empty());
    }
}
