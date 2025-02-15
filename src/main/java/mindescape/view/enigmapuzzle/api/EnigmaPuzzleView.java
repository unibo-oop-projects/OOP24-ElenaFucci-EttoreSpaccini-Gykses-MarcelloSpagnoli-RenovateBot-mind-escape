package mindescape.view.enigmapuzzle.api;

import java.util.List;

import mindescape.view.api.View;
import mindescape.view.utils.ImageButton;

/**
 * Interface for the view of the enigma puzzle.
 */
public interface EnigmaPuzzleView extends View {
    /**
     * Retrieves a list of ImageButton objects.
     * 
     * @return a List of ImageButton instances.
     */
    List<ImageButton> getButtons();
    /**
     * Updates the view with the given puzzle pieces.
     *
     * @param pieces a 2D array of Integer representing the puzzle pieces to be updated in the view
     */
    void update(Integer[][] pieces);
}
