package mindescape.controller.enigmapassword.impl;

import javax.swing.JPanel;
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
public class EnigmaPasswordControllerImpl implements EnigmaPasswordController {

    private final MainController mainController;
    private final EnigmaPasswordModel model; 
    private final EnigmaPasswordView view; 

    /**
     * Constructs an {@code EnigmaPasswordControllerImpl} with the given model and main controller.
     *
     * @param model the password enigma model
     * @param mainController the main controller managing this instance
     */
    public EnigmaPasswordControllerImpl(final EnigmaPasswordModel model, final MainController mainController) {
        this.mainController = mainController;
        this.model = model; 
        this.view = new EnigmaPasswordViewImpl(this); 
    }

    /**
     * Handles user input and checks if the password is correct.
     *
     * @param input the user-provided password
     */
    @Override
    public void handleInput(final Object input) {
        if (input instanceof String) {
            this.view.showResult(this.model.hit(input)); 
        }
    }

    /**
     * Retrieves the name of the enigma.
     *
     * @return the name of the enigma as a string
     */
    @Override
    public String getName() {
        return this.model.getName(); 
    }

    /**
     * Retrieves the panel associated with this controller.
     *
     * @return the {@code JPanel} for the view
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
        this.mainController.setController(ControllerName.WORLD);
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public Model getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start() {

    }
}