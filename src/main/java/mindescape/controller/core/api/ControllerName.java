package mindescape.controller.core.api;

import java.util.Objects;


/**
 * Enum representing various controller names used in the application.
 * Each enum constant is associated with a specific controller name.
 * <p>
 * The available controller names are:
 * <ul>
 *   <li>MENU - Represents the Menu controller</li>
 *   <li>WORLD - Represents the World controller</li>
 *   <li>PUZZLE - Represents the Puzzle controller</li>
 *   <li>ENIGMA_FIRST_DOOR - Represents the EnigmaFirstDoor controller</li>
 *   <li>CALENDAR - Represents the Calendar controller</li>
 *   <li>DRAWER - Represents the Drawer controller</li>
 *   <li>CAESAR_CIPHER - Represents the CaesarCipher controller</li>
 *   <li>WARDROBE - Represents the Wardrobe controller</li>
 *   <li>INVENTORY - Represents the Inventory controller</li>
 *   <li>LOAD - Represents the LoadGame controller</li>
 *   <li>GUIDE - Represents the Guide controller</li>
 * </ul>
 * Each controller name is associated with a string representation.
 * <p>
 * This enum provides methods to:
 * <ul>
 *   <li>Retrieve the name associated with a controller</li>
 *   <li>Convert a string to a corresponding ControllerName enum value</li>
 * </ul>
 */
public enum ControllerName {
    /**
     * Represents the Menu controller.
     */
    MENU("Menu"),
    /**
     * Represents the World controller.
     */
    WORLD("World"),
    /**
     * Represents the Puzzle controller.
     */
    PUZZLE("Puzzle"),
    /**
     * Represents the EnigmaFirstDoor controller.
     */
    ENIGMA_FIRST_DOOR("EnigmaFirstDoor"),
    /**
     * Represents the Calendar controller.
     */
    CALENDAR("Calendar"),
    /**
     * Represents the Drawer controller.
     */
    DRAWER("Drawer"),
    /**
     * Represents the CaesarCipher controller.
     */
    CAESAR_CIPHER("CaesarCipher"),
    /**
     * Represents the Wardrobe controller.
     */
    WARDROBE("Wardrobe"),
    /**
     * Represents the Inventory controller.
     */
    INVENTORY("Inventory"),
    /**
     * Represents the LoadGame controller.
     */
    LOAD("LoadGame"),
    /**
     * Represents the Guide controller.
     */
    GUIDE("Guide");
    /**
     * The name associated with the controller.
     */
    private final String name;
    /**
     * Constructs a new ControllerName with the specified name.
     *
     * @param name the name to be assigned to the ControllerName instance
     */
    ControllerName(final String name) {
        this.name = name;
    }

    /**
     * Retrieves the name associated with this controller.
     *
     * @return the name of the controller.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Converts a string to a corresponding ControllerName enum value.
     *
     * @param name the string representation of the ControllerName
     * @return the corresponding ControllerName enum value, or null if no match is found
     * @throws NullPointerException if the provided name is null
     */
    public static ControllerName fromString(final String name) {
        Objects.requireNonNull(name);
        for (final ControllerName controllerName : values()) {
            if (controllerName.name.equals(name)) {
                return controllerName;
            }
        }
        return null;
    }
}
