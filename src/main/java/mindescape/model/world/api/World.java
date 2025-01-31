package mindescape.model.world.api;

import java.util.List;
import mindescape.model.world.rooms.api.Room;

public interface World {

    List<Room> getRooms();

    void movePlayer(Direction direction);

    boolean letPlayerInteract();

    boolean hasWon();

    Room getCurrentRoom();

    void addRoom(Room room);
}
