package mindescape.model.world.api;

import java.util.List;
import java.util.Optional;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.rooms.api.Room;

public interface World {

    List<Room> getRooms();

    void movePlayer(Movement movement);

    Optional<Enigma>  letPlayerInteract();

    boolean hasWon();

    Room getCurrentRoom();

    void addRoom(Room room);
}
