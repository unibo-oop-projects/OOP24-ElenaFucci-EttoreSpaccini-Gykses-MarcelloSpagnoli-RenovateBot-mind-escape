package mindescape.view.caesarcipher.impl;

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

import mindescape.controller.caesarcipher.api.CaesarCipherController;
import mindescape.view.caesarcipher.api.CaesarCipherView;

/**
 * The {@code CaesarCipherViewImpl} class implements {@code CaesarCipherView} to provide a graphical user
 * interface for displaying and decrypting an encrypted Caesar Cipher text.
 */
public class CaesarCipherViewImpl implements CaesarCipherView {

    private final JPanel panel;
    private final JTextField shiftField;
    private final JLabel encryptedLabel;
    private final JTextField decryptedField;
    private final JButton decryptButton;

    /**
     * Constructs a {@code CaesarCipherViewImpl} with the given controller.
     *
     * @param controller the controller managing the Caesar Cipher enigma
     */
    public CaesarCipherViewImpl(CaesarCipherController controller) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Caesar Cipher", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.panel.add(titleLabel, gbc);

        encryptedLabel = new JLabel("Encrypted Text: " + controller.getEncryptedText(), SwingConstants.CENTER);
        encryptedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        encryptedLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.panel.add(encryptedLabel, gbc);

        JLabel shiftLabel = new JLabel("Enter Shift Value: ", SwingConstants.CENTER);
        shiftLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        shiftLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.panel.add(shiftLabel, gbc);

        shiftField = new JTextField(5);
        shiftField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        this.panel.add(shiftField, gbc);

        decryptButton = new JButton("Decrypt");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.panel.add(decryptButton, gbc);

        JLabel resultLabel = new JLabel("Decrypted Text: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.panel.add(resultLabel, gbc);

        decryptedField = new JTextField(15);
        decryptedField.setFont(new Font("Arial", Font.PLAIN, 14));
        decryptedField.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.panel.add(decryptedField, gbc);

        decryptButton.addActionListener(e -> controller.handleInput(shiftField.getText()));
    }

    /**
     * Does nothing in this implementation.
     */
    @Override
    public void draw() {
    }

    /**
     * Retrieves the panel associated with this view.
     *
     * @return the {@code JPanel} component of the view
     */
    @Override
    public JPanel getPanel() {
        return this.panel; 
    }

    /**
     * Displays the decrypted result in the corresponding text field.
     *
     * @param result the decrypted text
     */
    @Override
    public void showResult(final String result) {
        decryptedField.setText(result);
    }
}
