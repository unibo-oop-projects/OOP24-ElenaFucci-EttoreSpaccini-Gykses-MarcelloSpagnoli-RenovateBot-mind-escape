package mindescape.controller.enigmapassword.impl;

import java.util.Optional;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmapassword.api.EnigmaPasswordController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapassword.api.EnigmaPasswordModel;
import mindescape.view.enigmapassword.api.EnigmaPasswordView;
import mindescape.view.enigmapassword.impl.EnigmaPasswordViewImpl;

/**
 * Implementation of {@code EnigmaPasswordController} that manages user interactions
 * for password-based enigmas.
 */
public final class EnigmaPasswordControllerImpl implements EnigmaPasswordController {

    private final MainController mainController;
    private final EnigmaPasswordModel model; 
    private final EnigmaPasswordView view; 

    /**
     * Constructs an {@code EnigmaPasswordControllerImpl} with the given model and main controller.
     *
     * @param model the password enigma model
     * @param mainController the main controller managing this instance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The mainController need to be exposed to the caller")
    public EnigmaPasswordControllerImpl(final EnigmaPasswordModel model, final MainController mainController) {
        this.mainController = mainController;
        this.model = model; 
        this.view = new EnigmaPasswordViewImpl(this); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        if (input instanceof String) {
            this.view.showResult(this.model.hit((String) input)); 
        }
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.model.getName(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getModel() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
    }
}
