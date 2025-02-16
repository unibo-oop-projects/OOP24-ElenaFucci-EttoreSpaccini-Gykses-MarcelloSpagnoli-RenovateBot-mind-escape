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
     * @param currentRoom to be displayed
     */
    void draw(Room currentRoom);

    /**
     * Returns the panel of the view.
     * @return {@code JPanel} of the view.
     */
    JPanel getPanel();

    /**
     * Return a map of which keys are currently being pressed.
     * @return a Map<Integer, Boolean> containing if a key is pressed or not.
     */
    Map<Integer, Boolean> getKeyState();

    /**
     * Clears the keyboard input map.
     */
    void clearInput();

    /**
     * Updates the room image.
     * @param currentRoom the room to be rendered.
     */
    void updateRoomImage(Room currentRoom);

}
