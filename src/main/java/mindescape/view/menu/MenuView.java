package mindescape.view.menu;

import mindescape.controller.core.api.ClickableController;
import mindescape.view.api.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

/**
 * The MenuView class is responsible for creating the menu view of the game.
 */
public class MenuView implements View {

    private static final int BORDER_SIZE = 50;
    private static final int INSET_SIZE = 20;
    private static final int TITLE_FONT_SIZE = 48;
    private static final int BUTTON_FONT_SIZE = 24;
    private static final Color BACKGROUND_COLOR = new Color(20, 20, 20);
    private static final Color TITLE_COLOR = Color.WHITE;
    private final ClickableController menuController;
    private final JPanel panel = new JPanel();

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
        final JLabel titleLabel = createTitleLabel();
        final JPanel buttonPanel = createButtonPanel();
        addButtonsToPanel(buttonPanel);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        addComponentListener(titleLabel);
    }

    /**
     * Initializes the panel with a BorderLayout, sets its background color,
     * and applies an empty border with a specified size.
     */
    private void initializePanel() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
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
     * Creates a button panel with a GridBagLayout and an opaque background.
     * @return the created button panel 
     */
    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        return buttonPanel;
    }

    /**
     * Adds buttons to the specified button panel with the specified text, gridy, and action command.
     * @param buttonPanel the button panel to add the buttons to
     */
    private void addButtonsToPanel(final JPanel buttonPanel) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        addButton(buttonPanel, gbc, "New Game", 0, "NEW_GAME");
        addButton(buttonPanel, gbc, "Load Game", 1, "LOAD_GAME");
        addButton(buttonPanel, gbc, "Guide", 2, "GUIDE");
        addButton(buttonPanel, gbc, "Quit", 3, "QUIT");
    }

    /**
     * Adds a button to the specified button panel with the specified text, gridy, and action command.
     * @param buttonPanel
     * @param gbc
     * @param text
     * @param gridy
     * @param actionCommand
     */
    private void addButton(
        final JPanel buttonPanel, 
        final GridBagConstraints gbc, 
        final String text, 
        final int gridy, final 
        String actionCommand
    ) {
        final JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        gbc.gridy = gridy;
        buttonPanel.add(button, gbc);
        button.addActionListener(e -> this.menuController.handleInput(actionCommand));
    }

    /**
     * Adds a component listener to the specified title label.
     * @param titleLabel the title label to add the component listener to
     */
    private void addComponentListener(final JLabel titleLabel) {
        this.panel.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(BUTTON_FONT_SIZE, width / 15);
                titleLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
                updateButtonFontsAndSizes(width, fontSize);
            }
        });
    }

    /**
     * Updates the fonts and sizes of the buttons based on the specified width and font size.
     * @param width
     * @param fontSize
     */
    private void updateButtonFontsAndSizes(final int width, final int fontSize) {
        final int buttonFontSize = fontSize / 2;
        final Dimension buttonSize = new Dimension(width / 3, width / 12);

        for (final Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                final var button = (JButton) component;
                button.setFont(new Font("Arial", Font.BOLD, buttonFontSize));
                button.setPreferredSize(buttonSize);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
