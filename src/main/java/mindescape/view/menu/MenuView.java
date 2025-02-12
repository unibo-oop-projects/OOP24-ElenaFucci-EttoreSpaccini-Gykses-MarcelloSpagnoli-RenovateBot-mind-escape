package mindescape.view.menu;
import javax.swing.*;

import mindescape.controller.core.api.ClickableController;
import mindescape.view.api.View;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuView implements View {
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
        gbc.fill = GridBagConstraints.BOTH;
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton guideButton = new JButton("Guide");
        JButton quitButton = new JButton("Quit");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        guideButton.setFont(new Font("Arial", Font.BOLD, 24));
        quitButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(newGameButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(loadGameButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(guideButton, gbc);
        gbc.gridy = 3;
        buttonPanel.add(quitButton, gbc);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        newGameButton.addActionListener(e -> this.menuController.handleInput("NEW_GAME"));
        loadGameButton.addActionListener(e -> this.menuController.handleInput("LOAD_GAME"));
        guideButton.addActionListener(e -> this.menuController.handleInput("GUIDE"));
        quitButton.addActionListener(e -> this.menuController.handleInput("QUIT"));
        this.panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(24, width / 15);
                titleLabel.setFont(new Font("Serif", Font.BOLD, fontSize));
                newGameButton.setFont(new Font("Arial", Font.BOLD, fontSize / 2));
                loadGameButton.setFont(new Font("Arial", Font.BOLD, fontSize / 2));
                guideButton.setFont(new Font("Arial", Font.BOLD, fontSize / 2));
                quitButton.setFont(new Font("Arial", Font.BOLD, fontSize / 2));
                newGameButton.setPreferredSize(new Dimension(width / 3, width / 12));
                loadGameButton.setPreferredSize(new Dimension(width / 3, width / 12));
                guideButton.setPreferredSize(new Dimension(width / 3, width / 12));
                quitButton.setPreferredSize(new Dimension(width / 3, width / 12));
            }
        });
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
