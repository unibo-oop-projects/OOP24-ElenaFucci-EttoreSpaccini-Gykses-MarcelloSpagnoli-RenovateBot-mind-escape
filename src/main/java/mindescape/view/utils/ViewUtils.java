package mindescape.view.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

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
         * The background color of buttons.
         */
        public static final Color BUTTON_COLOR = new Color(30, 32, 34);

        /**
         * The background color of buttons when hovered over.
         */
        public static final Color BUTTON_HOVER_COLOR = new Color(50, 53, 55);

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
         * The background color of panels.
         */
        public static final Color PANEL_COLOR = new Color(20, 20, 20);
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
}
