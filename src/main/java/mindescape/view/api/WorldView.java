package mindescape.view.api;
import java.util.Map;

import javax.swing.JPanel;

import mindescape.model.world.rooms.api.Room;


/**
 * The {@code View} interface provides the methods to update the view in the application.
 * Implementations of this interface should define the behavior for starting and updating the view.
 */
public interface WorldView {

    /**
     * Updates the view. 
     * @param room 
     */
    void draw(Room currentRoom);

    /**
     * Returns the panel of the view.
     * @return {@code JPanel} of the view.
     */
    JPanel getPanel();

    Map<Integer, Boolean> getKeyState();

    void clearInput();

}
