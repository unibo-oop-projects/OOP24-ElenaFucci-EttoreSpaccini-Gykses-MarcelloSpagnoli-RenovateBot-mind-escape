package mindescape.controller.worldcontroller.impl;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.WorldView;
import mindescape.view.world.WorldViewImpl;
import java.awt.event.KeyEvent;

/**
 * The controller for the world.
 */
public class WorldController implements LoopController {

    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT,
        KeyEvent.VK_E, UserInput.INTERACT,
        KeyEvent.VK_I, UserInput.INVENTORY
    );

    private final World world;
    private final WorldView worldView;
    private final MainController mainController;
    private boolean running = true;
    private static final int FPS = 60; 
    private static final long TIME = 1_000; // 1 second in milliseconds

    /**
     * Constructs a new WorldController with the specified world and the reference to the main controller.
     *
     * @param world the world model to be controlled
     * @param mainController the main controller managing the overall application
     */
    public WorldController(final World world, final MainController mainController) {
        this.world = world;
        this.worldView = new WorldViewImpl(this, world.getCurrentRoom());
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

    /**
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
                        e.printStackTrace();
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
        for (final Map.Entry<Integer, Boolean> entry : new HashMap<>(worldView.getKeyState()).entrySet()) {
            if (entry.getValue()) {
                handleInput(KEY_MAPPER.get(entry.getKey()));
            }
        }
    }

    private void interactAction() {
        worldView.clearInput();
        this.world.letPlayerInteract().ifPresent(enigma -> 
        this.mainController.setController(ControllerName.fromString(enigma.getName()), enigma));
    }

    private void inventoryAction() {
        worldView.clearInput();
        this.mainController.setController(ControllerName.INVENTORY, null);
    }
}
