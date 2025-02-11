package mindescape.view.enigmapuzzle.impl;

import javax.swing.*;

import mindescape.controller.enigmapuzzle.impl.EnigmaPuzzleControllerImpl;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView {

    private List<ImageButton> buttons = new ArrayList<>();

    public EnigmaPuzzleViewImpl(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows * cols; i++) {
            ImageButton button = new ImageButton();
            buttons.add(button);
            add(button);
        }
    }

    // Metodo per associare il controller alla view
    public void setController(EnigmaPuzzleControllerImpl controller) {
        for (ImageButton button : buttons) {
            button.addActionListener(controller);  // Aggiungi il controller come listener a ogni pulsante
        }
    }

    public List<ImageButton> getButtons() {
        return buttons;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public JPanel getPanel() {
        return this;

    }
}