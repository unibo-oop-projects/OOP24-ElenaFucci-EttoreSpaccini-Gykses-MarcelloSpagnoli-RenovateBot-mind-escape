package mindescape.controller.enigmapuzzle.impl;

import mindescape.view.enigmapuzzle.impl.ImageButton;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.controller.maincontroller.api.MainController;

public class EnigmaPuzzleControllerImpl implements ActionListener, EnigmaPuzzleController {

    private final EnigmaPuzzleModelImpl model;
    private EnigmaPuzzleViewImpl view;
    private ImageButton firstButton = null;
    private final MainController mainController;
    /**
     * Constructs an EnigmaPuzzleControllerImpl with the specified model.
     *
     * @param model the model implementation for the enigma puzzle
     */
    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, MainController mainController) {
        this.model = model;
        this.view = new EnigmaPuzzleViewImpl(model.getRows(), model.getCols());  // Create the view with rows and cols
        this.view.setController(this);  // Set the controller for the view
        this.mainController = mainController;
        this.updateView();  // Initialize the view
    }

    /**
     * Handles the action event triggered when an image button is clicked.
     * This method determines the index of the clicked button and its position
     * in the grid (row and column). If this is the first button clicked, it
     * stores the button for future reference. If this is the second button
     * clicked, it swaps the pieces in the model based on the positions of the
     * first and second buttons, updates the view, and resets the first button
     * reference.
     *
     * @param e the action event triggered by clicking an image button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ImageButton clickedButton = (ImageButton) e.getSource();

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

            updateView();

            firstButton = null;
        }
        if (model.isSolved()) {
            this.quit();
        }
    }

    /**
     * Updates the view to reflect the current state of the model.
     * Iterates through each piece in the model and updates the corresponding
     * button in the view with the appropriate image. After updating all buttons,
     * the view is revalidated and repainted to ensure the changes are displayed.
     */
    private void updateView() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                ImageButton button = view.getButtons().get(i * model.getCols() + j);
                button.setImage(model.getPiece(i, j));
            }
        }

        view.revalidate();
        view.repaint();
    }

    /**
     * Retrieves the name from the model.
     *
     * @return the name as a String.
     */
    public String getName() {
        return this.model.getName();
    }

    public JPanel getPanel() {
        return this.view.getPanel();
    }

    public static void main(String[] args) {
        Image image = new ImageIcon("C:\\\\Users\\\\Elena\\\\Desktop\\\\MVC\\\\puzzlegame\\\\mattarella.jpg").getImage();  // Load the image
        EnigmaPuzzleModelImpl model = new EnigmaPuzzleModelImpl(3, 3, image, "Puzzle Game");
        EnigmaPuzzleControllerImpl controller = new EnigmaPuzzleControllerImpl(model, null);  // Pass the

        // Test the initialization of the view and controller
        JFrame frame = new JFrame("Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);  // Set an initial size for the JFrame
        frame.setLocationRelativeTo(null);

        frame.add(controller.getPanel());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        // Handle input (to be implemented if needed)
    }
    
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
        return null;  // Return the model (to be implemented as needed)
    }

    @Override
    public void start() {    }
}

