package mindescape.model.inventory.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import mindescape.model.inventory.api.Inventory;
import mindescape.model.world.items.interactable.api.Pickable;
/**
 * Implementation of the {@link Inventory} interface.
 * <p>
 * This class represents an inventory that stores {@link Pickable} items in a set.
 * It allows adding, removing, and retrieving items from the inventory.
 * </p>
 * 
 * @see Inventory
 */
public class InventoryImpl implements Inventory {

    private final Set<Pickable> set = new HashSet<>();

    /**
     * Returns the set of {@link Pickable} items in the inventory.
     * 
     * @return A set of {@link Pickable} items currently in the inventory.
     */
    @Override
    public Set<Pickable> getItems() {
        return this.set;
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
        set.add(pickable);
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
        if (set.contains(pickable)) {
            set.remove(pickable);
            return true;
        }
        return false;
    }
}