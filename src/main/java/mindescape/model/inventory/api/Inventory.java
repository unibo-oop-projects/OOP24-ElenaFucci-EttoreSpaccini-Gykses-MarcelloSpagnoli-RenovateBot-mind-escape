package mindescape.model.inventory.api;

import java.util.Set;

import mindescape.model.world.items.interactable.api.Pickable;

/**
 * Interface representing an inventory that can hold pickable items.
 */
public interface Inventory {

    /**
     * Retrieves a set of items that can be picked up.
     *
     * @return a set of Pickable items currently in the inventory.
     */
    Set<Pickable> getItems();

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
