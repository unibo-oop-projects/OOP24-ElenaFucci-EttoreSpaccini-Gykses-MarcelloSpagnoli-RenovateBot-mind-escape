package mindescape.model.world.core.api;

import mindescape.model.world.rooms.api.Room;

/**
 * The {@code WorldObserver} interface provides the methods to observe the world.
 */
public interface WorldObserver {
    /**
     * Called when the room has changed (pickable is pickde or the player has changes room).
     * @param newRoom the new room.
     */
    void onRoomChanged(Room newRoom);
}
