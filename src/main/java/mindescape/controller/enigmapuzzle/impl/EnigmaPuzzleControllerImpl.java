package mindescape.controller.enigmapuzzle.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mindescape.controller.enigmapuzzle.api.EnigmaPuzzleController;
import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;
import mindescape.view.enigmapuzzle.impl.ImageButton;
import mindescape.controller.maincontroller.api.MainController;

public class EnigmaPuzzleControllerImpl implements EnigmaPuzzleController, ActionListener {
    private final EnigmaPuzzleModelImpl model;
    private final EnigmaPuzzleViewImpl view;
    private ImageButton firstButton = null;
    private final MainController mainController;

    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, EnigmaPuzzleViewImpl view, final MainController mainController) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageButton clickedButton = (ImageButton) e.getSource();
        
        // Trova la posizione del pulsante cliccato
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
            
            view.updateView(model);

            firstButton = null;
        }
    }

    @Override
    public String getName() {
        return this.model.getName(); 
    }

    @Override
    public JPanel getPanel() {
        return this.view.getPanel(); 
    }

    @Override
    public void quit() {
        this.mainController.setController(this.mainController.findController("World"));
    }

    public static void main(String[] args) {
        try {
            // Carica l'immagine
            BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Elena\\Desktop\\MVC\\puzzlegame\\mattarella.jpg"));

            // Crea il modello e la vista
            EnigmaPuzzleModelImpl model = new EnigmaPuzzleModelImpl(3, 3, originalImage, "Puzzle");
            EnigmaPuzzleViewImpl view = new EnigmaPuzzleViewImpl(model);
            EnigmaPuzzleControllerImpl controller = new EnigmaPuzzleControllerImpl(model, view, null);

            // Crea la finestra
            JFrame frame = new JFrame("Puzzle Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setLayout(new BorderLayout());
            frame.add(view, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Collega i pulsanti al controller
            for (ImageButton button : view.getButtons()) {
                if (button != null) {
                    button.addActionListener(controller); // Ora EnigmaPuzzleControllerImpl è un ActionListener
                }
            }

            // Inizializza la vista
            view.updateView(model);

            // Test: Verifica il nome del puzzle
            System.out.println("Nome del puzzle: " + model.getName()); // Dovrebbe stampare "Puzzle"

            // Test: Verifica se il puzzle è risolto (all'inizio non dovrebbe esserlo)
            System.out.println("Il puzzle è risolto? " + model.isSolved()); // Dovrebbe stampare false

            // Test: Sposta i pezzi per testare il metodo isSolved
            // Simula il movimento dei pezzi tramite il controller
            model.swapPieces(0, 0, 1, 1);  // Scambia due pezzi
            view.updateView(model);
            
            // Verifica di nuovo se il puzzle è risolto dopo il movimento (non lo sarà)
            System.out.println("Il puzzle è risolto dopo lo scambio? " + model.isSolved()); // Dovrebbe stampare false

            // Test: Verifica il panel
            JPanel panel = controller.getPanel();
            System.out.println("Panel ottenuto: " + panel); // Dovrebbe stampare il pannello del puzzle

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }
}
