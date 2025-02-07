package mindescape.controller.core.api;

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
     */
    void buildNewWorld();

    /**
     * Builds an existing world component.
     *
     * @param world the existing world to build
     */
    void buildExistingWorld(World world);

    /**
     * Builds the puzzle component.
     */
    void buildPuzzle();

    /**
     * Builds the enigma first door component.
     */
    void buildEnigmaFirstDoor();

    /**
     * Builds the calendar component.
     */
    void buildCalendar();

    /**
     * Builds the drawer component.
     */
    void buildDrawer();

    /**
     * Builds the computer component.
     */
    void buildComputer();

    /**
     * Builds the wardrobe component.
     */
    void buildWardrobe();

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
}
