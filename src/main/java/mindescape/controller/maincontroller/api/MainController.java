package mindescape.controller.maincontroller.api;

import java.util.Optional;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerName;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;


/**
 * The MainController interface provides a method to set a Controller.
 * Implementing classes should define the behavior for setting the Controller.
 */
public interface MainController {
    /**
     * Sets the controller based on the provided controller name.
     *
     * @param controllerName the name of the controller to be set
     * @param enigma the enigma to be set
     */
    void setController(ControllerName controllerName, Optional<Enigma> enigma);

    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Returns the current Controller.
     *
     * @return the current Controller
     */
    Controller getController();

    /**
     * This method is called when the player wins the game.
     * It should handle the logic that needs to be executed upon winning,
     * such as updating the game state, displaying a winning message, 
     * and any other necessary actions.
     */
    void winning();

    /**
     * Exit the game.
     */
    void exit();

    /**
     * Save the game.
     */
    void save();

    /**
     * Load the game.
     * 
     * @param world the world to be loaded
     */
    void loadGame(World world);

    /**
     * Set the new player name.
     * 
     * @param playerName the new player name
     */
    void setPlayerName(String playerName);
}
