package mindescape.model.world.api;

import java.util.List;
import java.util.Optional;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

/**
 * The World interface represents a game world consisting of multiple rooms.
 * It provides methods to interact with the rooms and manage the player's movements and interactions.
 */
public interface World {

    /**
     * Retrieves a list of all rooms in the world.
     *
     * @return a list of Room objects representing all the rooms in the world.
     */
    List<Room> getRooms();

    /**
     * Moves the player in the specified direction.
     *
     * @param movement the direction in which the player should move
     */
    void movePlayer(Movement movement);

    /**
     * Allows the player to interact with the world and potentially solve an enigma.
     *
     * @return an {@link Optional} containing the {@link Enigma} if the player interacts with one,
     */
    Optional<Enigma> letPlayerInteract();

    /**
     * Checks if the player has won the game.
     *
     * @return {@code true} if the player has won, {@code false} otherwise.
     */
    boolean hasWon();

    /**
     * Retrieves the current room in the world.
     *
     * @return the current {@link Room} instance.
     */
    Room getCurrentRoom();

    /**
     * Adds a room to the world.
     *
     * @param room the room to be added
     */
    void addRoom(Room room);

    /**
     * Retrieves the player in the world.
     *
     * @return the {@link Player} instance representing the player in the world.
     */
    Player getPlayer();

}
