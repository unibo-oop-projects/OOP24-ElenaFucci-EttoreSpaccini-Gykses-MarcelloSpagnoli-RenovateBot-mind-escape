package mindescape.controller.api;

import javax.swing.JPanel;

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
    void handleInput(Object input);


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
