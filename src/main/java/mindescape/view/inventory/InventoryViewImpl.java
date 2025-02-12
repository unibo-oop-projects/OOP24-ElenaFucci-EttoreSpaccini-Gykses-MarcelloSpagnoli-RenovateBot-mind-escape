package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class InventoryViewImpl implements View {

    private final InventoryControllerImpl controller;
    private JPanel panel;
    private JPanel inventoryPanel;
    private JTextArea descriptionArea;

    /**
     * Costruttore della vista dell'inventario.
     * @param controller il controller dell'inventario che gestisce la logica
     */
    public InventoryViewImpl(InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)); // Layout verticale per i bottoni
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);

        // Aggiungi i pannelli alla vista
        panel.add(inventoryPanel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Aggiungi il listener per la modifica dinamica delle dimensioni del font
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(10, width / 30);
                updateFontSizes(fontSize);
            }
        });

        // Aggiungi il controller come osservatore all'inventario
        controller.getInventory().addObserver(this);

        // Gestione degli eventi da tastiera
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int pressed = e.getKeyCode();
                controller.handleInput(pressed); // Gestisci gli input (come la pressione di un tasto)
            }
        });
        panel.setFocusable(true); // Per abilitare l'ascolto degli eventi da tastiera
    }

    /**
     * Metodo chiamato per aggiornare la vista ogni volta che l'inventario cambia.
     * @param items l'insieme degli oggetti nell'inventario
     */
    public void updateItems(Set<Pickable> items) {
        updateInventoryButtons(items);
    }

    /**
     * Aggiorna i bottoni dell'inventario, aggiungendo un bottone per ogni oggetto.
     * @param items l'insieme degli oggetti
     */
    public void updateInventoryButtons(Set<Pickable> items) {
        inventoryPanel.removeAll(); // Rimuovi i bottoni esistenti
        for (Pickable item : items) {
            JButton itemButton = new JButton(item.getName());
            itemButton.setFocusable(false); // Disabilita la messa a fuoco per evitare il cambio di stile
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleItemClick(item); // Gestisce il click sull'oggetto
                }
            });

            inventoryPanel.add(itemButton);
        }

        // Aggiorna le dimensioni del font in base alla larghezza del pannello
        int width = panel.getWidth();
        int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize);

        // Rendi visibile la nuova disposizione
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Metodo privato per aggiornare la dimensione dei font per tutti i componenti
     * all'interno dell'inventario, come i bottoni e l'area di descrizione.
     * @param fontSize la nuova dimensione del font
     */
    private void updateFontSizes(int fontSize) {
        for (Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Imposta il font per i bottoni
            }
        }
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Imposta il font per la descrizione
    }

    /**
     * Metodo che restituisce il pannello principale di questa vista.
     * @return il pannello che rappresenta la vista
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Aggiorna l'area di descrizione dell'oggetto selezionato.
     * @param description la descrizione dell'oggetto
     */
    public void updateDescription(String description) {
        descriptionArea.setText(description);
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}




