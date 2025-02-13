package mindescape.model.world.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        gameObject = new GameObjectImpl(initialPosition, name, dimensions);
    }

    @Test
    void testGetPosition() {
        final Point2D position = gameObject.getPosition();
        assertEquals(initialPosition, position);
    }

    @Test
    void testSetPosition() {
        final Point2D newPosition = new Point2D(2, 2);
        gameObject.setPosition(newPosition);
        final Point2D position = gameObject.getPosition();
        assertNotNull(position);
        assertEquals(newPosition, position);
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
