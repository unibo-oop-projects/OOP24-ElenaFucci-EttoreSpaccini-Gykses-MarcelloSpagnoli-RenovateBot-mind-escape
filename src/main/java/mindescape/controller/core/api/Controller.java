package mindescape.controller.core.api;

import javax.swing.JPanel;

import mindescape.model.api.Model;

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

    /**
     * Checks if the current state can be saved.
     *
     * @return {@code true} if the state can be saved, {@code false} otherwise
     */
    boolean canSave();

    /**
     * Retrieves the model.
     *
     * @return the current instance of the Model.
     */
    Model getModel();

    /**
     * Starts the controller, initializing any necessary resources or processes.
     */
    void start();
}
