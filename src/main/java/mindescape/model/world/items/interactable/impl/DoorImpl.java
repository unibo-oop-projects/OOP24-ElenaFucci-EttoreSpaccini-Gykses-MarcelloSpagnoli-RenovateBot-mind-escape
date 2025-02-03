package mindescape.model.world.items.interactable.impl;

import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Pair;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

/**
 * Represents a concrete implementation of the {@link Door} interface.
 * <p>
 * This class defines a door that connects two rooms within the game world.
 * It extends {@link GameObjectImpl}, inheriting common properties such as position,
 * name, and dimensions.
 * </p>
 * <p>
 * The door allows transitioning between an origin room and a destination room.
 * The unlocking mechanism can be extended in subclasses or modified at runtime
 * using decorators.
 * </p>
 *
 * @see Door
 * @see GameObjectImpl
 */
public class DoorImpl extends GameObjectImpl implements Door {

    private final Pair<Room, Room> rooms;

    /**
     * Constructs a door with a given position, name, dimensions, and associated rooms.
     *
     * @param position        the optional position of the door in the game world
     * @param name            the name of the door
     * @param dimensions      the dimensions of the door
     * @param rooms           a pair of rooms representing the origin and destination rooms
     *                         connected by the door
     */
    public DoorImpl(final Optional<Point2D> position, final String name,
                    final Dimensions dimensions, final Pair<Room, Room> rooms) {
        super(position, name, dimensions);
        this.rooms = rooms;
    }

    /**
     * Defines the interaction behavior when the player interacts with the door.
     * <p>
     * This method switches the player's current room to the opposite room
     * connected by the door. If the player is currently in the origin room,
     * they will be moved to the destination room, and vice versa.
     * </p>
     *
     * @param player the player interacting with the door
     */
    @Override
    public void onAction(final Player player) {
        player.setCurrentRoom(
            player.getCurrentRoom().equals(this.rooms.getX())
                ? this.rooms.getY()
                : this.rooms.getX()
        );
    }
}
