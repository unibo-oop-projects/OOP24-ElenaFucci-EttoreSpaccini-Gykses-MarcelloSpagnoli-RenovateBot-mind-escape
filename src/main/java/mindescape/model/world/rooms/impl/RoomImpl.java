package mindescape.model.world.rooms.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;

public class RoomImpl implements Room {

    private final Dimensions dimensions;

    private final Set<GameObject> gameObjects;

    public RoomImpl(Dimensions dimensions, Set<GameObject> gameObjects) {
        this.dimensions = dimensions;
        this.gameObjects = new HashSet<>();
        gameObjects.stream().forEach(x -> addGameObject(x));
    }

    //TODO when PlayerImpl will be ready
    @Override
    public boolean isPlayerPresent() {
        return true;
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObject.getPosition().isPresent() && isPositionValid(gameObject.getPosition().get(), gameObject.getDimensions())) {
            gameObjects.add(gameObject);
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    @Override
    public Set<GameObject> getGameObjects() {
        return Collections.unmodifiableSet(this.gameObjects);
    }

    public boolean isPositionValid(Point2D pos, Dimensions dim) {
        return pos.x() >= 0 &&
            pos.y() >= 0 && 
            pos.x() + dim.height() <= this.dimensions.height() &&
            pos.y() + dim.height() <= this.dimensions.height();
    }
}
