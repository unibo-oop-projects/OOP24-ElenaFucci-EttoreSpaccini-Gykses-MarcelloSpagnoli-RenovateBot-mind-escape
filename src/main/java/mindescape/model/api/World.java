package mindescape.model.api;

import java.util.List; 

public interface World {

    List<Room> getRooms();

    //void movePlayer(Direction direction);

    boolean letPlayerInteract();

    boolean hasWon();

    Room getCurrentRoom();

    //TODO: void addRoom(Room room);
}
