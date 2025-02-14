package mindescape.view.enigmapuzzle.impl;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A class that extends JButton to display an image resized to the button's size.
 */
public final class ImageButton extends JButton {

    private static final long serialVersionUID = 1L;
    private transient Image image;

    /**
     * Sets the image for this button and repaints the component.
     *
     * @param image the new image to be set
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "L'immagine non viene modificata esternamente")
    public void setImage(final Image image) {
        this.image = image;
        repaint();
    }

    /**
     * Overrides the paintComponent method to draw an image on the button.
     * 
     * @param g the Graphics object used to draw the image
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
