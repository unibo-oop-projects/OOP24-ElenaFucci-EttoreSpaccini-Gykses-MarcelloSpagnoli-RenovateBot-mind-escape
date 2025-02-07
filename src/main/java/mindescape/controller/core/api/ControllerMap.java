package mindescape.controller.core.api;

/**
 * Interface for managing a collection of controllers.
 * Provides methods to find, add, remove, and clear controllers.
 */
public interface ControllerMap {

    /**
     * Finds and returns a controller based on the given controller name.
     *
     * @param name the name of the controller to find
     * @return the controller associated with the specified name
     */
    Controller findController(ControllerName name);

    /**
     * Adds a controller to the map.
     *
     * @param name the name of the controller to add
     * @param controller the controller to add
     */
    void addController(Controller controller);

    /**
     * Removes a controller from the map.
     *
     * @param name the name of the controller to remove
     */
    void removeController(ControllerName name);

    /**
     * Clears the current state or data of the implementing class.
     * This method is intended to reset or remove all relevant information,
     * effectively returning the object to its initial state.
     */
    void clear();

}