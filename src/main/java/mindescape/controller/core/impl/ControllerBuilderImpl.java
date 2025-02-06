package mindescape.controller.core.impl;

import java.util.Map;
import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.view.world.WorldViewImpl;

public class ControllerBuilderImpl implements ControllerBuilder {

    private final ControllerMap controllerMap = new ControllerMapImpl(Map.of());
    private final MainController mainController;

    public ControllerBuilderImpl(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void buildMenu() {
        this.controllerMap.addController(new MenuController(this.mainController));
    }

    @Override
    public void buildWorld() {
        this.controllerMap.addController(new WorldController(null, new WorldViewImpl(), mainController));
    }

    @Override
    public void buildPuzzle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildPuzzle'");
    }

    @Override
    public void buildEnigmaFirstDoor() {
        // TODO: Implement this method
        // this.controllerMap.addController(new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl("EnigmaFirstDoor", "Sergio Mattarella"), mainController));
    }

    @Override
    public void buildCalendar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildCalendar'");
    }

    @Override
    public void buildDrawer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildDrawer'");
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
    public ControllerMap getResult() {
        return this.controllerMap;
    }
    
}
