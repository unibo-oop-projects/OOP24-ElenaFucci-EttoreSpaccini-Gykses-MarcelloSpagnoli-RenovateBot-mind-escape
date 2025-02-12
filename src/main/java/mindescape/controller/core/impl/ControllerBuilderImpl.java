package mindescape.controller.core.impl;

import mindescape.controller.caesarcipher.impl.CaesarCipherControllerImpl;
import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.enigmacalendar.impl.CalendarControllerImpl;
import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.controller.enigmapuzzle.impl.EnigmaPuzzleControllerImpl;
import mindescape.controller.guide.impl.GuideControllerImpl;
import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.saveload.SavesController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.caesarcipher.api.CaesarCipherModel;
import mindescape.model.enigma.enigmapassword.api.EnigmaPasswordModel;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.model.world.api.World;
import mindescape.model.world.impl.WorldImpl;

/**
 * Implementation of the ControllerBuilder interface.
 * This class is responsible for building various controllers and managing them through a ControllerMap.
 */
public class ControllerBuilderImpl implements ControllerBuilder {

    private final ControllerMap controllerMap;
    private final MainController mainController;

    public ControllerBuilderImpl(final MainController mainController) {
        this.mainController = mainController;
        this.controllerMap = new ControllerMapImpl();
    }

    @Override
    public void buildMenu() {
        this.controllerMap.addController(new MenuController(this.mainController));
    }

    @Override
    public void buildPuzzle(Enigma enigma) {
        this.controllerMap.addController(new EnigmaPuzzleControllerImpl((EnigmaPuzzleModelImpl) enigma, this.mainController));
    }

    @Override
    public void buildEnigmaFirstDoor(Enigma enigma) {
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
    }

    @Override
    public void buildCalendar(Enigma enigma) {
        this.controllerMap.addController((new CalendarControllerImpl(mainController)));
    }

    @Override
    public void buildComputer(Enigma enigma) {
        this.controllerMap.addController(new CaesarCipherControllerImpl((CaesarCipherModel) enigma, this.mainController));
    }

    @Override
    public void buildWardrobe(Enigma enigma) {
        //TODO: check if this is the correct password
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
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
    public void buildLoad() {
       this.controllerMap.addController(new SavesController(this.mainController));
    }

    @Override
    public void buildInventory(final World world) {
        this.controllerMap.addController(new InventoryControllerImpl(world.getPlayer().getInventory(), mainController));
    }

    @Override
    public void buildDrawer(Enigma enigma) {
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
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
    public void buildGuide() {
        this.controllerMap.addController(new GuideControllerImpl(mainController));
    }

}
