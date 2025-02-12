package mindescape.view.enigmapassword.impl;

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
import mindescape.controller.enigmapassword.api.EnigmaPasswordController;
import mindescape.view.enigmapassword.api.EnigmaPasswordView;

/**
 * The {@code EnigmaPasswordViewImpl} class implements {@code EnigmaPasswordView} to provide a user interface
 * for entering a password and verifying it against the enigma's solution.
 */
public class EnigmaPasswordViewImpl implements EnigmaPasswordView {

    private static final String FONT_NAME = "Arial";
    private static final int TITLE_FONT_SIZE = 18;
    private static final int RESULT_FONT_SIZE = 20;
    private static final int BUTTON_FONT_SIZE = 14;
    private static final int PASSWORD_FIELD_COLUMNS = 15;
    private static final int INSET_SIZE = 10;
    private static final int MIN_FONT_SIZE = 12;
    private static final int FONT_SIZE_DIVISOR = 30;

    private final JPanel panel;
    private final JTextField passwordField;
    private final JLabel resultLabel;

    /**
     * Constructs an {@code EnigmaPasswordViewImpl} with the given controller.
     *
     * @param controller the controller managing the password enigma
     */
    public EnigmaPasswordViewImpl(final EnigmaPasswordController controller) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.DARK_GRAY);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        final JLabel titleLabel = new JLabel("Enter the Password", SwingConstants.CENTER);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.panel.add(titleLabel, gbc);

        this.passwordField = new JTextField(PASSWORD_FIELD_COLUMNS);
        this.passwordField.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.panel.add(passwordField, gbc);

        final JButton checkButton = new JButton("Check Password");
        checkButton.setFont(new Font(FONT_NAME, Font.BOLD, BUTTON_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.panel.add(checkButton, gbc);

        final JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(FONT_NAME, Font.BOLD, BUTTON_FONT_SIZE));
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.panel.add(quitButton, gbc);

        this.resultLabel = new JLabel("", SwingConstants.CENTER);
        this.resultLabel.setFont(new Font(FONT_NAME, Font.BOLD, RESULT_FONT_SIZE));
        this.resultLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.panel.add(resultLabel, gbc);

        checkButton.addActionListener(e -> controller.handleInput(passwordField.getText()));
        quitButton.addActionListener(e -> controller.quit());

        this.panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(MIN_FONT_SIZE, width / FONT_SIZE_DIVISOR);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSize + 6));
                resultLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSize + 4));
                passwordField.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                checkButton.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
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
     * Displays the result of the password check.
     *
     * @param solved {@code true} if the enigma is solved, {@code false} otherwise
     */
    @Override
    public void showResult(final boolean solved) {
        resultLabel.setText(solved ? "The enigma has been solved!" : "Wrong password. Try again.");
    }
}