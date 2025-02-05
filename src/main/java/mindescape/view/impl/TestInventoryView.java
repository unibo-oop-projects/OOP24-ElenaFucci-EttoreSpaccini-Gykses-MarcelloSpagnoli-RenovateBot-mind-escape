package mindescape.view.impl;

import mindescape.model.inventory.impl.InventoryImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.impl.PickableImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;

import javax.swing.*;
import java.util.Optional;

public class TestInventoryView {
    public static void main(String[] args) {
        // Creazione della finestra (JFrame)
        JFrame jframe = new JFrame("Test Inventory View");

        // Creazione di un inventario vuoto per il test
        InventoryImpl inventory = new InventoryImpl();

        // Creazione della view per l'inventario passando l'inventario
        InventoryViewImpl inventoryView = new InventoryViewImpl(inventory);

        // Aggiungere il pannello dell'inventario alla finestra
        jframe.add(inventoryView.getInventoryPanel());

        // Impostare alcune proprietà per il JFrame
        jframe.setSize(400, 400);  // Imposta le dimensioni della finestra
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Chiudi la finestra quando si clicca "X"
        jframe.setVisible(true);  // Rende visibile la finestra

        // Avviare la view
        inventoryView.start();

        // Simuliamo l'aggiunta di oggetti all'inventario
        // Creiamo delle dimensioni per gli oggetti
        Dimensions dimensions1 = new Dimensions(2.0, 3.0);
        Dimensions dimensions2 = new Dimensions(1.0, 1.5);

        // Creazione della posizione degli oggetti (Optional)
        Optional<Point2D> position1 = Optional.of(new Point2D(5.0, 10.0));
        Optional<Point2D> position2 = Optional.of(new Point2D(15.0, 20.0));

        // Creiamo i due oggetti Pickable
        Pickable item1 = new PickableImpl(position1, "Chiave", dimensions1, "Una piccola chiave di ferro.", 1);
        Pickable item2 = new PickableImpl(position2, "Mappa", dimensions2, "Una mappa che mostra la posizione delle stanze.", 2);

        // Aggiungere gli oggetti all'inventario
        inventory.addItems(item1);
        inventory.addItems(item2);

        // Aggiorna la view per riflettere i nuovi oggetti
        inventoryView.update();
    }
}

