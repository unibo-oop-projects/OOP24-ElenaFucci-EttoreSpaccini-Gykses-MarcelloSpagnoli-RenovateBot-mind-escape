package mindescape.controller.api;

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
}
