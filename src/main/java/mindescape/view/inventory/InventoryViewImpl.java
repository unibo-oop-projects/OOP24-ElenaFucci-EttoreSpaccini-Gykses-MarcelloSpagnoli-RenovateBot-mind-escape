package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

/**
 * InventoryViewImpl is a concrete implementation of the View interface that represents
 * the inventory view in the application. It is responsible for displaying the inventory
 * items and their descriptions, and handling user interactions with the inventory.
 * 
 * <p>This class uses a BorderLayout for the main panel, with the inventory items displayed
 * in the center and the description area at the bottom. The font sizes of the components
 * are dynamically adjusted based on the panel's width.</p>
 * 
 * <p>It observes changes in the inventory and updates the view accordingly. It also handles
 * user input through key events and item button clicks.</p>
 * 
 * @param controller The controller that manages the inventory logic.
 */
public class InventoryViewImpl implements View {

    private final InventoryControllerImpl controller;
    private JPanel panel;
    private JPanel inventoryPanel;
    private JTextArea descriptionArea;

    /**
     * Constructs an InventoryViewImpl with the specified controller.
     * Initializes the main panel with a BorderLayout, an inventory panel with a vertical BoxLayout,
     * and a non-editable description text area. Adds the inventory panel to the center of the main panel
     * and the description area within a scroll pane to the south of the main panel.
     * Adds a component listener to adjust font sizes based on the panel's width when resized.
     * Registers this view as an observer to the inventory model.
     * Adds a key listener to handle input events and sets the panel to be focusable.
     *
     * @param controller the InventoryControllerImpl instance to be used by this view
     */
    public InventoryViewImpl(InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);

        panel.add(inventoryPanel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(10, width / 30);
                updateFontSizes(fontSize);
            }
        });

        controller.getInventory().addObserver(this);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int pressed = e.getKeyCode();
                controller.handleInput(pressed);
            }
        });
        panel.setFocusable(true);
    }

    /**
     * Updates the inventory view with the given set of items.
     *
     * @param items the set of items to be displayed in the inventory
     */
    public void updateItems(Set<Pickable> items) {
        updateInventoryButtons(items);
    }

    /**
     * Updates the inventory buttons displayed in the inventory panel.
     * This method removes all existing buttons from the inventory panel and 
     * creates new buttons for each item in the provided set. Each button is 
     * labeled with the item's name and is associated with an action listener 
     * that handles item click events.
     *
     * @param items a set of Pickable items to be displayed as buttons in the inventory panel
     */
    public void updateInventoryButtons(Set<Pickable> items) {
        inventoryPanel.removeAll();
        for (Pickable item : items) {
            JButton itemButton = new JButton(item.getName());
            itemButton.setFocusable(false);
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleItemClick(item);
                }
            });

            inventoryPanel.add(itemButton);
        }

        int width = panel.getWidth();
        int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize);

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Updates the font sizes of the components within the inventory panel.
     * Specifically, it sets the font size for all JButton components and the description area.
     *
     * @param fontSize the new font size to be applied to the components
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
     * Returns the JPanel associated with this InventoryViewImpl.
     *
     * @return the JPanel instance representing the inventory view.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Updates the description area with the provided text.
     *
     * @param description the new description to be displayed in the description area
     */
    public void updateDescription(String description) {
        descriptionArea.setText(description);
    }

    /**
     * This method is currently not implemented and will throw an UnsupportedOperationException.
     *
     * @throws UnsupportedOperationException if the method is called
     */
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}




