package mindescape.view.enigmapuzzle.impl;

import javax.swing.*;

import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView {

    private final static long serialVersionUID = 1L;

    private final List<ImageButton> buttons = new ArrayList<>();

    private final BufferedImage image;
    private final int rows, cols;
    /**
     * Constructs an EnigmaPuzzleViewImpl with the specified number of rows and columns.
     * Initializes the layout as a GridLayout with the given rows and columns.
     * Creates and adds ImageButton instances to the view.
     *
     * @param rows the number of rows in the grid layout
     * @param cols the number of columns in the grid layout
     */
    public EnigmaPuzzleViewImpl(final int cols, final int rows, final EnigmaPuzzleController controller, final BufferedImage image) {
        this.rows = rows;
        this.cols = cols;
        this.image = image;
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows * cols; i++) {
            final ImageButton button = new ImageButton();
            buttons.add(button);
            button.addActionListener(e -> {
                controller.handleInput(buttons.indexOf(button));
            });
            add(button);
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

    public void update(Integer[][] pieces) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                int imgPos = pieces[i][j];
                buttons.get(i * rows + j).setImage(image.getSubimage(
                    (imgPos / this.rows) * (image.getWidth() / this.cols),
                    (imgPos % this.cols) * (image.getHeight() / this.rows),
                    image.getWidth() / pieces.length,
                    image.getHeight() / pieces.length
                ));
            }
        }

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


/*    private void divideImageIntoPieces() {
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);
        int pieceWidth = width / COLS;
        int pieceHeight = height / ROWS;
    
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
    
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                pieces[i][j] = bufferedImage.getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
            }
        }
    }
     */