package mindescape.model.world.items.noninteractable.impl;

import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.noninteractable.api.NonInteractable;

public class NonInteractableImpl extends GameObjectImpl implements NonInteractable {

    public NonInteractableImpl(final Optional<Point2D> position, final String name, final Dimensions dimensions) {
        super(position, name, dimensions);
    }

}
