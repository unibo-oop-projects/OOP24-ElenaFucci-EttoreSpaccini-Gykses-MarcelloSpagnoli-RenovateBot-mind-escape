package mindescape.view.menu;

import mindescape.controller.api.Controller;
import mindescape.controller.menu.MenuController;
import mindescape.view.api.View;
import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel implements View {

    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton quitButton;
    private static final long serialVersionUID = 1L;
    private final Controller controller;

    public MenuView(final Controller controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        newGameButton = createStyledButton("New Game");
        gbc.gridy = 0;
        add(newGameButton, gbc);
        
        loadGameButton = createStyledButton("Load Game");
        gbc.gridy = 1;
        add(loadGameButton, gbc);
        
        quitButton = createStyledButton("Quit");
        gbc.gridy = 2;
        add(quitButton, gbc);

        quitButton.addActionListener(e -> {
        });
        newGameButton.addActionListener(e -> {

        });
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }


    @Override
    public void draw() {
    }
}
