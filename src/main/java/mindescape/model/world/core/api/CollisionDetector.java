package mindescape.model.world.core.api;

import java.util.Optional;
import java.util.Set;

public interface CollisionDetector {
    /**
     * Given the position and the dimension of an object, determines if it collides with any object of the room.
     * If it does it return the object, which later may be used for an interaction.
     * @param position the position of the object
     * @param dim its dimensions
     * @param roomObjects the set of the room's object
     * @return an optional of the object that is colliding with
     */
    Optional<GameObject> collisions(Point2D position, Dimensions dim, Set<GameObject> roomObjects);
}

