package mindescape.view.caesarcipher.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

    private final static String FONT_NAME = "Arial";

    private final JPanel panel;
    private final JTextField shiftField;
    private final JLabel encryptedLabel;
    private final JTextField decryptedField;
    private final JButton decryptButton;
    private final JButton quitButton;

    private final JLabel titleLabel;
    private final JLabel resultLabel;
    private final JLabel shiftLabel;

    /**
     * Constructs a {@code CaesarCipherViewImpl} with the given controller.
     *
     * @param controller the controller managing the Caesar Cipher enigma
     */
    public CaesarCipherViewImpl(final CaesarCipherController controller) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.DARK_GRAY);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        titleLabel = new JLabel("Caesar Cipher", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.panel.add(titleLabel, gbc);

        encryptedLabel = new JLabel("Encrypted Text: " + controller.getEncryptedText(), SwingConstants.CENTER);
        encryptedLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.panel.add(encryptedLabel, gbc);

        shiftLabel = new JLabel("Enter Shift Value: ", SwingConstants.CENTER);
        shiftLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.panel.add(shiftLabel, gbc);

        shiftField = new JTextField(5);
        gbc.gridx = 1;
        this.panel.add(shiftField, gbc);

        decryptButton = new JButton("Decrypt");
        quitButton = new JButton("Quit");
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.panel.add(decryptButton, gbc);
        
        gbc.gridx = 1;
        this.panel.add(quitButton, gbc);

        resultLabel = new JLabel("Decrypted Text: ", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.panel.add(resultLabel, gbc);

        decryptedField = new JTextField(15);
        decryptedField.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.panel.add(decryptedField, gbc);

        decryptButton.addActionListener(e -> controller.handleInput(shiftField.getText()));
        quitButton.addActionListener(e -> controller.quit());

        this.panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(12, width / 30);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSize + 6));
                encryptedLabel.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                shiftLabel.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                resultLabel.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                shiftField.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                decryptedField.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                decryptButton.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
                quitButton.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
            }
        });
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
