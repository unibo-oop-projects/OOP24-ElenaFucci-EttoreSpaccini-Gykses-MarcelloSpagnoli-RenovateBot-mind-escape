package mindescape.model.world.core.api;

import java.io.Serializable;

/**
 * Represents the dimensions of an object with a specified width and height.
 * Both width and height must be positive integers.
 *
 * @param width the width of the object, must be a positive integer
 * @param height the height of the object, must be a positive integer
 * @throws IllegalArgumentException if either width or height is not positive
 */
public record Dimensions(double width, double height) implements Serializable {
    public final static Dimensions TILE = new Dimensions(16, 16);

    public Dimensions {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
    }
}
