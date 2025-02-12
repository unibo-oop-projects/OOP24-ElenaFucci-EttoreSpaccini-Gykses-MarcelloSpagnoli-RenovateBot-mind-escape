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
     * @param world the existing world to build
     */
    void buildExistingWorld(World world);

    /**
     * Builds the puzzle component.
     */
    void buildPuzzle(Enigma enigma);

    /**
     * Builds the enigma first door component.
     */
    void buildEnigmaFirstDoor(Enigma enigma);

    /**
     * Builds the calendar component.
     */
    void buildCalendar(Enigma enigma);

    /**
     * Builds the computer component.
     */
    void buildComputer(Enigma enigma);

    /**
     * Builds the wardrobe component.
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
     */
    void buildInventory(World world);

    /**
     * Builds the load controller.
     */
    void buildLoad();

    /**
     * Builds the drawer controller.
     */
    void buildDrawer(Enigma enigma);

    void buildGuide();
}
