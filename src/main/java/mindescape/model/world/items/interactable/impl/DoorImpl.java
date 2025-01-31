package mindescape.model.world.items.interactable.impl;

import java.util.Optional;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.rooms.api.Room;

/**
 * Represents a concrete implementation of the {@link Door} interface.
 * This class defines a door that connects two rooms within the game world.
 * It extends {@link GameObjectImpl}, inheriting common properties such as position, name, and dimensions.
 *
 * <p>The door allows transitioning between an origin room and a destination room.
 * The unlocking mechanism should be extended in subclasses or modified at runtime using decorators.</p>
 *
 * @see Door
 * @see GameObjectImpl
 */
public class DoorImpl extends GameObjectImpl implements Door {

    private Room originRoom;
    private Room destinationRoom;

    /**
     * Constructs a door with a given position, name, dimensions, and associated rooms.
     *
     * @param position       the optional position of the door in the game world
     * @param name           the name of the door
     * @param dimensions     the dimensions of the door
     * @param originRoom     the room where the door starts
     * @param destinationRoom the room where the door leads
     */
    public DoorImpl(final Optional<Point2D> position, final String name, final Dimensions dimensions, final Room originRoom, final Room destinationRoom) {
        super(position, name, dimensions);
        this.originRoom = originRoom;
        this.destinationRoom = destinationRoom;
    }

    /**
     * Switches the current room references, effectively allowing the player to transition
     * from the origin room to the destination room and vice versa.
     */
    @Override
    public void switchRooms() {
        final var temp = originRoom;
        originRoom = destinationRoom;
        destinationRoom = temp;
    }

    /**
     * Retrieves the room that the door leads to.
     *
     * @return the destination room
     */
    @Override
    public Room getDestinationRoom() {
        return this.destinationRoom;
    }

    /**
     * Attempts to unlock the door.
     * This default implementation always returns false.
     *
     * @return {@code false}, as this door implementation does not support unlocking.
     */
    @Override
    public boolean unlock() {
        return false;
    }

    /**
     * Defines the interaction behavior when the player interacts with the door.
     * This method should be overridden to provide specific interaction logic.
     */
    @Override
    public void onAction() {
        // Default implementation does nothing
    }
}