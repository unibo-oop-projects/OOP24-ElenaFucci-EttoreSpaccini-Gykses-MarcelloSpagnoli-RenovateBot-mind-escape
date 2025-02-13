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
public final class GuideViewImpl extends JPanel implements GuideView {

    private static final long serialVersionUID = 1L;
    private static final String FONT_NAME = "Arial";
    private static final int INITIAL_CAPACITY = 25;
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
        this.titleLabel = new JLabel("MindEscape Guide", SwingConstants.CENTER);
        this.guideTextArea = new JTextArea();
        this.backButton = new JButton("Menu");
        setUpComponents();
    }

    private void setUpComponents() {
        this.setLayout(new BorderLayout());

        this.titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        this.add(titleLabel, BorderLayout.NORTH);

        this.guideTextArea.setEditable(false);
        this.guideTextArea.setLineWrap(true);
        this.guideTextArea.setWrapStyleWord(true);
        this.guideTextArea.setText(loadGuideText());
        this.guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, 16));

        final JScrollPane scrollPane = new JScrollPane(guideTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        this.backButton.addActionListener(e -> this.guideController.quit());
        this.add(backButton, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = getWidth();
                final int calculatedFontSize = width / 30;
                final int newSize = Math.max(12, calculatedFontSize);

                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, newSize));
                guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
                backButton.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
            }
        });
    }

    private String loadGuideText() {
        final StringBuilder content = new StringBuilder(INITIAL_CAPACITY);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("guide/guide.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line; 
            do {
                line = reader.readLine();
                if (line != null) {
                    content.append(line).append('\n');
                } 
            } while (line != null && !"".equals(line));
        } catch (final IOException e) {
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
