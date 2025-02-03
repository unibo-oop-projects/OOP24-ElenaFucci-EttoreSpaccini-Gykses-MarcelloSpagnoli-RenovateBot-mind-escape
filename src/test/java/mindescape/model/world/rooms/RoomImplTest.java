package mindescape.model.world.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mindescape.model.world.rooms.impl.RoomImpl;

/**
 * Test class for {@link RoomImpl}.
 */
public class RoomImplTest {
    RoomImpl roomImpl;
    String roomFilePath;

    @BeforeEach
    void setUp() {
        roomFilePath = "src/main/resources/bedroom.tmx";
        roomImpl = new RoomImpl(roomFilePath);
    }

    @Test
    void testaddGameObject() {
        
    }


}
