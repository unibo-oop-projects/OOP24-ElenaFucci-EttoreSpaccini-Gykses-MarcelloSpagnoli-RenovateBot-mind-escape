package mindescape.controller.core.api;

import java.util.Objects;

/**
 * Enum representing different controller names in the application.
 * Each enum value has an associated string name.
 * 
 * <p>Enum Values:</p>
 * <ul>
 *   <li>MENU - Represents the Menu controller</li>
 *   <li>WORLD - Represents the World controller</li>
 *   <li>PUZZLE - Represents the Puzzle controller</li>
 *   <li>ENIGMA_FIRST_DOOR - Represents the EnigmaFirstDoor controller</li>
 *   <li>CALENDAR - Represents the Calendar controller</li>
 *   <li>DRAWER - Represents the Drawer controller</li>
 *   <li>COMPUTER - Represents the Computer controller</li>
 *   <li>WARDROBE - Represents the Wardrobe controller</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getName()} - Retrieves the name associated with this controller.</li>
 *   <li>{@link #fromString(String)} - Converts a string to a corresponding ControllerName enum value.</li>
 * </ul>
 */
public enum ControllerName {
        MENU("Menu"),
        WORLD("World"),
        PUZZLE("Puzzle"),
        ENIGMA_FIRST_DOOR("EnigmaFirstDoor"),
        CALENDAR("Calendar"),
        DRAWER("Drawer"),
        COMPUTER("Computer"),
        WARDROBE("Wardrobe"),
        INVENTORY("Inventory");
        
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
        public static ControllerName fromString(final String name) throws NullPointerException {
            Objects.requireNonNull(name);
            for (final ControllerName controllerName : ControllerName.values()) {
                if (controllerName.name.equals(name)) {
                    return controllerName;
                }
            }
            return null;
        }
    }