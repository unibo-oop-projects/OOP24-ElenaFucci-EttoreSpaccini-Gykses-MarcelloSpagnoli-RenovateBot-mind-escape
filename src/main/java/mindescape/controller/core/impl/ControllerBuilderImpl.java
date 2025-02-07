package mindescape.controller.core.impl;

import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.world.api.World;
import mindescape.model.world.impl.WorldImpl;

/**
 * Implementation of the ControllerBuilder interface.
 * This class is responsible for building various controllers and managing them through a ControllerMap.
 */
public class ControllerBuilderImpl implements ControllerBuilder {

    private final ControllerMap controllerMap = new ControllerMapImpl();
    private final MainController mainController;

    public ControllerBuilderImpl(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void buildMenu() {
        this.controllerMap.addController(new MenuController(this.mainController));
    }

    @Override
    public void buildPuzzle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildPuzzle'");
    }

    @Override
    public void buildEnigmaFirstDoor() {
        // this.controllerMap.addController(new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl("EnigmaFirstDoor", "Sergio Mattarella"), mainController));
    }

    @Override
    public void buildCalendar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildCalendar'");
    }

    @Override
    public void buildComputer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildComputer'");
    }

    @Override
    public void buildWardrobe() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildWardrobe'");
    }

    @Override
    public void buildNewWorld() {
        this.controllerMap.addController(new WorldController(new WorldImpl(), mainController));
    }

    @Override
    public void buildExistingWorld(final World world) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildExistingWorld'");
    }

    @Override
    public void reset() {
        this.controllerMap.clear();
    }

    @Override
    public ControllerMap getResult() {
        return this.controllerMap;
    }
}
