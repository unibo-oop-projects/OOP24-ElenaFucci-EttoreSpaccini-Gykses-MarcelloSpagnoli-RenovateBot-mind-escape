package mindescape.model.world.core.impl;

import java.util.Optional;
import java.util.Set;

import mindescape.model.world.core.api.CollisionDetector;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;

public class CollisionDetectorImpl implements CollisionDetector{

    @Override
    public Optional<GameObject> collisions(Point2D position, Dimensions dim, Set<GameObject> roomObjects) {
        return roomObjects.stream().filter(x -> !(x instanceof Player)).filter(x -> areColliding(position, dim, x)).findFirst();
    }

    private boolean areColliding(Point2D position, Dimensions dim, GameObject obj) {
        return isInRect(position, obj.getPosition().get(), obj.getDimensions())
            || isInRect(new Point2D(position.x() + dim.width(), position.y()), obj.getPosition().get(), obj.getDimensions())
            || isInRect(new Point2D(position.x(), position.y() + dim.height()), obj.getPosition().get(), obj.getDimensions())
            || isInRect(new Point2D(position.x() + dim.width(), position.y() + dim.height()), obj.getPosition().get(), obj.getDimensions());
    }

    private boolean isInRect(Point2D point, Point2D objPos, Dimensions objDim) {
        return (point.x() >= objPos.x() && point.x() <= objPos.x() + objDim.width())
            || (point.y() >= objPos.y() && point.y() <= objPos.y() + objDim.height());
    }
 
}
