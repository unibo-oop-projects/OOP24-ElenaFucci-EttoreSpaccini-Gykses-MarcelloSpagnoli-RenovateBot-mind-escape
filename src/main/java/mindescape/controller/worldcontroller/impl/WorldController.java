package mindescape.controller.worldcontroller.impl;

import javax.swing.JPanel;

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
        this.worldView = new WorldViewImpl(this);
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

    @Override
    public void loop() {
        while (this.isRunning()) {
            //TODO implement game loop logic here : game has to run with 60fps

            if (this.world.hasWon()) {
                this.mainController.winning();
            }

            this.worldView.draw(this.world.getCurrentRoom());
        }
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
 
}
