package mindescape.controller.maincontroller.api;

import mindescape.controller.core.api.Controller;

/**
 * The MainController interface provides a method to set a Controller.
 * Implementing classes should define the behavior for setting the Controller.
 */
public interface MainController {
    /**
     * Sets the controller for this instance.
     *
     * @param controller the controller to be set
     */
    void setController(Controller controller);

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
     * Finds a Controller by its name.
     *
     * @param name the name of the Controller to be found
     * @return the found Controller
     */
    Controller findController(String name);


    /**
     * Switches to the game Controller.
     */
    void switchToGame();

}
