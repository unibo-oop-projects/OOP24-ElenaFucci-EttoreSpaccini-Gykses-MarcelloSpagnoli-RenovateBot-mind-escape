package mindescape.controller.enigmapuzzle.impl;

import javax.swing.JPanel;
import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.controller.maincontroller.api.MainController;

/**
 * The EnigmaPuzzleControllerImpl class is the controller implementation for the enigma puzzle.
 */
public final class EnigmaPuzzleControllerImpl implements EnigmaPuzzleController {

    private final EnigmaPuzzleModelImpl model;
    private final EnigmaPuzzleViewImpl view;
    private final MainController mainController;

    /**
     * Constructs an EnigmaPuzzleControllerImpl with the specified model.
     *
     * @param model the model implementation for the enigma puzzle
     * @param mainController the main controller
     */
    public EnigmaPuzzleControllerImpl(final EnigmaPuzzleModelImpl model, final MainController mainController) {
        this.model = model;
        this.view = new EnigmaPuzzleViewImpl(model.getCols(), model.getRows(), this); 
        this.mainController = mainController;
        view.update(model.getPieces());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        if (input instanceof Integer) {
            final int value = (Integer) input; 
            if (this.model.hit(value)) {
                this.view.update(this.model.getPieces());

                if (this.model.isSolved()) {
                    this.quit();
                }
            }
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
        this.mainController.setController(ControllerName.WORLD, null);
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
