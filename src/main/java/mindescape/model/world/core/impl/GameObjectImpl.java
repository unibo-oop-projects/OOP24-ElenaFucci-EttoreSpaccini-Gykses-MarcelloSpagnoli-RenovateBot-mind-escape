package mindescape.model.world.core.impl;

import java.io.Serializable;
import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;

/**
 * Implementation of the {@link GameObject} interface representing a generic object
 * in the game world.
 * <p>
 * This class includes attributes such as position, name, and dimensions. It also
 * implements {@link Serializable} to allow objects to be saved and loaded.
 * </p>
 */
public class GameObjectImpl implements GameObject, Serializable {

    private static final long serialVersionUID = 1L;
    private Optional<Point2D> position;
    private final String name;
    private final Dimensions dimensions;

    /**
     * Constructs a new {@code GameObjectImpl} with the specified position, name, and dimensions.
     *
     * @param position   an {@link Optional} containing the initial position of the object,
     *                   or an empty {@link Optional} if the position is not set
     * @param name       the name of the game object
     * @param dimensions the dimensions of the game object
     */
    public GameObjectImpl(final Optional<Point2D> position, final String name, final Dimensions dimensions) {
        this.position = position;
        this.name = name;
        this.dimensions = dimensions;
    }

    /**
     * Returns the current position of the game object.
     *
     * @return an {@link Optional} containing the position if set, or an empty {@link Optional} otherwise
     */
    @Override
    public Optional<Point2D> getPosition() {
        return this.position;
    }

    /**
     * Sets the position of the game object.
     *
     * @param position an {@link Optional} containing the new position, or an empty {@link Optional} to unset the position
     */
    @Override
    public void setPosition(final Optional<Point2D> position) {
        this.position = position;
    }

    /**
     * Returns the name of the game object.
     *
     * @return the name of the object
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the dimensions of the game object.
     *
     * @return the {@link Dimensions} of the object
     */
    @Override
    public Dimensions getDimensions() {
        return this.dimensions;
    }
}
