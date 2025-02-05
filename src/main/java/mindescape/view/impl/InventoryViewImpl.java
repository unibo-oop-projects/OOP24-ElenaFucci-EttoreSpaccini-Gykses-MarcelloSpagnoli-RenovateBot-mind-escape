package mindescape.view.impl;

import mindescape.view.api.View;

import mindescape.model.inventory.api.Inventory;
import mindescape.model.world.items.interactable.api.Pickable;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class InventoryViewImpl implements View {

    private JPanel inventoryPanel;
    private JTextArea descriptionArea;
    private JPanel buttonPanel;
    private Inventory inventory;

    public InventoryViewImpl(Inventory inventory) {
        this.inventory = inventory;

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        inventoryPanel.add(buttonPanel, BorderLayout.NORTH);

        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        inventoryPanel.add(scrollPane, BorderLayout.SOUTH);
    }

    @Override
    public void start() {
        buttonPanel.removeAll();
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void update() {
        // Ogni volta che l'inventario cambia, aggiorniamo la view

        Set<Pickable> items = inventory.getItems();
        buttonPanel.removeAll();  // Pulisce i pulsanti esistenti (così non duplicano)

        // Aggiungiamo un pulsante per ogni oggetto nell'inventario
        for (Pickable item : items) {
            JButton button = new JButton(item.getName());
            button.addActionListener(e -> showItemDescription(item));  
            buttonPanel.add(button);
        }

        // Rende visibili i nuovi pulsanti e la disposizione aggiornata
        buttonPanel.revalidate();  // Rende visibile la nuova disposizione dei pulsanti
        buttonPanel.repaint();     // Rende visibile il cambiamento
    }

    @Override
    public boolean toQuit() {
        return JOptionPane.showConfirmDialog(inventoryPanel, "Vuoi uscire?", "Conferma uscita", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void showItemDescription(Pickable item) {
        descriptionArea.setText(item.getDescription());
    }

    public JPanel getInventoryPanel() {
        return inventoryPanel;
    }
}
