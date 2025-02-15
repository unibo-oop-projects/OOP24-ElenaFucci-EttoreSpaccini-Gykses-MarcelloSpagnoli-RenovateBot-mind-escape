package mindescape.controller.core.api;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;

/**
 * Factory interface for creating various types of controllers in the application.
 */
public interface ControllerFactory {

    /**
     * Creates a controller for the menu.
     * 
     * @return a Controller instance for the menu.
     */
    Controller buildMenu();

    /**
     * Creates a controller for a puzzle.
     * 
     * @param enigma the Enigma instance associated with the puzzle.
     * @return a Controller instance for the puzzle.
     */
    Controller buildPuzzle(Enigma enigma);

    /**
     * Creates a controller for the first door enigma.
     * 
     * @param enigma the Enigma instance associated with the first door.
     * @return a Controller instance for the first door enigma.
     */
    Controller buildEnigmaFirstDoor(Enigma enigma);

    /**
     * Creates a controller for the calendar enigma.
     * 
     * @param enigma the Enigma instance associated with the calendar.
     * @return a Controller instance for the calendar enigma.
     */
    Controller buildCalendar(Enigma enigma);

    /**
     * Creates a controller for the computer enigma.
     * 
     * @param enigma the Enigma instance associated with the computer.
     * @return a Controller instance for the computer enigma.
     */
    Controller buildComputer(Enigma enigma);

    /**
     * Creates a controller for the wardrobe enigma.
     * 
     * @param enigma the Enigma instance associated with the wardrobe.
     * @return a Controller instance for the wardrobe enigma.
     */
    Controller buildWardrobe(Enigma enigma);

    /**
     * Creates a controller for a new world.
     * 
     * @param username the username associated with the new world.
     * @return a Controller instance for the new world.
     */
    Controller buildNewWorld(String username);

    /**
     * Creates a controller for an existing world.
     * 
     * @param world the World instance representing the existing world.
     * @return a Controller instance for the existing world.
     */
    Controller buildExistingWorld(World world);

    /**
     * Creates a controller for loading.
     * 
     * @return a Controller instance for loading.
     */
    Controller buildLoad();

    /**
     * Creates a controller for the inventory.
     * 
     * @param world the World instance associated with the inventory.
     * @return a Controller instance for the inventory.
     */
    Controller buildInventory(World world);

    /**
     * Creates a controller for the drawer enigma.
     * 
     * @param enigma the Enigma instance associated with the drawer.
     * @return a Controller instance for the drawer enigma.
     */
    Controller buildDrawer(Enigma enigma);

    /**
     * Creates a controller for the guide.
     * 
     * @return a Controller instance for the guide.
     */
    Controller buildGuide();
}
