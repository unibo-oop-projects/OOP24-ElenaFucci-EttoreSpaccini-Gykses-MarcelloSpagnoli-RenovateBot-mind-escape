package mindescape.view.impl;

import java.util.Map;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.MainView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainViewImpl implements MainView {

    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT,
        KeyEvent.VK_E, UserInput.INTERACT,
        KeyEvent.VK_I, UserInput.INVENTORY
    );
    private final MainController mainController;
    private JPanel currentPanel;
    private final JFrame frame = new JFrame("Mind Escape");

    public MainViewImpl(final MainController controller) {
        this.mainController = controller;
        this.currentPanel = new JPanel(); // Initialize with an empty panel

        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                Objects.requireNonNull(e);
                final UserInput input = KEY_MAPPER.get(e.getKeyCode());
                if (input != null) {
                    mainController.getController().handleInput(input);
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: add save on file
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    @Override
    public void setPanel(final JPanel panel) {
        this.frame.remove(this.currentPanel);  
        this.currentPanel = panel;
        this.frame.add(this.currentPanel);  
        this.currentPanel.setVisible(true);
        this.currentPanel.setFocusable(true);
        this.currentPanel.requestFocusInWindow(); 
        this.show();
        this.currentPanel.revalidate();
        this.currentPanel.repaint();
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel.setVisible(true);
            frame.repaint();
            frame.revalidate();
        });
    }
}