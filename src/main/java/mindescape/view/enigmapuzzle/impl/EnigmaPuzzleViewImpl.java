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
     * @param controller the controller that manages the enigma puzzle view
     */
    public EnigmaPuzzleViewImpl(final int cols, final int rows, final EnigmaPuzzleController controller) {
        this.rows = rows;
        this.cols = cols;
        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("puzzle/puzzle.jpg"));
        } catch (IOException e) {
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
            button.setText("" + i);
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

    public void update(final Integer[][] pieces) {
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
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }
}


/*
private void divideImageIntoPieces() {
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
