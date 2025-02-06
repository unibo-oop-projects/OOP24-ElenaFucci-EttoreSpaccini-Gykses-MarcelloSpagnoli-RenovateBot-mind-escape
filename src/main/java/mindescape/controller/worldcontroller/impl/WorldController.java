package mindescape.controller.worldcontroller.impl;

import javax.swing.JPanel;
import mindescape.controller.api.Controller;
import mindescape.controller.api.LoopController;
import mindescape.controller.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.View;

public class WorldController implements LoopController {

    private final World world;
    private final View worldView;
    private final MainController mainController;
    private boolean running = true;
    private final String name = "World";

    public WorldController(final World world, final View worldView, final MainController mainController) {
        this.world = world;
        this.worldView = worldView;
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
                controller = this.mainController.findController("Inventory");
                this.mainController.setController(controller);
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
                //this.mainController.setController(new WinningView());
            }

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


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
        return (JPanel) this.worldView;
    }
 
}
