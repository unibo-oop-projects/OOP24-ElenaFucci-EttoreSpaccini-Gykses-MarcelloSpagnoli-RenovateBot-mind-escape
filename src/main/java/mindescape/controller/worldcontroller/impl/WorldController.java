package mindescape.controller.worldcontroller.impl;

import mindescape.controller.api.Controller;
import mindescape.controller.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.View;

public class WorldController implements Controller {
    private final World world;
    private final View worldView;
    private final MainController mainController;

    public WorldController(final World world, final View worldView, final MainController mainController) {
        this.world = world;
        this.worldView = worldView;
        this.mainController = mainController;
    }

    @Override
    public void handleInput(final UserInput input) {
        Enigma enigma;
        Controller controller;
        switch (input) {
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
            case OPEN_INVENTORY:
                controller = this.mainController.findController("Inventory");
                this.mainController.setController(controller);
                break;
            default:
                break;  
        }
    }

    @Override
    public void loop() {
        while (true) {
            this.worldView.start();
        }
    }
 
}
