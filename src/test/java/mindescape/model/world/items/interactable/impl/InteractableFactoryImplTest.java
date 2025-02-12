package mindescape.model.world.items.interactable.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;

public class InteractableFactoryImplTest {

    private InteractableFactoryImpl factory;

    @BeforeEach
    public void setUp() {
        factory = new InteractableFactoryImpl();
    }

    @Test
    public void testCreatePickable() {
        Pickable pickable = factory.createPickable("Key", Optional.of(new Point2D(1, 1)), new Dimensions(1, 1), "A small key", 1);
        assertNotNull(pickable);
        assertEquals("Key", pickable.getName());
    }

    @Test
    public void testCreateDoorLockedWithPickable() {
        Room room = new RoomImpl("Room1");
        Door door = factory.createDoorLockedWithPickable("Door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 2), 1, room, Optional.of(new Point2D(2, 2)));
        assertNotNull(door);
        assertEquals("Door", door.getName());
    }

    @Test
    public void testCreateDoorLockedWithEnigma() {
        Room room = new RoomImpl("Room1");
        Enigma enigma = new EnigmaPasswordModelImpl("Solve this", "1234");
        Door door = factory.createDoorLockedWithEnigma("Door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 2), enigma, room, Optional.of(new Point2D(2, 2)));
        assertNotNull(door);
        assertEquals("Door", door.getName());
    }

    @Test
    public void testCreateUnpickableWithEnigma() {
        Enigma enigma = new CaesarCipherModelImpl("Solve this", 3);
        Unpickable unpickable = factory.createUnpickableWithEnigma("Chest", Optional.of(new Point2D(1, 1)), new Dimensions(2, 2), enigma, Optional.empty());
        assertNotNull(unpickable);
        assertEquals("Chest", unpickable.getName());
    }

    @Test
    public void testCreateLockedUnpickable() {
        Pickable reward = new PickableImpl(Optional.of(new Point2D(1, 1)), "Reward", new Dimensions(1, 1), "A reward", 2);
        Unpickable unpickable = factory.createLockedUnpickable("Chest", Optional.of(new Point2D(1, 1)), new Dimensions(2, 2), 1, reward);
        assertNotNull(unpickable);
        assertEquals("Chest", unpickable.getName());
    }

    @Test
    public void testCreateUnpickable() {
        Pickable reward = new PickableImpl(Optional.of(new Point2D(1, 1)), "Reward", new Dimensions(1, 1), "A reward", 2);
        Unpickable unpickable = factory.createUnpickable("Chest", Optional.of(new Point2D(1, 1)), new Dimensions(2, 2), Optional.of(reward));
        assertNotNull(unpickable);
        assertEquals("Chest", unpickable.getName());
    }

    @Test
    public void testCreateSimpleDoor() {
        Room room = new RoomImpl("Room1");
        Door door = factory.createSimpleDoor("Door", Optional.of(new Point2D(1, 1)), new Dimensions(1, 2), room, Optional.of(new Point2D(2, 2)));
        assertNotNull(door);
        assertEquals("Door", door.getName());
    }
}