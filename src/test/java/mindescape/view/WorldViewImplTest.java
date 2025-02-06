package mindescape.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tiledreader.FileSystemTiledReader;

import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import mindescape.view.impl.WorldViewImpl;



public class WorldViewImplTest {

    WorldViewImpl worldViewImpl;
    Room bedRoom;

    @BeforeEach
    public void setUp() {
        worldViewImpl = new WorldViewImpl(null, null);
        bedRoom = RoomImpl.createRooms().stream()
            .filter(room -> room.getName().equals("bedroom"))
            .findFirst().get();
    }

    @Test
    public void testLoadTileset() {
        int height, width;
        height = new FileSystemTiledReader().getMap(bedRoom.getSource()).getTilesets()
            .getFirst().getTile(0, 0).getID();
        System.out.println("X: " + height );
    }

    @Test
    public void testAnotherFunctionality() {
        // Test another functionality of WorldViewImpl
    }

    // Add more test methods as needed
}