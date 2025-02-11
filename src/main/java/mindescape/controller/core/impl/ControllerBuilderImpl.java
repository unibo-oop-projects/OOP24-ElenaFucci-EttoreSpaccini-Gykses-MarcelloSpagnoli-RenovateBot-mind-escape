package mindescape.controller.core.impl;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import mindescape.controller.caesarcipher.impl.CaesarCipherControllerImpl;
import mindescape.controller.core.api.ControllerBuilder;
import mindescape.controller.core.api.ControllerMap;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmacalendar.impl.CalendarControllerImpl;
import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.controller.enigmapuzzle.impl.EnigmaPuzzleControllerImpl;
import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.menu.MenuController;
import mindescape.controller.saveload.SavesController;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
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
        Image image;
        try {
            image = ImageIO.read(new File("src/resources/puzzle/Presidente_Sergio_Mattarella.jpg"));
            this.controllerMap.addController(new EnigmaPuzzleControllerImpl(new EnigmaPuzzleModelImpl(5, 5, image, ControllerName.PUZZLE.getName()), this.mainController));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        

    @Override
    public void buildEnigmaFirstDoor() {
        this.controllerMap.addController(new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl(ControllerName.ENIGMA_FIRST_DOOR.getName(), "Sergio Mattarella"), mainController));
    }

    @Override
    public void buildCalendar() {
        this.controllerMap.addController(new CalendarControllerImpl(mainController));
    }

    @Override
    public void buildComputer() {
        this.controllerMap.addController(new CaesarCipherControllerImpl(new CaesarCipherModelImpl(ControllerName.CAESAR_CYPHER.getName(), 3), this.mainController));
    }

    @Override
    public void buildWardrobe() {
        //TODO: check if this is the correct password
        this.controllerMap.addController(new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl(ControllerName.WARDROBE.getName(), "Pianinator"), mainController));
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
    public void buildDrawer() {
        this.controllerMap.addController(new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl(ControllerName.DRAWER.getName(), "1213"), mainController));
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
