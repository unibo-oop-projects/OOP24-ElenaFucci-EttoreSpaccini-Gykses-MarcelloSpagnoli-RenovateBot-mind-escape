//"C:\\Users\\Elena\\Desktop\\MVC\\puzzlegame\\mattarella.jpg"
package mindescape.controller.enigmapuzzle.impl;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import mindescape.controller.core.api.ControllerName;
import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.view.enigmapuzzle.impl.ImageButton;
import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.controller.maincontroller.api.MainController;

public class EnigmaPuzzleControllerImpl implements EnigmaPuzzleController {

    private final EnigmaPuzzleModelImpl model;
    private final EnigmaPuzzleViewImpl view;
    private final MainController mainController;

    private ImageButton firstButton = null;

    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, MainController mainController) {
        this.model = model;
        this.view = new EnigmaPuzzleViewImpl(model.getRows(), model.getCols(), this);
        this.mainController = mainController;
    }

    public void onButtonClicked(ImageButton clickedButton) {
        // Gestisce il click del pulsante
        int clickedIndex = view.getButtons().indexOf(clickedButton);
        int row = clickedIndex / model.getCols();
        int col = clickedIndex % model.getCols();

        if (firstButton == null) {
            firstButton = clickedButton;
        } else {
            int firstIndex = view.getButtons().indexOf(firstButton);
            int firstRow = firstIndex / model.getCols();
            int firstCol = firstIndex % model.getCols();

            model.swapPieces(firstRow, firstCol, row, col);
            view.updateView(model.getShuffledPieces());

            firstButton = null;
        }
    }

    @Override
    public String getName() {
        return model.getName();
    }

    @Override
    public JPanel getPanel() {
        return view.getPanel();
    }

    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD);
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public void start() {
        // Avvia la vista con le tessere
        view.updateView(model.getShuffledPieces());
    }

    public static void main(String[] args) {
        // Carichiamo l'immagine da file
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\\\Users\\\\Elena\\\\Desktop\\\\MVC\\\\puzzlegame\\\\mattarella.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return; // Se l'immagine non può essere caricata, terminare l'esecuzione
        }

        // Impostiamo le dimensioni del puzzle (ad esempio, 4x4)
        int rows = 4;
        int cols = 4;

        // Creiamo il modello, la vista e il controller
        EnigmaPuzzleModelImpl model = new EnigmaPuzzleModelImpl(rows, cols, img, "Puzzle Game");
        EnigmaPuzzleViewImpl view = new EnigmaPuzzleViewImpl(rows, cols, new EnigmaPuzzleControllerImpl(model, null)); // null come placeholder per MainController
        EnigmaPuzzleControllerImpl controller = new EnigmaPuzzleControllerImpl(model, null);

        // Avviamo il frame dell'applicazione
        JFrame frame = new JFrame("Enigma Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(view.getPanel());
        frame.setVisible(true);

        // Avviamo il controller
        controller.start();
    }

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }

    @Override
    public Model getModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModel'");
    }
}
