package mindescape.controller.core.api;

import javax.swing.JPanel;

/**
 * The Controller interface defines the methods required to handle user input
 * and update the game state in the application.
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
     * Retrieves the name of the controller.
     *
     * @return the name as a {@code String}
     */
    String getName();

    /**
     * Retrieves the panel of the controller.
     *
     * @return the panel as a {@code JPanel}
     */
    JPanel getPanel();

}
