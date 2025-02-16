package mindescape.view.api;

import java.awt.image.BufferedImage;

import mindescape.model.world.rooms.api.Room;

public interface RoomRenderer {
    /**
     * Getter for the room image.
     * @return the room image.
     */
    public BufferedImage getRoomImage();

    /**
     * Updates the room image.
     * @param currentRoom the room to be rendered.
     */
    public void updateRoomImage(final Room currentRoom);
}

