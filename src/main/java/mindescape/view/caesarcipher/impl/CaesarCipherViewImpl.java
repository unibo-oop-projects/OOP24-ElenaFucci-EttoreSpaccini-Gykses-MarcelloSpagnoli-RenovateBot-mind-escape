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
public final class CaesarCipherViewImpl implements CaesarCipherView {

    private static final String FONT_NAME = "Arial";
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private static final Color LABEL_COLOR = Color.WHITE;
    private static final String TITLE = "Caesar Cipher";
    private static final String ENCRYPTED_TEXT_LABEL = "Encrypted Text: ";
    private static final String SHIFT_LABEL = "Enter Shift Value: ";
    private static final String DECRYPT_BUTTON_LABEL = "Decrypt";
    private static final String QUIT_BUTTON_LABEL = "Quit";
    private static final String RESULT_LABEL = "Decrypted Text: ";
    private static final int TEXT_FIELD_COLUMNS = 5;
    private static final int DECRYPTED_TEXT_FIELD_COLUMNS = 15;
    private static final int MIN_FONT_SIZE = 12;
    private static final int FONT_SIZE_DIVISOR = 30;
    private static final int TITLE_FONT_SIZE_ADJUSTMENT = 6;
    private static final int RESULT_ROW = 5;


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
        this.panel.setBackground(BACKGROUND_COLOR);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        titleLabel = new JLabel(TITLE, SwingConstants.CENTER);
        titleLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.panel.add(titleLabel, gbc);

        encryptedLabel = new JLabel(ENCRYPTED_TEXT_LABEL + controller.getEncryptedText(), SwingConstants.CENTER);
        encryptedLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.panel.add(encryptedLabel, gbc);

        shiftLabel = new JLabel(SHIFT_LABEL, SwingConstants.CENTER);
        shiftLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.panel.add(shiftLabel, gbc);

        shiftField = new JTextField(TEXT_FIELD_COLUMNS);
        gbc.gridx = 1;
        this.panel.add(shiftField, gbc);

        decryptButton = new JButton(DECRYPT_BUTTON_LABEL);
        quitButton = new JButton(QUIT_BUTTON_LABEL);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.panel.add(decryptButton, gbc);

        gbc.gridx = 1;
        this.panel.add(quitButton, gbc);

        resultLabel = new JLabel(RESULT_LABEL, SwingConstants.CENTER);
        resultLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.panel.add(resultLabel, gbc);

        decryptedField = new JTextField(DECRYPTED_TEXT_FIELD_COLUMNS);
        decryptedField.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = RESULT_ROW;
        gbc.gridwidth = 2;
        this.panel.add(decryptedField, gbc);

        decryptButton.addActionListener(e -> controller.handleInput(shiftField.getText()));
        quitButton.addActionListener(e -> controller.quit());

        this.panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(MIN_FONT_SIZE, width / FONT_SIZE_DIVISOR);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSize + TITLE_FONT_SIZE_ADJUSTMENT));
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
