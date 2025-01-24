package mindescape.api;

import java.util.Optional;

/**
 * The GameObject interface represents an object in the game with a position and a name.
 */
public interface GameObject {
    
    /**
     * Retrieves the position of the game object.
     *
     * @return an {@link Optional} containing the {@link Point2D} position of the game object,
     *         or an empty {@link Optional} if the position is not set.
     */
    Optional<Point2D> getPosition();
 
    /**
     * Sets the position of the game object.
     *
     * @param position the new position of the game object as a Point2D object
     */
    void setPosition(Point2D position);

    /**
     * Retrieves the name of the game object.
     *
     * @return the name of the game object as a String.
     */
    String getName();
}
