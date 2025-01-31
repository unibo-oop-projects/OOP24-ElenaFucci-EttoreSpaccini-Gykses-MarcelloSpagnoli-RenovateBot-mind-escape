package mindescape.model.inventory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mindescape.model.inventory.api.Inventory;
import mindescape.model.world.items.api.Pickable;

/**
 * Implementation of the {@link Inventory} interface.
 * <p>
 * This class represents an inventory that stores {@link Pickable} items in a list.
 * It allows adding, removing, and retrieving items from the inventory.
 * </p>
 * 
 * @see Inventory
 */
public class InventoryImpl implements Inventory {

    private final List<Pickable> list = new ArrayList<>();

    /**
     * Returns the list of {@link Pickable} items in the inventory.
     * 
     * @return A list of {@link Pickable} items currently in the inventory.
     */
    @Override
    public List<Pickable> getItems() {
        return this.list;
    }

    /**
     * Adds a {@link Pickable} item to the inventory.
     * 
     * @param pickable The item to add to the inventory. Cannot be null.
     * @throws NullPointerException If the provided {@link Pickable} is null.
     */
    @Override
    public void addItems(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        list.add(pickable);
    }

    /**
     * Removes a {@link Pickable} item from the inventory.
     * 
     * @param pickable The item to remove from the inventory. Cannot be null.
     * @return {@code true} if the item was successfully removed, {@code false} otherwise.
     * @throws NullPointerException If the provided {@link Pickable} is null.
     */
    @Override
    public boolean removeItem(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        if (list.contains(pickable)) {
            list.remove(pickable);
            return true;
        }
        return false;
    }
}
