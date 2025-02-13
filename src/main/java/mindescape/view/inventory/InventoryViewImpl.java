package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Image;
public class InventoryViewImpl implements View {

    private static final int DEFAULT_BUTTON_SIZE = 70; // Dimensione iniziale dei bottoni (quadrati)
    private static final int GRID_ROWS = 0; // Numero di colonne
    private static final int GRID_COLUMNS = 4; // Numero di colonne
    private static final int MARGIN = 10; // Margine tra i bottoni

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;
    private int buttonSize = 70;

    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        // Usare un GridLayout con righe dinamiche (0) e un numero fisso di colonne (4)
        inventoryPanel.setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS, MARGIN, MARGIN)); 
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);
        this.descriptionArea.setText("");
        panel.add(inventoryPanel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // ComponentListener per ridimensionare il testo
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(10, width / 30);
                updateFontSizes(fontSize);
            }
        });

        // KeyListener per gestire gli input da tastiera
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int pressed = e.getKeyCode();
                controller.handleInput(pressed);
            }
        });

        panel.setPreferredSize(new Dimension(400, 400)); // Dimensione iniziale del pannello
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Aggiorna i bottoni dell'inventario, ridimensionandoli proporzionalmente.
     */
    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();

        for (final Pickable item : items) {
            JButton itemButton = new JButton() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Ridimensiona l'icona per adattarla al bottone
                    Icon icon = createIcon(item);
                    if (icon != null) {
                        int buttonSize = Math.min(getWidth(), getHeight()); // Assicurati che sia un quadrato
                        Image img = ((ImageIcon) icon).getImage();
                        Image scaledImg = img.getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
                        setIcon(new ImageIcon(scaledImg));
                    }
                }
            };

            // Imposta inizialmente l'icona per il bottone
            itemButton.setIcon(createIcon(item));

            itemButton.setFocusable(false);
            itemButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            itemButton.setMinimumSize(new Dimension(buttonSize, buttonSize));
            itemButton.setMaximumSize(new Dimension(buttonSize, buttonSize));
            try {
                final ImageIcon icon = new ImageIcon(getIcon(item).getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
                itemButton.setIcon(icon);
            } catch (final IOException ex) {
            }
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleItemClick(item);
                }
            });

            // Imposta la dimensione iniziale dei bottoni come quadrati
            itemButton.setPreferredSize(new Dimension(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE)); // Impostiamo una dimensione iniziale
            itemButton.setMinimumSize(new Dimension(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE)); // Impostiamo una dimensione minima
            inventoryPanel.add(itemButton);
        }

        final int width = panel.getWidth();
        final int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize);

        // Rendi i bottoni ridimensionabili
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Crea l'icona per un item specifico, in base al suo nome.
     * Questo metodo sostituisce getIcon(item) e crea direttamente l'icona.
     */
    private Icon createIcon(final Pickable item) {
        String imagePath = switch(item.getName()) {
            case "key" -> "key.png";
            case "Office key" -> "key.png";
            case "Bed note" -> "ticket.png";
            case "Message" -> "ticket.png";
            case "Canteen note" -> "ticket.png";
            case "Hammer" -> "hammer.png";
            case "Torch" -> "torch.png";
            case "Wrench" -> "wrench.png";
            default -> throw new IllegalArgumentException("Unexpected item: " + item.getName());
        };
        return new ImageIcon(getClass().getClassLoader().getResource("pickable/" + imagePath));
    }

    /**
     * Aggiorna la descrizione mostrata nell'area di testo.
     */
    public void updateDescription(String description) {
        descriptionArea.setText(description);
    }

    /**
     * Aggiorna la dimensione dei caratteri per tutti i componenti.
     */
    private void updateFontSizes(int fontSize) {
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setFont(new Font("Arial", Font.PLAIN, fontSize));
            }
        }
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, fontSize)); 
    }
}
