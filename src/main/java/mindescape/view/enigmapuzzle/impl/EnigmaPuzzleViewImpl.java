package mindescape.view.enigmapuzzle.impl;

import javax.swing.*;

import mindescape.controller.enigmapuzzle.impl.EnigmaPuzzleControllerImpl;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView {

    private final static long serialVersionUID = 1L;

    private final List<ImageButton> buttons = new ArrayList<>();

    /**
     * Constructs an EnigmaPuzzleViewImpl with the specified number of rows and columns.
     * Initializes the layout as a GridLayout with the given rows and columns.
     * Creates and adds ImageButton instances to the view.
     *
     * @param rows the number of rows in the grid layout
     * @param cols the number of columns in the grid layout
     */
    public EnigmaPuzzleViewImpl(final int rows, final int cols) {
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows * cols; i++) {
            final ImageButton button = new ImageButton();
            buttons.add(button);
            add(button);
        }
    }

    /**
     * Sets the controller for the EnigmaPuzzleViewImpl.
     * This method adds the provided controller as an action listener to each button in the view.
     *
     * @param controller the EnigmaPuzzleControllerImpl to be set as the action listener for the buttons
     */
    public void setController(final EnigmaPuzzleControllerImpl controller) {
        for (final ImageButton button : buttons) {
            button.addActionListener(controller);
        }
    }

    /**
     * Retrieves the list of image buttons associated with the enigma puzzle view.
     *
     * @return a list of {@link ImageButton} objects.
     */
    public List<ImageButton> getButtons() {
        return buttons;
    }

    /**
     * Draws the enigma puzzle view.
     * This method is currently not implemented and will throw an UnsupportedOperationException if called.
     *
     * @throws UnsupportedOperationException if the method is called
     */
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    /**
     * Returns the JPanel instance associated with this view.
     *
     * @return the JPanel instance representing this view.
     */
    @Override
    public JPanel getPanel() {
        return this;

    }
}