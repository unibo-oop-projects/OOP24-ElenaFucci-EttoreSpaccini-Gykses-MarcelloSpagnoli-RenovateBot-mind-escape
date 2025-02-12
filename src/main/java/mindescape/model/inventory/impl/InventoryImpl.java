package mindescape.model.inventory.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.api.InventoryObserver;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.inventory.InventoryViewImpl;

/**
 * Implementation of the Inventory and InventoryObserver interfaces.
 * This class manages a collection of Pickable items and notifies observers
 * when the inventory changes.
 */
public class InventoryImpl implements Inventory, InventoryObserver, Serializable {

    private final Set<Pickable> set = new HashSet<>();
    private final transient Set<InventoryViewImpl> observers = new HashSet<>();

    /**
     * Retrieves the set of items in the inventory.
     *
     * @return a set of items that are pickable.
     */
    @Override
    public Set<Pickable> getItems() {
        return this.set;
    }

    /**
     * Adds a pickable item to the inventory.
     *
     * @param pickable the item to be added to the inventory; must not be null
     * @throws NullPointerException if the pickable item is null
     */
    @Override
    public void addItems(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        set.add(pickable);
        notifyObservers();
    }

    /**
     * Removes the specified pickable item from the inventory.
     * 
     * @param pickable the item to be removed from the inventory; must not be null
     * @return {@code true} if the item was successfully removed, {@code false} otherwise
     * @throws NullPointerException if the specified item is null
     */
    @Override
    public boolean removeItem(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        if (set.contains(pickable)) {
            set.remove(pickable);
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(InventoryViewImpl observer) {
        observers.add(observer);
    }

    /**
     * Removes the specified observer from the list of observers.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(InventoryViewImpl observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers about the changes in the inventory.
     * This method iterates through the list of observers and calls their
     * updateItems method, passing the current set of items.
     */
    @Override
    public void notifyObservers() {
        for (InventoryViewImpl observer : observers) {
            observer.updateItems(set);
        }
    }
}
