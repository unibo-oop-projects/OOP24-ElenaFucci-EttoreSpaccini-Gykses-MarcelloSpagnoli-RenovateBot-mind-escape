package mindescape.controller.api;

import mindescape.model.api.Model;
import mindescape.view.api.View;

/**
 * The Controller interface defines the methods required to handle user input
 * and update the game state in the application.
 */
public interface Controller {
    /**
     * Method to handle user input.
     *
     * @param input the user input to be handled
     */
    void handleInput(UserInput input);

    /**
     * Method to update the game state.
     */
    void loop();

    /**
     * Method to stop the game.
     */
    void stop();

    /**
     * Retrieves the current view associated with the controller.
     *
     * @return {@code View} the current view instance
     */
    View getView();

    /**
     * Retrieves the model associated with this controller.
     *
     * @return {@code Model} the current model instance
     */
    Model getModel();

    /**
     * Checks if the controller is currently running.
     *
     * @return {@code true} if the controller is running, {@code false} otherwise.
     */
    boolean isRunning();
}
