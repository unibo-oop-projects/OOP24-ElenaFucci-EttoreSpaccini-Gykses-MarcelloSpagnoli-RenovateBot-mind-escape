package mindescape.model.inventory.api;

import mindescape.view.inventory.InventoryViewImpl;

/**
 * Observer which used to notify the InventoryView about changes in the inventory.
 */
public interface InventoryObserver {

    void addObserver(InventoryViewImpl observer);

    void removeObserver(InventoryViewImpl observer);
    
    void notifyObservers();
}
