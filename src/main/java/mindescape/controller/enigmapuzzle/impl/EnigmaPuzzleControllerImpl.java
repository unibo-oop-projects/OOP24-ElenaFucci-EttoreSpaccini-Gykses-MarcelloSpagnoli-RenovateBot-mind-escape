package mindescape.controller.enigmapuzzle.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.model.enigma.enigmapuzzle.api.EnigmaPuzzleModel;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.view.enigmapuzzle.impl.ImageButton;
import mindescape.controller.maincontroller.api.MainController;


public class EnigmaPuzzleControllerImpl implements EnigmaPuzzleController {

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }

    private final EnigmaPuzzleModelImpl model;
    private final EnigmaPuzzleViewImpl view;
    private ImageButton firstButton = null;
    private final MainController mainController;


    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, EnigmaPuzzleViewImpl view, final MainController mainController) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        ImageButton clickedButton = (ImageButton) e.getSource();
        
        // Trova la posizione del pulsante cliccato
        int clickedIndex = view.getButtons().indexOf(clickedButton);
        int row = clickedIndex / model.getCols();
        int col = clickedIndex % model.getCols();

        if (firstButton == null) {
            firstButton = clickedButton;
        } else {
            int firstIndex = view.getButtons().indexOf(firstButton);
            int firstRow = firstIndex / model.getCols();
            int firstCol = firstIndex % model.getCols();
            
            model.swapPieces(firstRow, firstCol, row, col);
            
            view.updateView(model);

            firstButton = null;
        }
    }

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
        this.mainController.setController(this.mainController.findController("World"));
    }


}
