package mindescape.view.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * This is a utility class to help manage images transformations, it is used to render the tiles.
 */
public class ImageTransformer {
    
    /**
     * Resizes an image using a scaling factor.
     * @param image the image to adapt.
     * @param scaling the scaling factor.
     * @return the scaled BufferedImage.
     */
    public BufferedImage adapt(final BufferedImage image, final double scaling) {
        final int newWidth = (int) (image.getWidth() * scaling);
        final int newHeight = (int) (image.getHeight() * scaling);
        final BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, image.getType());
        final Graphics2D g2d = scaledImage.createGraphics();
        final AffineTransform at = AffineTransform.getScaleInstance(scaling, scaling);
        g2d.drawImage(image, at, null);
        g2d.dispose();
        return scaledImage;
    }

    /**
     * Rotates an image.
     * @param image the image to to rotate.
     * @param angle the angle to rotate.
     * @return the rotated BufferedImage.
     */
    public BufferedImage rotateImage(final BufferedImage image, final double angle) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);
        final BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }

    /**
     * Flips an image horizontally.
     * @param image the image to flipped.
     * @return the flipped BufferedImage.
     */
    public BufferedImage flipImageHorizontally(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1); // Flip horizontally
        transform.translate(-width, 0); // Move image back to the correct position
        final BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return flippedImage;
    }
}
