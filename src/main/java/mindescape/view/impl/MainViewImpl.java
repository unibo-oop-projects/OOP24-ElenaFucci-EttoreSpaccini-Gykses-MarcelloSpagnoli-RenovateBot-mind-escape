package mindescape.view.impl;

import java.util.Map;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mindescape.controller.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.MainView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainViewImpl extends JFrame implements MainView {

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

    public MainViewImpl(final MainController controller) {
        this.mainController = controller;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                Objects.requireNonNull(e);
                final UserInput input = KEY_MAPPER.get(e.getKeyCode());
                if (input != null) {
                    mainController.getController().handleInput(input);
                }
            }
        });
        this.add(this.currentPanel);
        
        super.setName("Mind Escape");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: add save on file
    }
    
    @Override
    public void setPanel(final JPanel panel) {
        this.remove(this.currentPanel);
        this.currentPanel = panel;
        this.add(this.currentPanel);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void show() {
       //this.currentPanel.draw();
    }
 
}
