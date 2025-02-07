package mindescape.controller.core.api;

import javax.swing.JPanel;

/**
 * The {@code Controller} interface defines the methods required to manage 
 * user interactions and update the game state within the application.
 */
public interface Controller {
    
    /**
     * Handles the given input.
     *
     * @param input the input to be handled
     * @throws IllegalArgumentException if the input is invalid
     * @throws NullPointerException if the input is null
     */
    void handleInput(Object input) throws IllegalArgumentException, NullPointerException;

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