package mindescape.view.menu;

import javax.swing.*;
import java.awt.*;
import mindescape.controller.core.api.ClickableController;
import mindescape.view.api.View;
import mindescape.view.utils.ViewUtils;

public class MenuView implements View {

    private static final long serialVersionUID = 1L;
    private final ClickableController menuController;
    private final JPanel panel = new JPanel();

    public MenuView(final ClickableController menuController) {
        this.menuController = menuController;
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        JLabel titleLabel = new JLabel("Mind Escape", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        JButton newGameButton = ViewUtils.createStyledButton("New Game");
        JButton loadGameButton = ViewUtils.createStyledButton("Load Game");
        JButton quitButton = ViewUtils.createStyledButton("Quit");
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