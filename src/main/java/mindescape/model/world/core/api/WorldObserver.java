package mindescape.model.world.core.api;

import mindescape.model.world.rooms.api.Room;

public interface WorldObserver {
    /**
     * Called when the room has changed (pickable is picke or the player has changes room)
     * @param newRoom
     */
    void onRoomChanged(Room newRoom);
}
