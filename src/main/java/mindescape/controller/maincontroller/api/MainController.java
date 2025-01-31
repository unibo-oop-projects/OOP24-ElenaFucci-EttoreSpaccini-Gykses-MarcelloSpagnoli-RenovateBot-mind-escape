package mindescape.controller.maincontroller.api;

import mindescape.controller.api.Controller;

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
}
