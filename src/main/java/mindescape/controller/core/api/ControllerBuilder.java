package mindescape.controller.core.api;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;

/**
 * Interface for building various components of the game controller.
 */
public interface ControllerBuilder {

    /**
     * Builds the menu component.
     */
    void buildMenu();

    /**
     * Builds a new world component.
     *
     * @param username the username of the player
     */
    void buildNewWorld(String username);

    /**
     * Builds an existing world component.
     *
     * @param world the world to build
     */
    void buildExistingWorld(World world);

    /**
     * Builds the puzzle component.
     *
     * @param enigma the enigma to build
     */
    void buildPuzzle(Enigma enigma);

    /**
     * Builds the first door enigma component.
     *
     * @param enigma the enigma to build
     */
    void buildEnigmaFirstDoor(Enigma enigma);

    /**
     * Builds a calendar for the given enigma.
     *
     * @param enigma the enigma for which the calendar is to be built
     */
    void buildCalendar(Enigma enigma);

    /**
     * Builds the computer component.
     * 
     * @param enigma the enigma to build
     */
    void buildComputer(Enigma enigma);

    /**
     * Builds the wardrobe component.
     * 
     * @param enigma the enigma to build
     */
    void buildWardrobe(Enigma enigma);

    /**
     * Returns the result of the building process as a ControllerMap.
     *
     * @return the built ControllerMap
     */
    ControllerMap getResult();

    /**
     * Resets the builder to its initial state.
     */
    void reset();

    /**
     * Builds the inventory for the controller.
     * 
     * @param world the world to build the inventory for
     */
    void buildInventory(World world);

    /**
     * Builds the load controller.
     */
    void buildLoad();

    /**
     * Builds the drawer controller.
     * 
     * @param enigma the enigma to build the drawer for
     */
    void buildDrawer(Enigma enigma);

    /**
     * Builds the guide controller.
     */
    void buildGuide();
}
