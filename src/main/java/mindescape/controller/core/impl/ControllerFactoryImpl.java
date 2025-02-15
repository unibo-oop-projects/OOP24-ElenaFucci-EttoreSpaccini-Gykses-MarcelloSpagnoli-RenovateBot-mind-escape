package mindescape.controller.core.impl;

import java.util.Objects;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.caesarcipher.impl.CaesarCipherControllerImpl;
import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerFactory;
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
 * Implementation of the ControllerFactory interface.
 */
public final class ControllerFactoryImpl implements ControllerFactory {

    private final MainController mainController;

    /**
     * Constructor for the ControllerFactoryImpl class.
     * 
     * @param mainController the MainController instance to be used by the factory.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The maincontroller needs to be exposed to the caller")
    public ControllerFactoryImpl(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildMenu() {
        return new MenuController(this.mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildPuzzle(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPuzzleModelImpl)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPuzzleControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        return new EnigmaPuzzleControllerImpl((EnigmaPuzzleModelImpl) enigma, this.mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildEnigmaFirstDoor(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        return new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildCalendar(final Enigma enigma) {
        if (!(enigma instanceof Calendar)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for CalendarControllerImpl: " + enigma.getClass()
            );
        }
        return new CalendarControllerImpl(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildComputer(final Enigma enigma) {
        if (!(enigma instanceof CaesarCipherModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for CaesarCipherControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        return new CaesarCipherControllerImpl((CaesarCipherModel) enigma, this.mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildWardrobe(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        return new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildNewWorld(final String username) {
        Objects.requireNonNull(username);
        return new WorldController(new WorldImpl(username), mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildExistingWorld(final World world) {
        Objects.requireNonNull(world);
        return new WorldController(world, mainController);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildLoad() {
       return new SavesControllerImpl(this.mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildInventory(final World world) {
        Objects.requireNonNull(world);
        return new InventoryControllerImpl(world.getPlayer().getInventory(), mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildDrawer(final Enigma enigma) {
        if (!(enigma instanceof EnigmaPasswordModel)) {
            throw new IllegalArgumentException(
                "Invalid enigma type for EnigmaPasswordControllerImpl: " + enigma.getClass().getSimpleName()
            );
        }
        return new EnigmaPasswordControllerImpl((EnigmaPasswordModel) enigma, mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller buildGuide() {
        return new GuideControllerImpl(mainController);
    }

}
