package mindescape.model.world.core.api;

import java.util.Optional;
import java.util.Set;

public interface CollisionDetector {
    Optional<GameObject> collisions(Point2D position, Dimensions dim, Set<GameObject> roomObjects);
}
