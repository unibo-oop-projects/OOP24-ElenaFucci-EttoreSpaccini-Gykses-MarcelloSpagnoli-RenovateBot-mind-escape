package mindescape.model.world.items.interactable.api;

import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.items.interactable.impl.InteractableFactoryImpl;
import mindescape.model.world.items.interactable.impl.PickableImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class InteractableFactoryTest {

    private InteractableFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new InteractableFactoryImpl();
    }

    @Test
    public void testCreatePickable() {
        Pickable result = factory.createPickable("item", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), "description", 1);
        assertNotNull(result);
        assertEquals("item", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals(1, result.getId());
    }

    @Test
    public void testCreateSimpleDoor() {
        Room room = new RoomImpl("Room1");
        Door result = factory.createSimpleDoor("door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), room);
        assertNotNull(result);
        assertEquals("door", result.getName());
        //assertEquals(room, result.getDestinationRoom());
    }

    @Test
    public void testCreateDoorLockedWithPickable() {
        Room room = new RoomImpl("Room1");
        Door result = factory.createDoorLockedWithPickable("door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), 1, room);
        assertNotNull(result);
        assertEquals("door", result.getName());
    }

    @Test
    public void testCreateDoorLockedWithEnigma() {
        Room room = new RoomImpl("Room1");
        Enigma enigma = new EnigmaPasswordModelImpl("EnigmaPassword", "1234"); 
        Door result = factory.createDoorLockedWithEnigma("door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), enigma, room);
        assertNotNull(result);
        assertEquals("door", result.getName());
    }

    @Test
    public void testCreateUnpickableWithEnigma() {
        Enigma enigma = new CaesarCipherModelImpl("Caesar Cipher", 3);
        Pickable reward = new PickableImpl(Optional.of(new Point2D(1, 1)), "reward", new Dimensions(1, 1), "reward description", 2);
        Unpickable result = factory.createUnpickableWithEnigma("object", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), enigma, Optional.of(reward));
        assertNotNull(result);
        assertEquals("object", result.getName());
    }

    @Test
    public void testCreateLockedUnpickable() {
        Pickable reward = new PickableImpl(Optional.of(new Point2D(1, 1)), "reward", new Dimensions(1, 1), "reward description", 2);
        Unpickable result = factory.createLockedUnpickable("object", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), 1, reward);
        assertNotNull(result);
        assertEquals("object", result.getName());
    }

    @Test
    public void testCreateUnpickable() {
        Pickable reward = new PickableImpl(Optional.of(new Point2D(1, 1)), "reward", new Dimensions(1, 1), "reward description", 2);
        Unpickable result = factory.createUnpickable("object", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), Optional.of(reward));
        assertNotNull(result);
        assertEquals("object", result.getName());
    }
}
