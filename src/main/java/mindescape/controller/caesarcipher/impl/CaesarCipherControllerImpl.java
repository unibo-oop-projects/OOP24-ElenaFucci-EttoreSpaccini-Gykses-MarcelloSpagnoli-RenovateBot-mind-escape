package mindescape.controller.caesarcipher.impl;

import javax.swing.JPanel;
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
public class CaesarCipherControllerImpl implements CaesarCipherController{

    private final MainController mainController;
    private final CaesarCipherModel model;
    private final CaesarCipherView view; 

    /**
     * Constructs a {@code CaesarCipherControllerImpl} with the given model and main controller.
     *
     * @param model the Caesar Cipher model
     * @param mainController the main controller managing this instance
     */
    public CaesarCipherControllerImpl(final CaesarCipherModel model, final MainController mainController) {
        this.mainController = mainController;
        this.model = model; 
        this.view = new CaesarCipherViewImpl(this); 
    }

    /**
     * Handles user input for the shift value and updates the view with the encrypted result.
     *
     * @param input the shift value provided by the user
     */
    @Override
    public void handleInput(Object input) {
        try {
            int shift = Integer.parseInt((String)input);
            view.showResult(model.decrypt(shift));
        } catch (NumberFormatException e) {
            view.showResult("Invalid shift value!");
        }
    }

    /**
     * Retrieves the name of this controller.
     *
     * @return the name of the controller
     */
    @Override
    public String getName() {
        return this.model.getName(); 
    }

    /**
     * Retrieves the associated panel for this controller.
     *
     * @return the {@code JPanel} used for the view
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel(); 
    }

    /**
     * Quits the current controller and switches to another controller in the main application.
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD, null);
    }

    @Override
    public String getEncryptedText() {
        return this.model.getEncryptedText(); 
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public void start() {
    }
    
}
