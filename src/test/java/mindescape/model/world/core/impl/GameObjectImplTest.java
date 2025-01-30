package mindescape.model.world.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mindescape.model.world.core.api.*;


public class GameObjectImplTest {

    private GameObjectImpl gameObject;
    private Point2D initialPosition;
    private String name;
    private Dimensions dimensions;

    @BeforeEach
    public void setUp() {
        initialPosition = new Point2D(1, 1);
        name = "TestObject";
        dimensions = new Dimensions(2, 2);
        gameObject = new GameObjectImpl(Optional.of(initialPosition), name, dimensions);
    }

    @Test
    public void testGetPosition() {
        Optional<Point2D> position = gameObject.getPosition();
        assertTrue(position.isPresent());
        assertEquals(initialPosition, position.get());
    }

    @Test
    public void testSetPosition() {
        Point2D newPosition = new Point2D(2, 2);
        gameObject.setPosition(newPosition);
        Optional<Point2D> position = gameObject.getPosition();
        assertTrue(position.isPresent());
        assertEquals(newPosition, position.get());
    }

    @Test
    public void testGetName() {
        assertEquals(name, gameObject.getName());
    }

    @Test
    public void testGetDimensions() {
        assertEquals(dimensions, gameObject.getDimensions());
    }
}