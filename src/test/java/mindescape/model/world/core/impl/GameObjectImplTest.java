package mindescape.model.world.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;

/**
 * Test class for {@link GameObjectImpl}.
 */
class GameObjectImplTest {

    private GameObjectImpl gameObject;
    private Point2D initialPosition;
    private String name;
    private Dimensions dimensions;

    @BeforeEach
    void setUp() {
        initialPosition = new Point2D(1, 1);
        name = "TestObject";
        dimensions = new Dimensions(2, 2);
        gameObject = new GameObjectImpl(Optional.of(initialPosition), name, dimensions);
    }

    @Test
    void testGetPosition() {
        final Optional<Point2D> position = gameObject.getPosition();
        assertTrue(position.isPresent());
        assertEquals(initialPosition, position.get());
    }

    @Test
    void testSetPosition() {
        final Point2D newPosition = new Point2D(2, 2);
        gameObject.setPosition(newPosition);
        final Optional<Point2D> position = gameObject.getPosition();
        assertTrue(position.isPresent());
        assertEquals(newPosition, position.get());
    }

    @Test
    void testGetName() {
        assertEquals(name, gameObject.getName());
    }

    @Test
    void testGetDimensions() {
        assertEquals(dimensions, gameObject.getDimensions());
    }
}
