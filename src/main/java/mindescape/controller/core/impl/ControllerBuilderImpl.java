package mindescape.controller.core.impl;

import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.saveload.SavesController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.enigma.api.EnigmaFactory.EnigmaType;
import mindescape.model.enigma.enigmapassword.api.EnigmaPasswordModel;
import mindescape.model.enigma.impl.EnigmaFactoryImpl;
import mindescape.model.world.api.World;
import mindescape.model.world.impl.WorldImpl;

/**
 * Implementation of the ControllerBuilder interface.
 * This class is responsible for building various controllers and managing them through a ControllerMap.
 */
public class ControllerBuilderImpl implements ControllerBuilder {

    private final ControllerMap controllerMap = new ControllerMapImpl();
    private final MainController mainController;
    private final EnigmaFactory enigmaFactory = new EnigmaFactoryImpl();

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
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) this.enigmaFactory.getEnigma(EnigmaType.ENIGMA_FIRST_DOOR.getName()), mainController));
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
    public void buildNewWorld(final String username) {
        this.controllerMap.addController(new WorldController(new WorldImpl(username), mainController));
    }

    @Override
    public void buildExistingWorld(final World world) {
        this.controllerMap.addController(new WorldController(world, mainController));
    }

    @Override
    public void reset() {
        this.controllerMap.clear();
    }

    @Override
    public ControllerMap getResult() {
        return this.controllerMap;
    }
    
    @Override
    public void buildLoad() {
       this.controllerMap.addController(new SavesController(this.mainController));
    }

    @Override
    public void buildInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildInventory'");
    }
}
