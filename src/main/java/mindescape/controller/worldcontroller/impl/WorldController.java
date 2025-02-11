package mindescape.controller.worldcontroller.impl;

import java.util.Map;
import javax.swing.JPanel;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.LoopController;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.WorldView;
import mindescape.view.world.WorldViewImpl;
import java.awt.event.KeyEvent;

public class WorldController implements LoopController {

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
        switch ((UserInput) input) {
            case UP -> this.world.movePlayer(Movement.UP);
            case DOWN -> this.world.movePlayer(Movement.DOWN);
            case LEFT -> this.world.movePlayer(Movement.LEFT);
            case RIGHT -> this.world.movePlayer(Movement.RIGHT);
            case INTERACT -> this.world.letPlayerInteract().ifPresent(enigma -> 
                this.mainController.setController(ControllerName.fromString(enigma.getName())));
            case INVENTORY -> this.mainController.setController(ControllerName.INVENTORY);
            default -> throw new IllegalArgumentException("Unknown input: " + input);
        }
    }

    @Override
    public void quit() {
        this.running = false;
    }

    private void loop() throws InterruptedException{
        final long frameTime = TIME / FPS;

        while (this.running) {
            long startTime = System.currentTimeMillis();
            this.worldView.draw(this.world.getCurrentRoom());
            
            if (world.hasWon()) {
                this.mainController.winning();
            }

            this.worldView.draw(this.world.getCurrentRoom());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JPanel getPanel() {
        return this.worldView.getPanel();
    }

    @Override
    public boolean canSave() {
        return true;
    }
    
    @Override
    public Model getModel() {
        return this.world;
    }

    @Override
    public void start() {
        try {
            this.loop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
