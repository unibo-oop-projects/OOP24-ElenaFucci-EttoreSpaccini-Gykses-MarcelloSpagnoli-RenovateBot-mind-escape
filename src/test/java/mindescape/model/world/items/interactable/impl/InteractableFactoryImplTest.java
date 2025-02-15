package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.player.impl.PlayerImpl;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

class InteractableFactoryImplTest {

    private static final String KEY = "Key";
    private static final String PASSWORD = "123";

    private InteractableFactoryImpl factory;
    private Player player; 
    private List<Room> rooms;

    // CHECKSTYLE: MagicNumber OFF
    // Magic numbers in a test are acceptable
    @BeforeEach
    void setUp() {
        this.factory = new InteractableFactoryImpl();
        this.rooms = RoomImpl.createRooms();
        this.player = new PlayerImpl(new Point2D(1, 1), "Spaccini", new Dimensions(1, 1), this.rooms.getFirst());
    }

    @Test
    void testCreatePickable() {
        final Pickable pickable = factory.createPickable(KEY, new Point2D(1, 1), new Dimensions(1, 1), "A small key", 1);
        assertNotNull(pickable);
        assertEquals(KEY, pickable.getName());
        player.getInventory().addItems(pickable);
        assertTrue(player.getInventory().getItems().contains(pickable));
    }

    @Test
    void testCreateDoorLockedWithPickable() {
        final Pickable key = factory.createPickable(KEY, new Point2D(1, 1), new Dimensions(1, 1), "A small key", 1);
        final Door door = factory.createDoorLockedWithPickable("Locked Door", new Point2D(2, 2), new Dimensions(2, 3), 1, this.rooms.get(1), new Point2D(3, 3));
        assertNotNull(door);
        assertFalse(door.isUnlocked());
        player.getInventory().addItems(key);
        door.onAction(player);
        assertTrue(door.isUnlocked());
        assertEquals(player.getCurrentRoom(), this.rooms.get(1));
    }

    @Test
    void testCreateDoorLockedWithEnigma() {
        final Enigma enigma = new EnigmaPasswordModelImpl("password", PASSWORD);
        final Door door = factory.createDoorLockedWithEnigma("Enigma Door", new Point2D(2, 2), new Dimensions(2, 3), enigma, this.rooms.get(2), new Point2D(3, 3));
        assertNotNull(door);
        assertFalse(door.isUnlocked());
        enigma.hit("123");
        door.onAction(player);
        assertTrue(door.isUnlocked());
        assertEquals(player.getCurrentRoom(), this.rooms.get(2));
    }

    @Test
    void testCreateUnpickableWithEnigma() {
        final Enigma enigma = new EnigmaPasswordModelImpl("password", PASSWORD);
        final Pickable reward = factory.createPickable("Reward", new Point2D(1, 1), new Dimensions(1, 1), "A reward", 2);
        final Unpickable unpickable = factory.createUnpickableWithEnigma("Locked Chest", new Point2D(2, 2), new Dimensions(2, 2), enigma, reward);
        assertNotNull(unpickable);
        assertFalse(unpickable.isUnlocked());
        enigma.hit("123");
        assertTrue(unpickable.isUnlocked());
        player.getInventory().addItems(reward);
        assertTrue(player.getInventory().getItems().contains(reward));
    }

    @Test
    void testCreateLockedUnpickable() {
        final Pickable key = factory.createPickable(KEY, new Point2D(1, 1), new Dimensions(1, 1), "A small key", 1);
        final Pickable reward = factory.createPickable("Reward", new Point2D(1, 1), new Dimensions(1, 1), "A reward", 2);
        final Unpickable unpickable = factory.createLockedUnpickable("Locked Chest", new Point2D(2, 2), new Dimensions(2, 2), 1, reward);
        assertNotNull(unpickable);
        assertFalse(unpickable.isUnlocked());
        player.getInventory().addItems(key);
        unpickable.onAction(player);
        assertTrue(unpickable.isUnlocked());
        assertTrue(player.getInventory().getItems().contains(reward));
    }

    @Test
    void testCreateUnpickable() {
        final Pickable reward = factory.createPickable("Reward", new Point2D(1, 1), new Dimensions(1, 1), "A reward", 2);
        final Unpickable unpickable = factory.createUnpickable("Chest", new Point2D(2, 2), new Dimensions(2, 2), reward);
        assertNotNull(unpickable);
        unpickable.onAction(player);
        assertTrue(player.getInventory().getItems().contains(reward));
    }

    @Test
    void testCreateSimpleDoor() {
        final Door door = factory.createSimpleDoor("Simple Door", new Point2D(2, 2), new Dimensions(2, 3), this.rooms.get(3), new Point2D(3, 3));
        assertNotNull(door);
        assertTrue(door.isUnlocked());
        door.onAction(player);
        assertEquals(player.getCurrentRoom(), this.rooms.get(3));
    }
    // CHECKSTYLE: MagicNumber OFF
}
