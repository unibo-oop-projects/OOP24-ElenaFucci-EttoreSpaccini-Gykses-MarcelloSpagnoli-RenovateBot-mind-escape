package mindescape.view.guide.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import mindescape.controller.guide.api.GuideController;
import mindescape.view.guide.api.GuideView;

public class GuideViewImpl extends JPanel implements GuideView {
    private final static String GUIDE_TEXT = """
            Benvenuto nella guida di MindEscape!
            Questo gioco è un'avventura testuale in cui dovrai risolvere enigmi e rompicapi per avanzare.
            Ogni stanza nasconde un enigma che dovrai risolvere per poter proseguire.
            Buona fortuna!
            """;
    private final static String FONT_NAME = "Arial";

    private final JLabel titleLabel;
    private final JTextArea guideTextArea;
    private final JButton backButton;
    private final GuideController guideController;

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
        this.guideTextArea.setText(GUIDE_TEXT);
        this.guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, 16));

        final JScrollPane scrollPane = new JScrollPane(guideTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        this.backButton = new JButton("Menu");
        this.backButton.addActionListener(e -> this.guideController.quit());
        this.add(backButton, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(800, 600));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                final int newSize = Math.max(12, getWidth() / 30);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, newSize));
                guideTextArea.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
                backButton.setFont(new Font(FONT_NAME, Font.PLAIN, newSize - 4));
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this;
    }
}
