package mindescape.model.world.items.noninteractable.impl;

import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.noninteractable.api.NonInteractable;

/**
 * Implementation of the {@link NonInteractable} interface.
 * This class represents a game object that cannot be interacted with.
 * It extends {@link GameObjectImpl}, inheriting its position, name, and dimensions.
 */
public class NonInteractableImpl extends GameObjectImpl implements NonInteractable {

    /**
     * Constructs a NonInteractable object with a specified position, name, and dimensions.
     *
     * @param position   the optional position of the object in the game world, or empty if not placed
     * @param name       the name of the object
     * @param dimensions the dimensions of the object
     */
    public NonInteractableImpl(final Optional<Point2D> position, final String name, final Dimensions dimensions) {
        super(position, name, dimensions);
    }

}

