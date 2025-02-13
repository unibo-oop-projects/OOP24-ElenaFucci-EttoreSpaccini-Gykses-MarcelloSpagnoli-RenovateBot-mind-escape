package mindescape.view.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The ViewUtils class provides utility methods for creating and styling UI components.
 * This class should not be instantiated.
 */
public final class ViewUtils {

    private static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 18);
    private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 36);
    private static final Color BUTTON_COLOR = new Color(30, 32, 34);
    private static final Color BUTTON_HOVER_COLOR = new Color(50, 53, 55);
    private static final Color LABEL_COLOR = new Color(230, 230, 230);
    private static final Color PANEL_COLOR = new Color(20, 20, 20);
    private static final Color TEXT_FIELD_BACKGROUND = new Color(45, 48, 50);
    private static final Color TEXT_FIELD_TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_AREA_BACKGROUND = new Color(40, 43, 45);
    private static final Color TEXT_AREA_TEXT_COLOR = new Color(220, 220, 220);
    private static final Color TITLE_COLOR = new Color(255, 69, 0);
    private static final Color BORDER_COLOR = new Color(80, 80, 80);
    private static final Color BORDER_HOVER_COLOR = new Color(100, 100, 100);
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BORDER_THICKNESS = 2;
    private static final int TEXT_FIELD_PADDING = 5;
    private static final int TITLE_WIDTH = 400;
    private static final int TITLE_HEIGHT = 80;
    private static final int TEXT_AREA_PADDING = 10;

    /**
     * Private constructor to prevent instantiation.
     */
    private ViewUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Creates a styled JButton with specified text.
     *
     * @param text the text to be displayed on the button
     * @return a styled JButton
     */
    public static JButton createStyledButton(final String text) {
        final JButton button = new JButton(text);
        button.setFont(DEFAULT_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                button.setBackground(BUTTON_HOVER_COLOR);
                button.setBorder(BorderFactory.createLineBorder(BORDER_HOVER_COLOR, BORDER_THICKNESS));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
                button.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
            }
        });
        return button;
    }

    /**
     * Creates a styled JLabel with specified text.
     *
     * @param text the text to be displayed on the label
     * @return a styled JLabel
     */
    public static JLabel createStyledLabel(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(DEFAULT_FONT);
        label.setForeground(LABEL_COLOR);
        label.setOpaque(true);
        return label;
    }

    /**
     * Creates a styled JPanel.
     *
     * @return a styled JPanel
     */
    public static JPanel createStyledPanel() {
        final JPanel panel = new JPanel();
        panel.setBackground(PANEL_COLOR);
        panel.setLayout(new BorderLayout());
        return panel;
    }

    /**
     * Creates a styled JTextField.
     *
     * @param columns the number of columns for the text field
     * @return a styled JTextField
     */
    public static JTextField createStyledTextField(final int columns) {
        final JTextField textField = new JTextField(columns);
        textField.setFont(DEFAULT_FONT);
        textField.setBackground(TEXT_FIELD_BACKGROUND);
        textField.setForeground(TEXT_FIELD_TEXT_COLOR);
        textField.setBorder(BorderFactory.createEmptyBorder(TEXT_FIELD_PADDING, TEXT_FIELD_PADDING * 2, TEXT_FIELD_PADDING, TEXT_FIELD_PADDING * 2));
        return textField;
    }

    /**
     * Creates a styled JTextArea inside a JScrollPane.
     *
     * @param rows the number of rows
     * @param columns the number of columns
     * @return a JScrollPane containing a styled JTextArea
     */
    public static JScrollPane createStyledTextArea(final int rows, final int columns) {
        final JTextArea textArea = new JTextArea(rows, columns);
        textArea.setFont(DEFAULT_FONT);
        textArea.setBackground(TEXT_AREA_BACKGROUND);
        textArea.setForeground(TEXT_AREA_TEXT_COLOR);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(TEXT_AREA_PADDING, TEXT_AREA_PADDING, TEXT_AREA_PADDING, TEXT_AREA_PADDING));

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(TEXT_AREA_BACKGROUND);
        return scrollPane;
    }

    /**
     * Creates a large title JLabel.
     * Used for main titles in the UI.
     *
     * @param text the title text
     * @return a styled JLabel for titles
     */
    public static JLabel createTitleLabel(final String text) {
        final JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TITLE_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        return titleLabel;
    }
}
