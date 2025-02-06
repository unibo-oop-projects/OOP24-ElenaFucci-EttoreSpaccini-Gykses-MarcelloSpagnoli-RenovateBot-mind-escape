package mindescape.view.enigmapassword.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mindescape.controller.enigmapassword.api.EnigmaPasswordController;
import mindescape.view.enigmapassword.api.EnigmaPasswordView;

public class EnigmaPasswordViewImpl implements EnigmaPasswordView {

    private final JPanel panel; 
    private final JTextField passwordField;
    private final JLabel resultLabel;

    public EnigmaPasswordViewImpl(final EnigmaPasswordController controller) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.DARK_GRAY);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Enter the Password", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.panel.add(titleLabel, gbc);

        passwordField = new JTextField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.panel.add(passwordField, gbc);

        JButton checkButton = new JButton("Check Password");
        checkButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.panel.add(checkButton, gbc);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.panel.add(quitButton, gbc);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.panel.add(resultLabel, gbc);

        checkButton.addActionListener(e ->  {
                controller.handleInput(passwordField.getText());
            });

        quitButton.addActionListener(e -> {
                controller.quit();
            });
    }

    @Override
    public void draw() {
    }

    @Override
    public JPanel getPanel() {
        return this.panel; 
    }

    @Override
    public void showResult(boolean solved) {
        resultLabel.setText(solved ? "The enigma has been solved!" : "Wrong password. Try again.");
    }
    
}
