package mindescape.view.enigmapuzzle.impl;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.api.EnigmaPuzzleView;

public class EnigmaPuzzleViewImpl extends JPanel implements EnigmaPuzzleView{

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    private List<ImageButton> buttons = new ArrayList<>();

    public EnigmaPuzzleViewImpl(EnigmaPuzzleModelImpl model) {
        setLayout(new GridLayout(model.getRows(), model.getCols()));
        for (int i = 0; i < model.getRows() * model.getCols(); i++) {
            ImageButton button = new ImageButton();
            buttons.add(button);
            add(button);
        }
    }

    public List<ImageButton> getButtons() {
        return buttons;
    }

    public void updateView(EnigmaPuzzleModelImpl model) {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                ImageButton button = buttons.get(i * model.getCols() + j);
                button.setImage(model.getPiece(i, j));
            }
        }
    }

    public JPanel getPanel() {
        return this;
    }

}
