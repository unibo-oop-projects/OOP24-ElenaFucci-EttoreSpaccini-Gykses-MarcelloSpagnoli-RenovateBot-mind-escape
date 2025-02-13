package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
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
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Implementation of the InventoryView.
 */
public class InventoryViewImpl implements View {

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;

    /**
     * Constructs an InventoryViewImpl with the specified InventoryControllerImpl.
     * Initializes the main panel with a BorderLayout and sets up the inventory panel
     * with a vertical BoxLayout for buttons. A non-editable JTextArea is created for
     * displaying descriptions, and a JScrollPane is added to allow scrolling.
     * The content panel includes the inventory panel and the description area.
     * A component listener is added to dynamically adjust the font size based on the
     * panel's width. The initial preferred size of the panel is set to 600x400.
     *
     * @param controller the InventoryControllerImpl to be associated with this view
     */
    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)); // Layout verticale per i bottoni
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);
        this.descriptionArea.setText("");
        panel.add(inventoryPanel, BorderLayout.CENTER);
        final JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(10, width / 30);
                updateFontSizes(fontSize);
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                final int pressed = e.getKeyCode();
                controller.handleInput(pressed);
            }
        });

        panel.setPreferredSize(new Dimension(600, 400)); 
    }

    /**
     * Returns the JPanel associated with this view.
     *
     * @return the JPanel instance
     */
    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * Updates the inventory buttons displayed in the inventory panel.
     * This method removes all existing buttons and adds new buttons for each item in the provided set.
     * Each button is labeled with the item's name and has an action listener that handles item clicks.
     * The font size of the buttons is adjusted based on the width of the panel.
     *
     * @param items the set of items to be displayed as buttons in the inventory panel
     */
    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();
        for (final Pickable item : items) {
            final JButton itemButton = new JButton(getIcon(item));
            itemButton.setFocusable(false);
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.handleItemClick(item);
                }

            });

            inventoryPanel.add(itemButton);
        }

        final int width = panel.getWidth();
        final int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize);
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    private Icon getIcon(final Pickable item) {
        final String imagePath = switch (item.getName()) {
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
        
        // Carica l'immagine dalla risorsa
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("pickable/" + imagePath));
    
        // Definisci una dimensione fissa per le icone (ad esempio 50x50)
        int iconSize = 70;
    
        // Ridimensiona l'immagine per adattarla alla dimensione del bottone
        ImageIcon resizedIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(iconSize, iconSize, java.awt.Image.SCALE_SMOOTH));
        
        return resizedIcon;
    }
    
    /**
     * Updates the description displayed in the text area.
     *
     * @param description the new description to be displayed
     */
    public void updateDescription(final String description) {
        descriptionArea.setText(description);
    }

    /**
     * Updates the font sizes of all components within the inventory panel.
     * Specifically, it sets the font size of all JButton components and the description area.
     *
     * @param fontSize the new font size to be applied to the components
     */
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
