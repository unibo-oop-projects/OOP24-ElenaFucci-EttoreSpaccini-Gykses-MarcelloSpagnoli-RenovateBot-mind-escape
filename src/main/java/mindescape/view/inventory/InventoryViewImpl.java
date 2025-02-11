package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)); // Layout verticale per i bottoni
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);

        // Impostiamo un testo di default per la descrizione
        this.descriptionArea.setText("");

        // Creiamo un pannello che include i bottoni e la JTextArea
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Aggiungiamo il pannello dei bottoni dell'inventario
        contentPanel.add(inventoryPanel, BorderLayout.CENTER);

        // Aggiungiamo l'area di descrizione
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        contentPanel.add(scrollPane, BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.CENTER);

        // Aggiungiamo un listener per ridimensionare il contenuto
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                // Impostiamo una dimensione del font dinamica in base alla larghezza del pannello
                int fontSize = Math.max(10, width / 30);  // La dimensione minima del font è 14
                updateFontSizes(fontSize); // Aggiorna la dimensione del font
            }
        });

        // Imposta una dimensione iniziale per il pannello
        panel.setPreferredSize(new Dimension(600, 400)); 
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

            inventoryPanel.add(itemButton);  // Aggiungi il bottone al pannello
        }

        // Applichiamo subito il ridimensionamento del font in base alla larghezza iniziale
        int width = panel.getWidth();
        int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize); // Applica la dimensione del font appena aggiunti i bottoni

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    public void updateDescription(String description) {
        descriptionArea.setText(description);  // Mostra la descrizione nell'area di testo
    }

    // Metodo per aggiornare la dimensione del font per tutti i bottoni e la JTextArea
    private void updateFontSizes(int fontSize) {
        for (Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Imposta il font per il bottone
            }
        }
        // Impostiamo il font anche per la JTextArea
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, fontSize)); 
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}




