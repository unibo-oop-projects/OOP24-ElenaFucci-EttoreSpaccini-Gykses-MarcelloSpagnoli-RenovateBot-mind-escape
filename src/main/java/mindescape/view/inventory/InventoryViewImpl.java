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
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Image;

/**
 * Implementation of the InventoryView interface.
 */
public final class InventoryViewImpl implements View {

    private static final int GRID_ROWS = 0;
    private static final int GRID_COLUMNS = 4;
    private static final int MARGIN = 10;
    private static final int MIN_FONT_SIZE = 10;
    private static final int FONT_SIZE_DIVISOR = 30;
    private static final int PANEL_SIZE = 400;
    private static final int DESCRIPTION_ROWS = 5;
    private static final int DESCRIPTION_COLUMNS = 20;

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;

    /**
     * Constructs an InventoryViewImpl with the specified controller.
     * Initializes the main panel with a BorderLayout and an inventory panel with a GridLayout.
     * Sets up a non-editable description area within a scroll pane.
     * Adds a component listener to adjust font sizes based on panel width.
     * Adds a key listener to handle input events.
     * Sets the preferred size of the panel to 400x400.
     *
     * @param controller the InventoryControllerImpl instance to be used by this view
     */
    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS, MARGIN, MARGIN)); 
        this.descriptionArea = new JTextArea(DESCRIPTION_ROWS, DESCRIPTION_COLUMNS);
        this.descriptionArea.setEditable(false);
        this.descriptionArea.setText("");
        panel.add(inventoryPanel, BorderLayout.CENTER);
        final JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(MIN_FONT_SIZE, width / FONT_SIZE_DIVISOR);
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

        panel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    }

    /**
     * Returns the JPanel associated with this view.
     *
     * @return the JPanel instance
     */
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * Updates the inventory buttons based on the given set of items.
     * This method removes all existing buttons from the inventory panel and creates new buttons
     * for each item in the provided set. Each button displays an icon representing the item and
     * is configured with an action listener to handle item clicks.
     *
     * @param items the set of items to be displayed as buttons in the inventory panel
     */
    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();

        for (final Pickable item : items) {
            final JButton itemButton = new JButton() {
                @Override
                public void paintComponent(final Graphics g) {
                    super.paintComponent(g);
                    final Icon icon = createIcon(item);
                    if (icon != null) {
                        final int buttonSize = Math.min(getWidth(), getHeight());
                        final Image img = ((ImageIcon) icon).getImage();
                        final Image scaledImg = img.getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
                        setIcon(new ImageIcon(scaledImg));
                    }
                }
            };

            itemButton.setIcon(createIcon(item));

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
        final int fontSize = Math.max(MIN_FONT_SIZE, width / FONT_SIZE_DIVISOR);
        updateFontSizes(fontSize);

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Creates an Icon for the given Pickable item.
     *
     * @param item the Pickable item for which the icon is to be created
     * @return the Icon corresponding to the given item
     * @throws IllegalArgumentException if the item's name is unexpected
     */
    private Icon createIcon(final Pickable item) {
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
        return new ImageIcon(getClass().getClassLoader().getResource("pickable/" + imagePath));
    }

    /**
     * Updates the description area with the provided text.
     *
     * @param description the new description text to be set in the description area
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
