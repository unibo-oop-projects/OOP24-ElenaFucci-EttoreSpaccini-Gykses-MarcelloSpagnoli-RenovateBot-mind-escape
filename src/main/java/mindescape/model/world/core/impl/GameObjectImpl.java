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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dimensions == null) ? 0 : dimensions.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameObjectImpl other = (GameObjectImpl) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dimensions == null) {
            if (other.dimensions != null)
                return false;
        } else if (!dimensions.equals(other.dimensions))
            return false;
        return true;
    }
}
