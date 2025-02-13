package mindescape.controller.core.impl;

import java.util.Objects;
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
import mindescape.controller.saveload.impl.SavesControllerImpl;
import mindescape.controller.worldcontroller.impl.WorldController;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.caesarcipher.api.CaesarCipherModel;
import mindescape.model.enigma.calendar.Calendar;
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

    /**
     * Constructs a new ControllerBuilderImpl with the specified MainController.
     *
     * @param mainController the main controller to be used by this builder
     */
    public ControllerBuilderImpl(final MainController mainController) {
        this.mainController = mainController;
        this.controllerMap = new ControllerMapImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildMenu() {
        this.controllerMap.addController(new MenuController(this.mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildPuzzle(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPuzzleModelImpl)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPuzzleControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        this.controllerMap.addController(new EnigmaPuzzleControllerImpl((EnigmaPuzzleModelImpl) enigma, this.mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildEnigmaFirstDoor(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildCalendar(final Enigma enigma) {
        if (!(enigma instanceof Calendar)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for CalendarControllerImpl: " + enigma.getClass()
            );
        }
        this.controllerMap.addController(new CalendarControllerImpl(mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildComputer(final Enigma enigma) {
        if (!(enigma instanceof CaesarCipherModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for CaesarCipherControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        this.controllerMap.addController(new CaesarCipherControllerImpl((CaesarCipherModel) enigma, this.mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildWardrobe(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildNewWorld(final String username) {
        Objects.requireNonNull(username);
        this.controllerMap.addController(new WorldController(new WorldImpl(username), mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExistingWorld(final World world) {
        Objects.requireNonNull(world);
        this.controllerMap.addController(new WorldController(world, mainController));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void buildLoad() {
       this.controllerMap.addController(new SavesControllerImpl(this.mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildInventory(final World world) {
        Objects.requireNonNull(world);
        this.controllerMap.addController(new InventoryControllerImpl(world.getPlayer().getInventory(), mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildDrawer(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        this.controllerMap.addController(new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.controllerMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerMap getResult() {
        return this.controllerMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildGuide() {
        this.controllerMap.addController(new GuideControllerImpl(mainController));
    }

}
