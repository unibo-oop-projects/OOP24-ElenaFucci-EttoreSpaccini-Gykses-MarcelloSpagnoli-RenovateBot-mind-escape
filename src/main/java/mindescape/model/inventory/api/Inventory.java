package mindescape.model.inventory.api;

import java.util.List;

import mindescape.model.world.items.api.Pickable;

/**
 * Interface representing an inventory that can hold pickable items.
 */
public interface Inventory {

    /**
     * Retrieves a list of items that can be picked up.
     *
     * @return a list of Pickable items currently in the inventory.
     */
    List<Pickable> getItems();

    /**
     * Add an item to the inventory.
     * @param pickable item to add to the inventory.
     */
    void addItems(Pickable pickable);

    /**
     * Remove an item from the inventory.
     * @param pickable item to remove from the inventory.
     * @return true if the item was removed, false otherwise.
     */
    boolean removeItem(Pickable pickable);
}
