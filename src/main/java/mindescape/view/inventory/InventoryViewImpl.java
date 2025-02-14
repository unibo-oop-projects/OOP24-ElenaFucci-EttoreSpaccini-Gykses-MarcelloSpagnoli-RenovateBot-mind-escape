package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
import mindescape.view.enigmapuzzle.impl.ImageButton;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Image;

public final class InventoryViewImpl implements View {

    private static final int GRID_COLUMNS = 4; // Numero fisso di colonne
    private static final int MARGIN = 10;
    private static final int MIN_FONT_SIZE = 10;
    private static final int FONT_SIZE_DIVISOR = 30;
    private static final int PANEL_SIZE = 400;
    private static final int DESCRIPTION_ROWS = 5;
    private static final int DESCRIPTION_COLUMNS = 20;
    
    private static final int MIN_BUTTON_SIZE = 50; // La dimensione minima del bottone

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;

    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());  // Usare BorderLayout

        // Pannello che contiene i bottoni con layout null
        this.inventoryPanel = new JPanel(null); 
        this.descriptionArea = new JTextArea(DESCRIPTION_ROWS, DESCRIPTION_COLUMNS);
        this.descriptionArea.setEditable(false);
        this.descriptionArea.setText("");
        
        panel.add(inventoryPanel, BorderLayout.CENTER);
        final JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Aggiungiamo un listener per il ridimensionamento
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                // Ottieni la larghezza del pannello
                final int panelWidth = panel.getWidth();
                
                // Calcola la nuova dimensione dei bottoni in modo che si ingrandiscano proporzionalmente
                int buttonSize = Math.max(MIN_BUTTON_SIZE, (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);
                updateButtonSizes(buttonSize);  // Aggiorna le dimensioni dei bottoni
                positionButtons(buttonSize);  // Ricalcola la posizione dei bottoni in base al nuovo layout
                final int fontSize = Math.max(MIN_FONT_SIZE, panelWidth / FONT_SIZE_DIVISOR);
                updateFontSizes(fontSize);  // Aggiorna la dimensione dei font
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                final int pressed = e.getKeyCode();
                controller.handleInput(pressed);
            }
        });

        panel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();

        // Aggiungi i bottoni dinamicamente all'inventario
        for (final Pickable item : items) {
            final ImageButton itemButton = new ImageButton();
            final Image image = createImage(item);
            itemButton.setImage(image);
            itemButton.setFocusable(false);
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.handleItemClick(item);
                }
            });

            // Impostiamo la dimensione iniziale del bottone
            inventoryPanel.add(itemButton);
        }

        // Aggiorna i bottoni dopo l'inserimento
        final int panelWidth = panel.getWidth();
        final int buttonSize = Math.max(MIN_BUTTON_SIZE, (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);
        updateButtonSizes(buttonSize);  // Aggiorna le dimensioni dei bottoni
        positionButtons(buttonSize);  // Ricalcola la posizione dei bottoni

        // Ridisegna la vista
        inventoryPanel.revalidate();
        inventoryPanel.repaint();

        // Applica la dimensione del font in base alla larghezza del pannello
        final int fontSize = Math.max(MIN_FONT_SIZE, panelWidth / FONT_SIZE_DIVISOR);
        updateFontSizes(fontSize);
    }

    private Image createImage(final Pickable item) {
        final String imagePath = switch (item.getName()) {
            case "key", "Office key" -> "key.png";
            case "Bed note", "Message", "Canteen note" -> "ticket.png";
            case "Hammer" -> "hammer.png";
            case "Torch" -> "torch.png";
            case "Wrench" -> "wrench.png";
            default -> throw new IllegalArgumentException("Unexpected item: " + item.getName());
        };
        return new ImageIcon(getClass().getClassLoader().getResource("pickable/" + imagePath)).getImage();
    }

    public void updateDescription(final String description) {
        descriptionArea.setText(description);
    }

    // Metodo per aggiornare la dimensione dei bottoni
    private void updateButtonSizes(final int buttonSize) {
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                final JButton button = (JButton) comp;
                // Ridimensiona i bottoni
                button.setSize(buttonSize, buttonSize);
            }
        }
    }

    // Metodo per aggiornare la posizione dei bottoni
    private void positionButtons(final int buttonSize) {
        int x = MARGIN;  // Posizione X iniziale dei bottoni
        int y = MARGIN;  // Posizione Y iniziale dei bottoni

        int columnCount = 0;

        // Distribuiamo i bottoni in base al numero di colonne
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;

                // Imposta la posizione assoluta del bottone
                button.setLocation(x, y);

                columnCount++;
                if (columnCount >= GRID_COLUMNS) {
                    // Se abbiamo raggiunto il numero di colonne, inizia una nuova riga
                    columnCount = 0;
                    x = MARGIN;
                    y += buttonSize + MARGIN; // Vai alla riga successiva
                } else {
                    // Spostati alla colonna successiva
                    x += buttonSize + MARGIN;
                }
            }
        }
    }

    private void updateFontSizes(final int fontSize) {
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                final JButton button = (JButton) comp;
                button.setFont(new Font("Arial", Font.PLAIN, fontSize));
            }
        }
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, fontSize)); 
    }
}
