package mindescape.view.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The ViewUtils class provides utility methods for creating and styling UI components.
 */
public class ViewUtils {

    private static final Font defaultFont = new Font("Serif", Font.PLAIN, 18);
    private static final Font titleFont = new Font("Serif", Font.BOLD, 36);
    private static final Color buttonColor = new Color(30, 32, 34); 
    private static final Color buttonHoverColor = new Color(50, 53, 55);
    private static final Color labelColor = new Color(230, 230, 230);
    private static final Color panelColor = new Color(20, 20, 20); 
    private static final Color textFieldBackground = new Color(45, 48, 50); 
    private static final Color textFieldTextColor = new Color(255, 255, 255); 
    private static final Color textAreaBackground = new Color(40, 43, 45); 
    private static final Color textAreaTextColor = new Color(220, 220, 220); 

    /**
     * Creates a styled JButton with specified text.
     *
     * @param text the text to be displayed on the button
     * @return a styled JButton with the specified text
     */
    public static JButton createStyledButton(final String text) {
        final JButton button = new JButton(text);
        button.setFont(defaultFont);
        button.setForeground(Color.WHITE);
        button.setBackground(buttonColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(150, 40));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(buttonHoverColor);
                button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(buttonColor);
                button.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
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
        label.setFont(defaultFont);
        label.setForeground(labelColor);
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
        panel.setBackground(panelColor);
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
        textField.setFont(defaultFont);
        textField.setBackground(textFieldBackground);
        textField.setForeground(textFieldTextColor);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
        textArea.setFont(defaultFont);
        textArea.setBackground(textAreaBackground);
        textArea.setForeground(textAreaTextColor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretColor(new Color(255, 255, 255));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(textAreaBackground);
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
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(255, 69, 0));  // Fiery orange-red for tension and urgency
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(400, 80));
        return titleLabel;
    }
}
