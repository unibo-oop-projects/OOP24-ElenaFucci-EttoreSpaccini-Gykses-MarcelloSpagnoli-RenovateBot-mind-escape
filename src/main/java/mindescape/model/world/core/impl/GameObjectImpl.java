package mindescape.model.world.core.impl;

import java.io.Serializable;
import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;

public class GameObjectImpl implements GameObject, Serializable {
    
    private static final long serialVersionUID = 1L;
    private Optional<Point2D> position;
    private final String name;
    private final Dimensions dimensions;

    public GameObjectImpl(final Optional<Point2D> position, final String name, final Dimensions dimensions) {
        this.position = position;
        this.name = name;
        this.dimensions = dimensions;
    }

    @Override
    public Optional<Point2D> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Point2D position) {
        this.position = Optional.of(position);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Dimensions getDimensions() {
        return this.dimensions;
    }
}
