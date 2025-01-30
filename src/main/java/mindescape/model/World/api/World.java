package mindescape.model.World.api;

import java.util.List; 
import mindescape.model.World.Rooms.api.Room;

public interface World {

    List<Room> getRooms();

    //void movePlayer(Direction direction);

    boolean letPlayerInteract();

    boolean hasWon();

    Room getCurrentRoom();

    //TODO: void addRoom(Room room);
}
