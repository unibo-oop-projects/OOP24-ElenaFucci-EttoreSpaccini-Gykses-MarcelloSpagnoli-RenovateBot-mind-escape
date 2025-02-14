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

import org.checkerframework.checker.units.qual.C;

/**
 * The ViewUtils class provides utility methods for creating and styling UI components.
 * This class should not be instantiated.
 */
public final class ViewUtils {

    /**
 * The Style class provides a collection of constants used for styling UI components.
 * This class contains various fonts, colors, and dimensions that can be applied to
 * different UI elements to maintain a consistent look and feel throughout the application.
 */
public static class Style {
    /**
     * The default font used for most UI components.
     */
    public static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 18);

    /**
     * The font used for titles in the UI.
     */
    public static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 36);

    /**
     * The background color of buttons.
     */
    public static final Color BUTTON_COLOR = new Color(30, 32, 34);

    /**
     * The background color of buttons when hovered over.
     */
    public static final Color BUTTON_HOVER_COLOR = new Color(50, 53, 55);

    /**
     * The color of labels.
     */
    public static final Color LABEL_COLOR = new Color(230, 230, 230);

    /**
     * The background color of panels.
     */
    public static final Color PANEL_COLOR = new Color(20, 20, 20);

    /**
     * The background color of text fields.
     */
    public static final Color TEXT_FIELD_BACKGROUND = new Color(45, 48, 50);

    /**
     * The text color of text fields.
     */
    public static final Color TEXT_FIELD_TEXT_COLOR = Color.WHITE;

    /**
     * The background color of text areas.
     */
    public static final Color TEXT_AREA_BACKGROUND = new Color(40, 43, 45);

    /**
     * The text color of text areas.
     */
    public static final Color TEXT_AREA_TEXT_COLOR = new Color(220, 220, 220);

    /**
     * The color of titles.
     */
    public static final Color TITLE_COLOR = new Color(255, 69, 0);

    /**
     * The border color of components.
     */
    public static final Color BORDER_COLOR = new Color(80, 80, 80);

    /**
     * The border color of components when hovered over.
     */
    public static final Color BORDER_HOVER_COLOR = new Color(100, 100, 100);

    /**
     * The width of buttons.
     */
    public static final int BUTTON_WIDTH = 150;

    /**
     * The height of buttons.
     */
    public static final int BUTTON_HEIGHT = 40;

    /**
     * The thickness of borders.
     */
    public static final int BORDER_THICKNESS = 2;

    /**
     * The padding inside text fields.
     */
    public static final int TEXT_FIELD_PADDING = 5;

    /**
     * The width of title labels.
     */
    public static final int TITLE_WIDTH = 400;

    /**
     * The height of title labels.
     */
    public static final int TITLE_HEIGHT = 80;

    /**
     * The padding inside text areas.
     */
    public static final int TEXT_AREA_PADDING = 10;
}

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
        button.setFont(Style.DEFAULT_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(Style.BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Style.BORDER_COLOR, Style.BORDER_THICKNESS));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(Style.BUTTON_WIDTH, Style.BUTTON_HEIGHT));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                button.setBackground(Style.BUTTON_HOVER_COLOR);
                button.setBorder(BorderFactory.createLineBorder(Style.BORDER_HOVER_COLOR, Style.BORDER_THICKNESS));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                button.setBackground(Style.BUTTON_COLOR);
                button.setBorder(BorderFactory.createLineBorder(Style.BORDER_COLOR, Style.BORDER_THICKNESS));
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
        label.setFont(Style.DEFAULT_FONT);
        label.setForeground(Style.LABEL_COLOR);
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
        panel.setBackground(Style.PANEL_COLOR);
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
        textField.setFont(Style.DEFAULT_FONT);
        textField.setBackground(Style.TEXT_FIELD_BACKGROUND);
        textField.setForeground(Style.TEXT_FIELD_TEXT_COLOR);
        textField.setBorder(BorderFactory.createEmptyBorder(
            Style.TEXT_FIELD_PADDING, Style.TEXT_FIELD_PADDING * 2,
            Style.TEXT_FIELD_PADDING, Style.TEXT_FIELD_PADDING * 2
        ));
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
        textArea.setFont(Style.DEFAULT_FONT);
        textArea.setBackground(Style.TEXT_AREA_BACKGROUND);
        textArea.setForeground(Style.TEXT_AREA_TEXT_COLOR);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(
            Style.TEXT_AREA_PADDING, 
            Style.TEXT_AREA_PADDING, 
            Style.TEXT_AREA_PADDING, 
            Style.TEXT_AREA_PADDING
        ));

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(Style.TEXT_AREA_BACKGROUND);
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
        titleLabel.setFont(Style.TITLE_FONT);
        titleLabel.setForeground(Style.TITLE_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(Style.TITLE_WIDTH, Style.TITLE_HEIGHT));
        return titleLabel;
    }

    /**
     * Creates a styled JScrollPane containing a JTextArea.
     *
     * @param textArea the JTextArea to be displayed in the scroll pane
     * @return a styled JScrollPane
     */
    public static JScrollPane createStyledScrollPane(final JLabel label) {
        final JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    /**
     * Creates a styled JLabel with specified text.
     *
     * @param text the text to be displayed on the label
     * @return a styled JLabel
     */
    public static JLabel enigmaJLabel(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(Style.DEFAULT_FONT);
        label.setForeground(Style.LABEL_COLOR);
        label.setOpaque(true);
        label.setBackground(Color.DARK_GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(
            Style.TEXT_FIELD_PADDING, Style.TEXT_FIELD_PADDING * 2,
            Style.TEXT_FIELD_PADDING, Style.TEXT_FIELD_PADDING * 2
        ));

        return label;
    }

}
