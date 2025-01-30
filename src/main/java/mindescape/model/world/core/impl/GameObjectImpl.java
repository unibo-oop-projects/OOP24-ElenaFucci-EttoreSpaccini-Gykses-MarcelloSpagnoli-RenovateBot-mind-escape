package mindescape.model.world.core.impl;

import java.util.Optional;

import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;

public class GameObjectImpl implements GameObject {
    private Optional<Point2D> position;
    private String name;

    public GameObjectImpl(Optional<Point2D> position, String name) {
        this.position = position;
        this.name = name;
    }

    @Override
    public Optional<Point2D> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = Optional.of(position);
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
