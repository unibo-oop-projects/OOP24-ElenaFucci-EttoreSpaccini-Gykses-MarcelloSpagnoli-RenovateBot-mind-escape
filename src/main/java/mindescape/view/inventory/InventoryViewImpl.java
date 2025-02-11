package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class InventoryViewImpl implements View {

    private final InventoryControllerImpl controller;
    private JPanel panel;
    private JPanel inventoryPanel;
    private JTextArea descriptionArea;

    public InventoryViewImpl(InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        this.inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)); // Layout verticale per i bottoni
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);

        // Impostiamo un testo di default per la descrizione
        this.descriptionArea.setText("Seleziona un oggetto per visualizzarne la descrizione.");

        // Creiamo un pannello che include i bottoni e la JTextArea
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Aggiungiamo il pannello dei bottoni dell'inventario
        contentPanel.add(inventoryPanel, BorderLayout.CENTER);

        // Aggiungiamo l'area di descrizione
        contentPanel.add(new JScrollPane(descriptionArea), BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.CENTER);

        // Rende il pannello ridimensionabile
        panel.setPreferredSize(new Dimension(600, 400)); // Imposta una dimensione iniziale
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateInventoryButtons(Set<Pickable> items) {
        inventoryPanel.removeAll();  // Pulisce i bottoni precedenti
        for (Pickable item : items) {
            JButton itemButton = new JButton(item.getName());  // Usa il nome dell'oggetto come testo del bottone
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleItemClick(item); // Quando clicchi sul bottone, richiami il controller
                }
            });
            inventoryPanel.add(itemButton);
        }
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    public void updateDescription(String description) {
        descriptionArea.setText(description);  // Mostra la descrizione nell'area di testo
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}



