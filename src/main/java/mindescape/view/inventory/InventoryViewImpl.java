package mindescape.view.inventory;

import mindescape.view.api.View;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.world.items.interactable.api.Pickable;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class InventoryViewImpl implements View {

    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;
    private final JPanel buttonPanel;
    private final Inventory inventory;

    public InventoryViewImpl(final Inventory inventory) {
        this.inventory = inventory;

        this.inventoryPanel = new JPanel();
        this.inventoryPanel.setLayout(new BorderLayout());

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout());
        this.inventoryPanel.add(buttonPanel, BorderLayout.NORTH);

        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        this.inventoryPanel.add(scrollPane, BorderLayout.SOUTH);
    }

    
    private void start() {
        this.buttonPanel.removeAll();
        this.buttonPanel.revalidate();
        this.buttonPanel.repaint();
    }

    @Override
    public void draw() {
        // Ogni volta che l'inventario cambia, aggiorniamo la view

        final Set<Pickable> items = inventory.getItems();
        this.buttonPanel.removeAll();  // Pulisce i pulsanti esistenti (così non duplicano)

        // Aggiungiamo un pulsante per ogni oggetto nell'inventario
        for (final Pickable item : items) {
            final JButton button = new JButton(item.getName());
            button.addActionListener(e -> showItemDescription(item));  
            buttonPanel.add(button);
        }

        // Rende visibili i nuovi pulsanti e la disposizione aggiornata
        buttonPanel.revalidate();  // Rende visibile la nuova disposizione dei pulsanti
        buttonPanel.repaint();     // Rende visibile il cambiamento
    }

    private void showItemDescription(final Pickable item) {
        descriptionArea.setText(item.getDescription());
    }
    @Override
    public JPanel getPanel() {
        return this.inventoryPanel;
    }
}
