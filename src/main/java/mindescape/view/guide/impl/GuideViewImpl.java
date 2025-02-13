package mindescape.view.guide.impl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import mindescape.controller.guide.api.GuideController;
import mindescape.view.guide.api.GuideView;

/**
 * Implementation of the GuideView.
 */
public class GuideViewImpl extends JPanel implements GuideView {

    private static final long serialVersionUID = 1L;
    private final static String FONT_NAME = "Arial";
    private final JLabel titleLabel;
    private final JTextArea guideTextArea;
    private final JButton backButton;
    private final GuideController guideController;

    /**
     * Constructor of the GuideViewImpl.
     * @param guideController the controller of the guide.
     */
    public GuideViewImpl(final GuideController guideController) {
        this.guideController = guideController;
        this.setLayout(new BorderLayout());

        this.titleLabel = new JLabel("MindEscape Guide", SwingConstants.CENTER);
        this.titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        this.add(titleLabel, BorderLayout.NORTH);

        this.guideTextArea = new JTextArea();
        this.guideTextArea.setEditable(false);
        this.guideTextArea.setLineWrap(true);
        this.guideTextArea.setWrapStyleWord(true);
        this.guideTextArea.setText(loadGuideText());
        this.guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, 16));

        final JScrollPane scrollPane = new JScrollPane(guideTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        this.backButton = new JButton("Menu");
        this.backButton.addActionListener(e -> this.guideController.quit());
        this.add(backButton, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(final ComponentEvent e) {
                final int newSize = Math.max(12, getWidth() / 30);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, newSize));
                guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
                backButton.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
            }
        });
    }

    private String loadGuideText() {
        final StringBuilder content = new StringBuilder();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("guide/guide.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (final IOException | NullPointerException e) {
            content.append("Failed to load guide.txt");
        }
        return content.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }
}
