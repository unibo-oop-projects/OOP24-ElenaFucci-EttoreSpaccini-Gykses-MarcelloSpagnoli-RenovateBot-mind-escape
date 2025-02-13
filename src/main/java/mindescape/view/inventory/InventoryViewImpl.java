package mindescape.view.inventory;

import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.view.api.View;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
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
    private int buttonSize = 70;

    /**
     * Constructs an InventoryViewImpl with the specified InventoryControllerImpl.
     */
    public InventoryViewImpl(final InventoryControllerImpl controller) {
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
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
                buttonSize = Math.max(40, width / 8);
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

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    public void updateInventoryButtons(final Set<Pickable> items) {
        inventoryPanel.removeAll();
        for (final Pickable item : items) {
            final JButton itemButton = new JButton();
            itemButton.setFocusable(false);
            itemButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            itemButton.setMinimumSize(new Dimension(buttonSize, buttonSize));
            itemButton.setMaximumSize(new Dimension(buttonSize, buttonSize));
            try {
                final ImageIcon icon = new ImageIcon(getIcon(item).getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
                itemButton.setIcon(icon);
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.handleItemClick(item);
                }
            });
            inventoryPanel.add(itemButton);
        }
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    private BufferedImage getIcon(final Pickable item) throws IOException {
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
        
        return ImageIO.read(getClass().getClassLoader().getResource("pickable/" + imagePath));
    }
    
    public void updateDescription(final String description) {
        descriptionArea.setText(description);
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
