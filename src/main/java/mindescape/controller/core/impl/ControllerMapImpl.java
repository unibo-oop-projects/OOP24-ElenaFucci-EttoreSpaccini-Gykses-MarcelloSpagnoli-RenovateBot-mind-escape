package mindescape.controller.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;

/**
 * Implementation of the ControllerMap interface that manages a collection of controllers.
 */
public class ControllerMapImpl implements ControllerMap {

    private final Map<String, Controller> controllers;

    /**
     * Default constructor that initializes an empty controller map.
     */
    public ControllerMapImpl() {
        this.controllers = new HashMap<>();
    }

    /**
     * Constructor that initializes the controller map with the given controllers.
     * @param controllersMap The initial map of controllers.
     */
    public ControllerMapImpl(final Map<String, Controller> controllersMap) {
        this.controllers = new HashMap<>(controllersMap);
    }

    @Override
    public Controller findController(final ControllerName name) {
        Objects.requireNonNull(name, "Controller name must not be null.");
        return this.controllers.get(name.getName());
    }

    @Override
    public void addController(final Controller controller) {
        this.controllers.put(controller.getName(), controller);
    }

    @Override
    public void removeController(final ControllerName name) {
        this.controllers.remove(name.getName());
    }

    @Override
    public void clear() {
        this.controllers.clear();
    }

    @Override
    public boolean containsController(final ControllerName name) {
        return this.controllers.containsKey(name.getName());
    }

    @Override
    public Iterable<Controller> getControllers() {
        return this.controllers.values();
    }
}