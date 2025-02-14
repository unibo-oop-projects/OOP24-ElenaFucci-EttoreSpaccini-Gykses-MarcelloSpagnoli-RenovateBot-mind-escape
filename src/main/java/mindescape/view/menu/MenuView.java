package mindescape.view.menu;

import mindescape.controller.core.api.ClickableController;
import mindescape.view.api.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The MenuView class is responsible for creating the menu view of the game.
 */
public class MenuView implements View {

    private static final Dimension PREFERED_SIZE = new Dimension(200, 50);
    private static final int BORDER_SIZE = 50;
    private static final int INSET_SIZE = 15;
    private static final int TITLE_FONT_SIZE = 48;
    private static final int BUTTON_FONT_SIZE = 24;
    private static final Color BACKGROUND_COLOR = new Color(20, 20, 20);
    private static final Color BUTTON_BG_COLOR = new Color(40, 40, 40);
    private static final Color TITLE_COLOR = Color.WHITE;
    private final ClickableController menuController;
    private final JPanel panel = new JPanel();
    private final JPanel buttonPanel = new JPanel(new GridBagLayout());
    private final JLabel titleLabel;

    /**
     * Constructs a new MenuView with the specified menu controller.
     * Initializes the panel, creates and adds the title label and button panel to the main panel.
     * Also adds a component listener to the title label.
     *
     * @param menuController the controller responsible for handling menu interactions
     */
    public MenuView(final ClickableController menuController) {
        this.menuController = menuController;
        initializePanel();
        titleLabel = createTitleLabel();
        addButtonsToPanel();
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        addComponentListener();
    }

    /**
     * Initializes the panel with a BorderLayout, sets its background color,
     * and applies an empty border with a specified size.
     */
    private void initializePanel() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        // **Assicuriamoci che il buttonPanel abbia lo stesso sfondo**
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(BACKGROUND_COLOR);
    }

    /**
     * Creates a title label with the specified text, font, color, and alignment.
     * @return the created title label
    */
    private JLabel createTitleLabel() {
        final JLabel titleLabel = new JLabel("Mind Escape", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(TITLE_COLOR);
        titleLabel.setOpaque(false);
        return titleLabel;
    }

    /**
     * Adds buttons to the button panel and centers them.
     */
    private void addButtonsToPanel() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        addButton(buttonPanel, gbc, "New Game", 0, "NEW_GAME");
        addButton(buttonPanel, gbc, "Load Game", 1, "LOAD_GAME");
        addButton(buttonPanel, gbc, "Guide", 2, "GUIDE");
        addButton(buttonPanel, gbc, "Quit", 3, "QUIT");
    }

    /**
     * Creates and adds a button to the specified button panel.
     * @param buttonPanel the panel where buttons are added
     * @param gbc layout constraints
     * @param text button text
     * @param gridy position in the grid
     * @param actionCommand command to handle the click
     */
    private void addButton(
        final JPanel buttonPanel, 
        final GridBagConstraints gbc, 
        final String text, 
        final int gridy, 
        final String actionCommand
    ) {
        final JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        button.setFocusPainted(false);
        button.setPreferredSize(PREFERED_SIZE);
        button.setBackground(BUTTON_BG_COLOR);
        button.setForeground(TITLE_COLOR);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setOpaque(true);

        gbc.gridy = gridy;
        buttonPanel.add(button, gbc);
        button.addActionListener(e -> this.menuController.handleInput(actionCommand));
    }

    /**
     * Adds a component listener to handle resizing events.
     */
    private void addComponentListener() {
        this.panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(BUTTON_FONT_SIZE, width / 15);
                titleLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
                updateButtonFontsAndSizes(width);
            }
        });
    }

    /**
     * Updates button font sizes and preferred sizes based on panel width.
     * @param width the current width of the panel
     */
    private void updateButtonFontsAndSizes(final int width) {
        final int buttonFontSize = Math.max(18, width / 30);
        final Dimension buttonSize = new Dimension(Math.max(200, width / 4), Math.max(50, width / 20));

        for (final var component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                final JButton button = (JButton) component;
                button.setFont(new Font("Arial", Font.BOLD, buttonFontSize));
                button.setPreferredSize(buttonSize);
            }
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The panel needs to be exposed to the caller for manipulation")
    public JPanel getPanel() {
        return this.panel;
    }
}
