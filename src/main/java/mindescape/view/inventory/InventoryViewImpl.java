package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
import mindescape.view.utils.ImageButton;
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
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.swing.JSplitPane;
import java.awt.Image;

/**
 * Implementation of the inventory view in the game.
 */
public final class InventoryViewImpl implements View {

    private static final int GRID_COLUMNS = 4; 
    private static final int MARGIN = 10;
    private static final int MIN_FONT_SIZE = 10;
    private static final int FONT_SIZE_DIVISOR = 30;
    private static final int PANEL_SIZE = 400;
    private static final int DESCRIPTION_ROWS = 5;
    private static final int DESCRIPTION_COLUMNS = 20;
    private static final int MIN_BUTTON_SIZE = 10;
    private static final int DESCRIPTION_FONT_SIZE = 20;
    private static final double SPLIT_PANE_RESIZE_WEIGHT = 0.8;

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;
    private final JSplitPane splitPane;

    /**
     * Constructs an InventoryViewImpl with the specified controller.
     * Initializes the main panel with a BorderLayout and adds the inventory panel and description area.
     * Sets up component and key listeners to handle resizing and key events.
     *
     * @param controller the InventoryControllerImpl instance to handle input and manage inventory actions.
     */
    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());

        this.inventoryPanel = new JPanel(null); 
        this.descriptionArea = new JTextArea(DESCRIPTION_ROWS, DESCRIPTION_COLUMNS);
        this.descriptionArea.setEditable(false);
        this.descriptionArea.setText("");

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inventoryPanel, new JScrollPane(descriptionArea));
        splitPane.setDividerLocation(PANEL_SIZE - DESCRIPTION_ROWS * DESCRIPTION_FONT_SIZE);
        splitPane.setDividerSize(10);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(SPLIT_PANE_RESIZE_WEIGHT);
        panel.add(splitPane, BorderLayout.CENTER);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int panelWidth = panel.getWidth();
                final int panelHeight = panel.getHeight();
                final int buttonSizeWidth = Math.max(
                        MIN_BUTTON_SIZE, 
                        (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS
                    );
                final int buttonSizeHeight = Math.max(
                        MIN_BUTTON_SIZE, 
                        (panelHeight - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS
                    );
                updateButtonSizes(buttonSizeWidth, buttonSizeHeight);
                positionButtons(buttonSizeWidth, buttonSizeHeight);

                final int fontSize = Math.max(MIN_FONT_SIZE, panelWidth / FONT_SIZE_DIVISOR);
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
     * Returns the JPanel associated with this InventoryViewImpl.
     * 
     * @return the JPanel instance used in this view.
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The panel is returned to the caller")
    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * Updates the inventory buttons displayed on the inventory panel.
     * 
     * This method removes all existing buttons from the inventory panel and 
     * creates new buttons for each item in the provided set. Each button is 
     * configured with an image representing the item and an action listener 
     * that handles item click events. The buttons are then added to the 
     * inventory panel.
     * 
     * The method also calculates the appropriate size for the buttons based 
     * on the dimensions of the panel and updates the button sizes and positions 
     * accordingly. Finally, it adjusts the font size of the buttons' text based 
     * on the width of the panel.
     * 
     * @param items the set of items to be displayed as buttons in the inventory panel
     */
    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();

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

            inventoryPanel.add(itemButton);
        }

        final int panelWidth = panel.getWidth();
        final int buttonSizeWidth = Math.max(MIN_BUTTON_SIZE, (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);
        final int panelHeight = panel.getHeight();
        final int buttonSizeHeight = Math.max(MIN_BUTTON_SIZE, (panelHeight - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);

        updateButtonSizes(buttonSizeWidth, buttonSizeHeight);
        positionButtons(buttonSizeWidth, buttonSizeHeight);

        inventoryPanel.revalidate();
        inventoryPanel.repaint();

        final int fontSize = Math.max(MIN_FONT_SIZE, panelWidth / FONT_SIZE_DIVISOR);
        updateFontSizes(fontSize);
    }

    private void updateButtonSizes(final int buttonWidth, final int buttonHeight) {
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                final JButton button = (JButton) comp;
                button.setSize(buttonWidth, buttonHeight);
            }
        }
    }

    private void positionButtons(final int buttonWidth, final int buttonHeight) {
        int x = MARGIN;
        int y = MARGIN;

        int columnCount = 0;

        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                final JButton button = (JButton) comp;

                button.setLocation(x, y);

                columnCount++;
                if (columnCount >= GRID_COLUMNS) {
                    columnCount = 0;
                    x = MARGIN;
                    y += buttonHeight + MARGIN;
                } else {
                    x += buttonWidth + MARGIN;
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

    /**
     * Updates the description area with the provided text.
     *
     * @param description the new description to be set in the description area
     */
    public void updateDescription(final String description) {
        descriptionArea.setText(description);
    }
}
