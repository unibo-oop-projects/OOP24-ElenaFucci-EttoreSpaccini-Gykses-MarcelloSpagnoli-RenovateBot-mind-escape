package mindescape.view.api;

import java.awt.image.BufferedImage;

import mindescape.model.world.rooms.api.Room;

/**
 * Interface that models a RoomRenderer.
 */
public interface RoomRenderer {
    /**
     * Getter for the room image.
     * @return the room image.
     */
    BufferedImage getRoomImage();

    /**
     * Updates the room image.
     * @param currentRoom the room to be rendered.
     */
    void updateRoomImage(Room currentRoom);
}

