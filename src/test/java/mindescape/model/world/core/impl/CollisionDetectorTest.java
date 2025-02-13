package mindescape.model.world.core.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mindescape.model.world.core.api.CollisionDetector;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

/**
 * Unit tests for the {@link CollisionDetector} implementation.
 * This class tests the collision detection functionality in various scenarios
 * within a room containing different game objects.
 */
public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private Room bedRoom;
    private Player player;

    @BeforeEach
    public void setUp() {
        collisionDetector = new CollisionDetectorImpl();
        bedRoom = RoomImpl.createRooms().stream().filter(x -> x.getName().equals("bedroom")).findAny().get();
    }

    @Test
    public void testCollisionDetected() {
        assertEquals(collisionDetector.collisions(new Point2D(188, 127), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.of(bedRoom.getGameObjects().stream().filter(x -> "Bed".equals(x.getName())).findAny().get()));
    }

    @Test
    public void testNoCollisionDetected() {
        assertEquals(collisionDetector.collisions(new Point2D(118, 152), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.empty());
    }

    @Test
    public void testCollisionWithTable() {
        assertEquals(collisionDetector.collisions(new Point2D(200, 150), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.of(bedRoom.getGameObjects().stream().filter(x -> "Table".equals(x.getName())).findAny().get()));
    }

    @Test
    public void testCollisionWithChair() {
        assertEquals(collisionDetector.collisions(new Point2D(220, 180), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.of(bedRoom.getGameObjects().stream().filter(x -> "Chair".equals(x.getName())).findAny().get()));
    }

    @Test
    public void testCollisionWithWall() {
        assertEquals(collisionDetector.collisions(new Point2D(50, 50), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.of(bedRoom.getGameObjects().stream().filter(x -> "Wall".equals(x.getName())).findAny().get()));
    }

    @Test
    public void testNoCollisionAtBoundary() {
        assertEquals(collisionDetector.collisions(new Point2D(0, 0), Dimensions.TILE, bedRoom.getGameObjects()),
            Optional.empty());
    }
}
