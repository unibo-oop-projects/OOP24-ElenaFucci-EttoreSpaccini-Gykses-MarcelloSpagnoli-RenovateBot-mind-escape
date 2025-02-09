package mindescape.view.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The ViewUtils class provides utility methods for creating and styling UI components.
 */
public class ViewUtils {

    private static final Font defaultFont = new Font("Serif", Font.PLAIN, 18); // Changed to Serif for a more classic feel
    private static final Font titleFont = new Font("Serif", Font.BOLD, 36);  // More dramatic title font
    private static final Color buttonColor = new Color(30, 32, 34); // Darker button color for tension
    private static final Color buttonHoverColor = new Color(50, 53, 55); // Slightly lighter for hover effect
    private static final Color labelColor = new Color(230, 230, 230); // Light color for contrast
    private static final Color panelColor = new Color(20, 20, 20); // Almost black background for psychological effect
    private static final Color textFieldBackground = new Color(45, 48, 50); // Darker text field
    private static final Color textFieldTextColor = new Color(255, 255, 255); // White text for contrast
    private static final Color textAreaBackground = new Color(40, 43, 45); // Slightly softer background for text areas
    private static final Color textAreaTextColor = new Color(220, 220, 220); // Softer white for text in text areas

    /**
     * Creates a styled JButton with specified text.
     *
     * @param text the text to be displayed on the button
     * @return a styled JButton with the specified text
     */
    public static JButton createStyledButton(final String text) {
        JButton button = new JButton(text);
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
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
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
        JPanel panel = new JPanel();
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
    public static JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
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
    public static JScrollPane createStyledTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setFont(defaultFont);
        textArea.setBackground(textAreaBackground);
        textArea.setForeground(textAreaTextColor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretColor(new Color(255, 255, 255));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
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
    public static JLabel createTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(255, 69, 0));  // Fiery orange-red for tension and urgency
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(400, 80));
        return titleLabel;
    }
}
