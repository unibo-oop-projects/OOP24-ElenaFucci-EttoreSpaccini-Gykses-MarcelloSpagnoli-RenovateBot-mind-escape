package mindescape.controller.core.api;

public interface ControllerMap {

    enum ControllerName {
        MENU("Menu"),
        WORLD("World"),
        PUZZLE("Puzzle"),
        ENIGMA_FIRST_DOOR("EnigmaFirstDoor"),
        CALENDAR("Calendar"),
        DRAWER("Drawer"),
        COMPUTER("Computer"),
        WARDROBE("Wardrobe");
        
        private final String name;

        ControllerName(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
        
        public static ControllerName fromString(String name) {
            for (ControllerName controllerName : ControllerName.values()) {
                if (controllerName.name.equals(name)) {
                    return controllerName;
                }
            }
            return null;
        }
    }

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
}