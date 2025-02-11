//"C:\\Users\\Elena\\Desktop\\MVC\\puzzlegame\\mattarella.jpg"
package mindescape.controller.enigmapuzzle.impl;
import mindescape.view.enigmapuzzle.impl.ImageButton;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mindescape.model.enigma.enigmapuzzle.impl.EnigmaPuzzleModelImpl;
import mindescape.view.enigmapuzzle.impl.EnigmaPuzzleViewImpl;

public class EnigmaPuzzleControllerImpl implements ActionListener {

    private final EnigmaPuzzleModelImpl model;
    private final EnigmaPuzzleViewImpl view;
    private ImageButton firstButton = null;

    public EnigmaPuzzleControllerImpl(EnigmaPuzzleModelImpl model, EnigmaPuzzleViewImpl view) {
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
            
            // Dopo aver aggiornato il modello, il controller aggiorna la vista
            updateView();

            firstButton = null;
        }
    }

    // Metodo per aggiornare la vista tramite il Controller
    private void updateView() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                ImageButton button = view.getButtons().get(i * model.getCols() + j);
                button.setImage(model.getPiece(i, j));  // Aggiorna l'immagine del pulsante
            }
        }
        
        // Forza il ridisegno della view
        view.revalidate();
        view.repaint();
    }
    

    public String getName() {
        return this.model.getName(); 
    }

    public JPanel getPanel() {
        return this.view.getPanel(); 
    }

    // Metodo principale per testare il controller
    public static void main(String[] args) {
        Image image = new ImageIcon("C:\\\\Users\\\\Elena\\\\Desktop\\\\MVC\\\\puzzlegame\\\\mattarella.jpg").getImage();  // Carica l'immagine
        EnigmaPuzzleModelImpl model = new EnigmaPuzzleModelImpl(3, 3, image, "Puzzle Game");
        EnigmaPuzzleViewImpl view = new EnigmaPuzzleViewImpl(3, 3);  // Non passiamo il model alla view
        EnigmaPuzzleControllerImpl controller = new EnigmaPuzzleControllerImpl(model, view);

        // Associa il controller alla view
        view.setController(controller);

        controller.updateView(); // Inizializza la vista

        // Testa l'inizializzazione della vista e del controller
        JFrame frame = new JFrame("Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getMaximumSize();  // Imposta una dimensione iniziale per il JFrame
        frame.setVisible(true);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
