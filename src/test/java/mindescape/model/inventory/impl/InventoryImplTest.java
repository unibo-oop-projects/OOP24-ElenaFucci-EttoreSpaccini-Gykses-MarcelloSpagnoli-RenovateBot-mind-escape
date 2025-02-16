package mindescape.model.inventory.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.impl.PickableImpl;

/**
 * Unit tests for the InventoryImpl class.
 */
final class InventoryImplTest {

    private InventoryImpl inventory;
    private Pickable item1;
    private Pickable item2;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes an instance of InventoryImpl and two Pickable items.
     */
    @BeforeEach
    void setUp() {
        inventory = new InventoryImpl();
        item1 = new PickableImpl(new Point2D(0, 0), "hammer", null, null, 0);
        item2 = new PickableImpl(new Point2D(1, 2), "ticket", null, null, 0);
    }

    /**
     * Tests the addItems method of the InventoryImpl class.
     * Verifies that the item1 is added to the inventory.
     */
    @Test
    void testAddItems() {
        inventory.addItems(item1);
        final Set<Pickable> items = inventory.getItems();  // Dichiarato final
        assertTrue(items.contains(item1));
    }

    /**
     * Tests the addItems method of the InventoryImpl class with a null argument.
     * Verifies that a NullPointerException is thrown.
     */
    @Test
    void testAddItemsNull() {
        assertThrows(NullPointerException.class, () -> inventory.addItems(null));
    }

    /**
     * Tests the removeItem method of the InventoryImpl class.
     * Verifies that the item1 is removed from the inventory.
     */
    @Test
    void testRemoveItem() {
        inventory.addItems(item1);
        final boolean removed = inventory.removeItem(item1);  // Dichiarato final
        assertTrue(removed);
        final Set<Pickable> items = inventory.getItems();  // Dichiarato final
        assertFalse(items.contains(item1));
    }

    /**
     * Tests the removeItem method of the InventoryImpl class with an item that is not present in the inventory.
     * Verifies that the method returns false.
     */
    @Test
    void testRemoveItemNotPresent() {
        final boolean removed = inventory.removeItem(item1);  // Dichiarato final
        assertFalse(removed);
    }

    /**
     * Tests the removeItem method of the InventoryImpl class with a null argument.
     */
    @Test
    void testRemoveItemNull() {
        assertThrows(NullPointerException.class, () -> inventory.removeItem(null));
    }

    /**
     * Tests the getItems method of the InventoryImpl class.
     * 
     * This test adds two items to the inventory and verifies that:
     * 1. The size of the items set is 2.
     * 2. The set contains both added items.
     */
    @Test
    void testGetItems() {
        inventory.addItems(item1);
        inventory.addItems(item2);
        final Set<Pickable> items = inventory.getItems();  // Dichiarato final
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    void testGetItemsUnmodifiable() {
        inventory.addItems(item1);
        final Set<Pickable> items = inventory.getItems();  // Dichiarato final
        assertThrows(UnsupportedOperationException.class, () -> items.add(item2));
    }
}
