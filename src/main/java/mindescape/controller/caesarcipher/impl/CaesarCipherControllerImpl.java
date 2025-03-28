package mindescape.controller.caesarcipher.impl;

import java.util.Optional;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.caesarcipher.api.CaesarCipherController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.enigma.caesarcipher.api.CaesarCipherModel;
import mindescape.view.caesarcipher.api.CaesarCipherView;
import mindescape.view.caesarcipher.impl.CaesarCipherViewImpl;

/**
 * Implementation of {@code CaesarCipherController} that manages user interaction and model updates.
 */
public final class CaesarCipherControllerImpl implements CaesarCipherController {

    private final MainController mainController;
    private final CaesarCipherModel model;
    private final CaesarCipherView view; 

    /**
     * Constructs a {@code CaesarCipherControllerImpl} with the given model and main controller.
     *
     * @param model the Caesar Cipher model
     * @param mainController the main controller managing this instance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The mainController need to be exposed to the caller")
    public CaesarCipherControllerImpl(final CaesarCipherModel model, final MainController mainController) {
        this.mainController = mainController;
        this.model = model; 
        this.view = new CaesarCipherViewImpl(this); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        try {
            final int shift = Integer.parseInt((String) input);
            model.hit(model.decrypt(shift)); 
            view.showResult(model.decrypt(shift));
        } catch (NumberFormatException e) {
            view.showResult("Invalid shift value!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ControllerName.CAESAR_CIPHER.getName();
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
    public String getEncryptedText() {
        return this.model.getEncryptedText(); 
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
