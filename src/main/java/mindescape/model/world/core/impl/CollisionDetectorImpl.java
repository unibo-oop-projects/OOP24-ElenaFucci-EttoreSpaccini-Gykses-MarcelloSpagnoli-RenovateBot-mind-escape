package mindescape.model.world.core.impl;

import java.util.Optional;
import java.util.Set;
import mindescape.model.world.core.api.CollisionDetector;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;

/**
 * Implementation of the CollisionDetector interface.
 */
public final class CollisionDetectorImpl implements CollisionDetector {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> collisions(final Point2D position, final Dimensions dim, final Set<GameObject> roomObjects) {
        return roomObjects.stream()
        .filter(x -> !(x instanceof Player))
        .filter(x -> areColliding(position, dim, x))
        .findFirst();
    }

    /*
     * Checks if two objects are colliding based on their positions and dimensions.
     *
     * position the position of the first object
     * dim the dimensions of the first object
     * obj the second object to check collision against
     * true if the objects are colliding, false otherwise
     */
    private boolean areColliding(final Point2D position, final Dimensions dim, final GameObject obj) {
        final Point2D objPos = obj.getPosition();
        final Dimensions objDim = obj.getDimensions();
        return position.x() < objPos.x() + objDim.width() 
            && position.x() + dim.width() > objPos.x()
            &&   position.y() < objPos.y() + objDim.height()
            &&   position.y() + dim.height() > objPos.y();
    }
}
