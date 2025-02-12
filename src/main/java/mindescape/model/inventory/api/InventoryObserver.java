package mindescape.model.inventory.api;

import mindescape.view.inventory.InventoryViewImpl;

public interface InventoryObserver {

    void addObserver(InventoryViewImpl observer);

    void removeObserver(InventoryViewImpl observer);
    
    void notifyObservers();
}
