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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Image;

/**
 * Implementation of the inventory view. 
 **/
public final class InventoryViewImpl implements View {

    private static final int GRID_COLUMNS = 4;
    private static final int MARGIN = 10;
    private static final int MIN_FONT_SIZE = 10;
    private static final int FONT_SIZE_DIVISOR = 30;
    private static final int PANEL_SIZE = 400;
    private static final int DESCRIPTION_ROWS = 5;
    private static final int DESCRIPTION_COLUMNS = 20;
    private static final int MIN_BUTTON_SIZE = 50;

    private final InventoryControllerImpl controller;
    private final JPanel panel;
    private final JPanel inventoryPanel;
    private final JTextArea descriptionArea;

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
        panel.add(inventoryPanel, BorderLayout.CENTER);
        final JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int panelWidth = panel.getWidth();
                final int buttonSize = Math.max(MIN_BUTTON_SIZE, (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);
                updateButtonSizes(buttonSize);
                positionButtons(buttonSize);
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
     * @return the JPanel instance representing the inventory view.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The panel is returned to the caller")
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * Updates the inventory buttons displayed in the inventory panel.
     * 
     * This method removes all existing buttons from the inventory panel and 
     * creates new buttons for each item in the provided set. Each button is 
     * associated with an image representing the item and an action listener 
     * that handles item click events.
     * 
     * The method also adjusts the size and position of the buttons based on 
     * the panel's width and updates the font sizes accordingly.
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
        final int buttonSize = Math.max(MIN_BUTTON_SIZE, (panelWidth - MARGIN * (GRID_COLUMNS + 1)) / GRID_COLUMNS);
        updateButtonSizes(buttonSize);
        positionButtons(buttonSize);

        inventoryPanel.revalidate();
        inventoryPanel.repaint();

        final int fontSize = Math.max(MIN_FONT_SIZE, panelWidth / FONT_SIZE_DIVISOR);
        updateFontSizes(fontSize);
    }

    /**
     * Creates an image representation of the given pickable item.
     *
     * @param item the pickable item for which the image is to be created
     * @return the image representation of the given pickable item
     * @throws IllegalArgumentException if the item's name is unexpected
     */
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

    private void updateButtonSizes(final int buttonSize) {
        for (final Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                final JButton button = (JButton) comp;
                button.setSize(buttonSize, buttonSize);
            }
        }
    }

    private void positionButtons(final int buttonSize) {
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
                    y += buttonSize + MARGIN;
                } else {
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
