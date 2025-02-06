package mindescape.view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mindescape.controller.api.ClickableController;
import mindescape.view.api.View;

public class MenuView extends JPanel implements View {

    private static final long serialVersionUID = 1L;
    private final ClickableController menuController;

    public MenuView(final ClickableController menuController) {
        this.menuController = menuController;
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton quitButton = new JButton("Quit");
        
        newGameButton.addActionListener(e -> {
            menuController.handleInput("NEW_GAME");
            System.out.println("New Game");
        });
        loadGameButton.addActionListener(e -> {
            menuController.handleInput("LOAD_GAME");
            System.out.println("Load Game");
        });
        quitButton.addActionListener(e -> {
            menuController.handleInput("QUIT");
            System.out.println("Quit");
        });
        
        super.add(newGameButton);
        super.add(loadGameButton);
        super.add(quitButton);
    }

    @Override
    public void draw() {
        super.setVisible(true);
        super.repaint();
    }

    @Override
    public JPanel getPanel() {
        return this;
    }
}
