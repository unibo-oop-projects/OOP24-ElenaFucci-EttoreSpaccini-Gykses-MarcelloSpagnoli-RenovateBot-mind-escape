package mindescape.model.world.core.api;

import java.io.Serializable;

/**
 * A record that represents a point in a 2-dimensional space.
 * 
 * @param x the x-coordinate of the point
 * @param y the y-coordinate of the point
 */
public record Point2D(double x, double y) implements Serializable {
} 
