package mindescape.view.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The ViewUtils class provides utility methods for creating and styling UI components.
 */
public class ViewUtils {

    /**
     * Creates a styled JButton with specified text.
     * The button has a preferred size of 250x60, uses Arial bold font of size 18,
     * has a black background, gold foreground, and a gold border.
     * The button's background color changes when the mouse enters or exits the button area.
     *
     * @param text the text to be displayed on the button
     * @return a styled JButton with the specified text
     */
    public static JButton createStyledButton(final String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 60));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(0, 0, 0));
        button.setForeground(new Color(255, 215, 0));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 50, 50)); 
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0));  
            }
        });
    
        return button;
    }
    
}
