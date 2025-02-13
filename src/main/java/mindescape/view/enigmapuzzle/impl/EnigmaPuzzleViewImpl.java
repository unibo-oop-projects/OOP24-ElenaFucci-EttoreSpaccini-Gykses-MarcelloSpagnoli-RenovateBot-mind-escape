package mindescape.view.enigmapuzzle.impl;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the enigma puzzle.
 */
public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView {

    private static final long serialVersionUID = 1L;
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
     * @param controller the controller that manages the enigma puzzle view
     */
    public EnigmaPuzzleViewImpl(final int cols, final int rows, final EnigmaPuzzleController controller) {
        this.rows = rows;
        this.cols = cols;
        final BufferedImage img;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("puzzle/puzzle.jpg"));
        } catch (final IOException e) {
            throw new RuntimeException("image not found");
        }
        this.image = img;
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
     * Updates the puzzle view with the provided pieces.
     *
     * @param pieces a 2D array representing the positions of the puzzle pieces.
     *               Each element in the array corresponds to the position of a piece
     *               in the original image.
     */
    public void update(final Integer[][] pieces) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                final int imgPos = pieces[i][j];
                buttons.get(i * rows + j).setImage(image.getSubimage(
                    imgPos / this.rows * image.getWidth() / this.cols,
                    imgPos % this.cols * image.getHeight() / this.rows,
                    image.getWidth() / pieces.length,
                    image.getHeight() / pieces.length
                ));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }
}
