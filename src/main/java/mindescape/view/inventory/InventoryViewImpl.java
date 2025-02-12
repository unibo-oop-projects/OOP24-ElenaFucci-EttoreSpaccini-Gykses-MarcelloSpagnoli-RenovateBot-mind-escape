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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

public class InventoryViewImpl implements View {

    private final InventoryControllerImpl controller;
    private JPanel panel;
    private JPanel inventoryPanel;
    private JTextArea descriptionArea;

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
    public InventoryViewImpl(InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)); // Layout verticale per i bottoni
        this.descriptionArea = new JTextArea(5, 20);
        this.descriptionArea.setEditable(false);

        this.descriptionArea.setText("");

        //JPanel contentPanel = new JPanel(new BorderLayout());

        panel.add(inventoryPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        //panel.add(contentPanel, BorderLayout.CENTER);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(10, width / 30);
                updateFontSizes(fontSize);
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int pressed = e.getKeyCode();
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
        return panel;
    }

    /**
     * Updates the inventory buttons displayed in the inventory panel.
     * This method removes all existing buttons and adds new buttons for each item in the provided set.
     * Each button is labeled with the item's name and has an action listener that handles item clicks.
     * The font size of the buttons is adjusted based on the width of the panel.
     *
     * @param items the set of items to be displayed as buttons in the inventory panel
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

        // Applichiamo subito il ridimensionamento del font in base alla larghezza iniziale
        int width = panel.getWidth();
        int fontSize = Math.max(10, width / 30);
        updateFontSizes(fontSize); // Applica la dimensione del font appena aggiunti i bottoni

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Updates the description displayed in the text area.
     *
     * @param description the new description to be displayed
     */
    public void updateDescription(String description) {
        descriptionArea.setText(description);
    }

    /**
     * Updates the font sizes of all components within the inventory panel.
     * Specifically, it sets the font size of all JButton components and the description area.
     *
     * @param fontSize the new font size to be applied to the components
     */
    private void updateFontSizes(int fontSize) {
        for (Component comp : inventoryPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setFont(new Font("Arial", Font.PLAIN, fontSize));
            }
        }
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, fontSize)); 
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}




