package mindescape.controller.core.api;

import javax.swing.JPanel;

/**
 * The {@code Controller} interface defines the methods required to manage 
 * user interactions and update the game state within the application.
 */
public interface Controller {

    /**
     * Handles user input and processes it accordingly to update the game state.
     *
     * @param input the user input to be processed
     */
    void handleInput(Object input);

    /**
     * Retrieves the name of this controller.
     *
     * @return the name of the controller as a {@code String}
     */
    String getName();

    /**
     * Retrieves the main panel associated with this controller.
     *
     * @return the panel managed by this controller as a {@code JPanel}
     */
    JPanel getPanel();

    /**
     * Requests to quit the current controller, triggering a transition 
     * to the next controller in the {@code MainController}.
     */
    void quit();
}