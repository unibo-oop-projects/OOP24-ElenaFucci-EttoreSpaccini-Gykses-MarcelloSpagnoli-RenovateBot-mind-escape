package mindescape.view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mindescape.controller.api.ClickableController;
import mindescape.view.api.View;

public class MenuView implements View {

    private static final long serialVersionUID = 1L;
    private final ClickableController menuController;
    private final JPanel panel = new JPanel();

    public MenuView(final ClickableController menuController) {
        this.menuController = menuController;
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20, 20, 20));  // Dark background
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));  // Adds padding around the content
        JLabel titleLabel = new JLabel("Mind Escape", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);  // To keep the background of buttons visible
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Adjust padding between buttons
        JButton newGameButton = createStyledButton("New Game");
        JButton loadGameButton = createStyledButton("Load Game");
        JButton quitButton = createStyledButton("Quit");        
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(newGameButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(loadGameButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(quitButton, gbc);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        /*
         * handlers for the buttons
         */
        newGameButton.addActionListener(e -> menuController.handleInput("NEW_GAME"));
        loadGameButton.addActionListener(e -> menuController.handleInput("LOAD_GAME"));
        quitButton.addActionListener(e -> menuController.handleInput("QUIT"));
    }

    private JButton createStyledButton(String text) {
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

    @Override
    public void draw() {
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}