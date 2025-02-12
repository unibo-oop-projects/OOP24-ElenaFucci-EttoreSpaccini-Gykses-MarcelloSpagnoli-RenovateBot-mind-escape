package mindescape.model.world.rooms;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.impl.DoorLockedWithEnigma;
import mindescape.model.world.items.interactable.impl.PickableImpl;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;

public class RoomImplTest {
    private List<Room> rooms;
    private Room bedroom; 

    @BeforeEach
    public void setUp() {
        rooms = RoomImpl.createRooms();
        bedroom = rooms.stream()
            .filter(room -> room.getName().equals("bedroom"))
            .findFirst().get();
        bedroom.getGameObjects().forEach(x -> System.out.println(x.getName()));
    }

    @Test
    public void testIsPlayerPresent() {
        assertFalse(bedroom.isPlayerPresent());
    }

    @Test
    public void testAddGameObject() {
        int size = bedroom.getGameObjects().size();
        bedroom.addGameObject(new GameObjectImpl(new Point2D(0, 0), "dummy", Dimensions.TILE));
        assertEquals(size + 1, bedroom.getGameObjects().size());
    }

    @Test
    public void testRemoveGameObject() {
        int size = bedroom.getGameObjects().size();
        Pickable obj = new PickableImpl(new Point2D(0, 0), "", Dimensions.TILE, "", 0);
        bedroom.addGameObject(obj);
        assertEquals(size + 1, bedroom.getGameObjects().size());
        bedroom.removeGameObject(obj);
        assertEquals(size, bedroom.getGameObjects().size());
    }

    @Test
    public void testGetGameObjects() {
        assertEquals(bedroom.getGameObjects().size(), 8);
        assertEquals(bedroom.getGameObjects().stream().filter(obj -> obj instanceof DoorLockedWithEnigma).count(), 1);
    }

    @Test
    public void testIsPositionValid() {
        assertFalse(bedroom.isPositionValid(new Point2D(0, -1), Dimensions.TILE));
        assertFalse(bedroom.isPositionValid(new Point2D(254., 254), Dimensions.TILE));
        assertTrue(bedroom.isPositionValid(new Point2D(120, 120), Dimensions.TILE));
    }

    @Test
    public void testGetName() {
        assertEquals("bedroom", bedroom.getName());
    }

}