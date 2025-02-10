package mindescape.controller.worldcontroller.impl;

import java.util.Map;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;

import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.WorldView;
import mindescape.view.world.WorldViewImpl;

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
    private final String name = "World"; 
    private static final int FPS = 60; 
    private static final long TIME = 1_000; // 1 second in milliseconds

    /**
     * Constructs a new WorldController with the specified world, world view, and the reference to the main controller.
     *
     * @param world the world model to be controlled
     * @param worldView the view associated with the world
     * @param mainController the main controller managing the overall application
     */
    public WorldController(final World world, final MainController mainController) {
        this.world = world;
        this.worldView = new WorldViewImpl(this, world.getCurrentRoom());
        this.mainController = mainController;
    }

    @Override
    public void handleInput(final Object input) {
        Enigma enigma;
        Controller controller;
        switch ((UserInput) input) {
            case UP:
                this.world.movePlayer(Movement.UP);
                break;
            case DOWN:
                this.world.movePlayer(Movement.DOWN);
                break;
            case LEFT:
                this.world.movePlayer(Movement.LEFT);
                break;
            case RIGHT:
                this.world.movePlayer(Movement.RIGHT);
                break;
            case INTERACT:
                enigma = this.world.letPlayerInteract().get();   
                controller = this.mainController.findController(enigma.getName());
                this.mainController.setController(controller);
                break;
            case INVENTORY:
                this.mainController.switchToInventory();
                break;
            default:
                break;  
        }
    }

    @Override
    public void quit() {
        this.running = false;
    }

    private class Loop extends Thread {
        public void run(){  
            while (isRunning()) {
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                movePlayerIfKeyPressed();
                if (world.hasWon()) {
                    mainController.winning();
                }
                worldView.draw(world.getCurrentRoom());
            }
        }
    }

    @Override
    public void loop() {
        new Loop().start();
    }

    private boolean isRunning() {
        return this.running;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JPanel getPanel() {
        return this.worldView.getPanel();
    }

    private void movePlayerIfKeyPressed() {
        for (Map.Entry<Integer, Boolean> entry : worldView.getKeyState().entrySet()) {
            if (entry.getValue()) { // Se il tasto è premuto
                handleInput(KEY_MAPPER.get(entry.getKey()));
            }
        }
    }
 
}
