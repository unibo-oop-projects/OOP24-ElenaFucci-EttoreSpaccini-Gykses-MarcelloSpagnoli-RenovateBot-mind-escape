package mindescape.controller.core.impl;

import java.util.HashMap;
import java.util.Map;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerMap;

/**
 * Implementation of the ControllerMap interface that manages a collection of controllers.
 * This class provides methods to find and add controllers to the map.
 */
public class ControllerMapImpl implements ControllerMap {

    private final Map<String, Controller> controllers = new HashMap<>();

    public ControllerMapImpl(final Map<String, Controller> controllersMap) {
        if (!controllersMap.isEmpty()) {
            for (final var controller : controllersMap.entrySet()) {
                this.controllers.put(controller.getKey(), controller.getValue());
            }
        }
    }

    @Override
    public Controller findController(final ControllerName name) {
        return this.controllers.get(name.getName());
    }

    @Override
    public void addController(final Controller controller) {
        this.controllers.put(controller.getName(), controller);
    }
}
