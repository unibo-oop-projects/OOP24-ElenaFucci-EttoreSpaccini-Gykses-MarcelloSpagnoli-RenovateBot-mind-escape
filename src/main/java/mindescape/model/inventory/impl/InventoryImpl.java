package mindescape.model.inventory.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.api.InventoryObserver;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.inventory.InventoryViewImpl;

public class InventoryImpl implements Inventory, InventoryObserver {

    private final Set<Pickable> set = new HashSet<>();
    private final Set<InventoryViewImpl> observers = new HashSet<>(); // Lista degli osservatori

    @Override
    public Set<Pickable> getItems() {
        return this.set;
    }

    @Override
    public void addItems(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        set.add(pickable);
        notifyObservers();  // Notifica gli osservatori dopo un cambiamento
    }

    @Override
    public boolean removeItem(Pickable pickable) {
        Objects.requireNonNull(pickable, "Pickable item cannot be null");
        if (set.contains(pickable)) {
            set.remove(pickable);
            notifyObservers();  // Notifica gli osservatori dopo un cambiamento
            return true;
        }
        return false;
    }

    @Override
    public void addObserver(InventoryViewImpl observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(InventoryViewImpl observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (InventoryViewImpl observer : observers) {
            observer.updateItems(set); // Chiama il metodo per aggiornare gli oggetti nella vista
        }
    }
}
