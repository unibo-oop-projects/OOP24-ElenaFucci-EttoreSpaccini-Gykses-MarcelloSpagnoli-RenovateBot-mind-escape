package mindescape.controller.enigmapuzzle.impl;

import javax.swing.JPanel;

import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.controller.maincontroller.api.MainController;

public class EnigmaPuzzleControllerImpl implements EnigmaPuzzleController {

    private final EnigmaPuzzleModelImpl model;
    private EnigmaPuzzleViewImpl view;
    private final MainController mainController;
    /**
     * Constructs an EnigmaPuzzleControllerImpl with the specified model.
     *
     * @param model the model implementation for the enigma puzzle
     */
    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, MainController mainController) {
        this.model = model;
        this.view = new EnigmaPuzzleViewImpl(model.getCols(), model.getRows(), this); 
        this.mainController = mainController;
        view.update(model.getPieces());  // Initialize the view
    }
    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        input = (Integer) input;
        if(this.model.hit(input)){
            this.view.update(this.model.getPieces());
            if(this.model.isSolved()) {
                this.quit();
            }
        }
    }
    @Override
    public String getName() {
        return this.model.getName();
    }
    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD, null);
    }
    @Override
    public boolean canSave() {
        return true;
    }
    @Override
    public Model getModel() {
        return this.model;
    }
    @Override
    public void start() {
    }
    

}