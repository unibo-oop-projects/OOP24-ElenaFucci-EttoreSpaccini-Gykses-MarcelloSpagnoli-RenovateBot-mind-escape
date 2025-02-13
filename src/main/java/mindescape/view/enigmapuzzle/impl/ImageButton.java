package mindescape.view.enigmapuzzle.impl;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * ImageButton is a custom JButton that allows setting an image to be displayed on the button.
 * It extends the JButton class and overrides the paintComponent method to draw the image.
 */
public final class ImageButton extends JButton {

    private static final long serialVersionUID = 1L;
    private transient Image image;

    /**
     * Sets the image for this button and repaints the component.
     *
     * @param image the new image to be set
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The image is not modified externally")
    public void setImage(final Image image) {
        this.image = image;
        repaint();
    }

    /**
     * Overrides the paintComponent method to draw an image on the component.
     * 
     * @param g the Graphics object used for drawing the image
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
