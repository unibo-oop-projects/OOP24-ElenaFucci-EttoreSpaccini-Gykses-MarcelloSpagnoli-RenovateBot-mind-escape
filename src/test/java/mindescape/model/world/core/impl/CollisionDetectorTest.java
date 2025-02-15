package mindescape.model.world.core.impl;

import mindescape.model.world.core.api.CollisionDetector;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import mindescape.model.world.core.api.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

/**
 * Test class for {@link CollisionDetector}.
 */
final class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private Set<GameObject> roomObjects;
    private final Room bedroom = RoomImpl.createRooms().stream()
        .filter(room -> "bedroom".equals(room.getName()))
        .findFirst().get();

    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetectorImpl(); // Assuming CollisionDetectorImpl is the implementation
        roomObjects = bedroom.getGameObjects();
    }

    // CHECKSTYLE: MagicNumber OFF
    // Magic numbers in a test are acceptable
    @Test
    void testBed() {
        assertTrue(collisionDetector.collisions(new Point2D(177, 100),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testChair() {
        assertTrue(collisionDetector.collisions(new Point2D(65, 170),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testWalls() {
        assertTrue(collisionDetector.collisions(new Point2D(24, 28),
            Dimensions.TILE, roomObjects).isPresent());
        assertTrue(collisionDetector.collisions(new Point2D(6, 104),
            Dimensions.TILE, roomObjects).isPresent());
        assertTrue(collisionDetector.collisions(new Point2D(42, 249),
            Dimensions.TILE, roomObjects).isPresent());
        assertTrue(collisionDetector.collisions(new Point2D(156, 249),
            Dimensions.TILE, roomObjects).isPresent());
        assertTrue(collisionDetector.collisions(new Point2D(246, 195),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testBoxes() {
        assertTrue(collisionDetector.collisions(new Point2D(43, 70),
            Dimensions.TILE, roomObjects).isPresent());
        assertTrue(collisionDetector.collisions(new Point2D(119, 113),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testBarrel() {
        assertTrue(collisionDetector.collisions(new Point2D(225, 73),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testChest() {
        assertTrue(collisionDetector.collisions(new Point2D(175, 200),
            Dimensions.TILE, roomObjects).isPresent());
    }

    @Test
    void testNoCollision() {
        assertFalse(collisionDetector.collisions(new Point2D(144, 83),
            Dimensions.TILE, roomObjects).isPresent());
        assertFalse(collisionDetector.collisions(new Point2D(113, 185),
            Dimensions.TILE, roomObjects).isPresent());
        assertFalse(collisionDetector.collisions(new Point2D(48, 106),
            Dimensions.TILE, roomObjects).isPresent());
    }
    // CHECKSTYLE: MagicNumber ON
}


