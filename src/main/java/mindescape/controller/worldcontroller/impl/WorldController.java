package mindescape.controller.worldcontroller.impl;

import mindescape.controller.api.Controller;
import mindescape.controller.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.Movement;
import mindescape.view.api.View;

public class WorldController implements Controller {
    private final World world;
    private final View worldView;
    private final MainController mainController;

    public WorldController(World world, View worldView, MainController mainController) {
        this.world = world;
        this.worldView = worldView;
        this.mainController = mainController;
    }

    @Override
    public void handleInput(UserInput input) {
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
                var x = this.world.letPlayerInteract();   
                this.mainController.setController(null);
                break;
            case OPEN_INVENTORY:
                this.mainController.setController(null);
                break;
            case CLOSE_INVENTORY:
            default:
                break;  
            
        }

    }

    @Override
    public void loop() {

    }
}
