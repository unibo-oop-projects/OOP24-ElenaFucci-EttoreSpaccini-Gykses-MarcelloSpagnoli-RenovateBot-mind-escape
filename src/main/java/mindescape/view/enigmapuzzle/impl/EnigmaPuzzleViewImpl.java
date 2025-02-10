package mindescape.view.enigmapuzzle.impl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import mindescape.controller.enigmapuzzle.impl.EnigmaPuzzleControllerImpl;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;

public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView {

    private List<ImageButton> buttons;
    private EnigmaPuzzleControllerImpl controller; // Riferimento al controller

    public EnigmaPuzzleViewImpl(int rows, int cols, EnigmaPuzzleControllerImpl controller) {
        this.controller = controller; // Inizializza il controller
        setLayout(new GridLayout(rows, cols));
        buttons = new ArrayList<>();
        for (int i = 0; i < rows * cols; i++) {
            ImageButton button = new ImageButton();
            buttons.add(button);
            button.addActionListener(e -> controller.onButtonClicked(button)); // Passa l'evento al controller
            add(button);
        }
    }

    
    public List<ImageButton> getButtons() {
        return buttons;
    }

    public void updateView(List<Image> newPieces) {
        for (int i = 0; i < newPieces.size(); i++) {
            buttons.get(i).setImage(newPieces.get(i));
        }
    }

    @Override
    public JPanel getPanel() {
        return this;  // Restituiamo il JPanel che contiene la vista
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}
