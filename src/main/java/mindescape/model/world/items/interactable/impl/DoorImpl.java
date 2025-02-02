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
 * The unlocking mechanism can be extended in subclasses or modified at runtime using decorators.</p>
 *
 * @see Door
 * @see GameObjectImpl
 */
public class DoorImpl extends GameObjectImpl implements Door {

    private Room originRoom;
    private Room destinationRoom;
    private boolean unlocked; 

    /**
     * Constructs a door with a given position, name, dimensions, and associated rooms.
     *
     * @param position        the optional position of the door in the game world
     * @param name            the name of the door
     * @param dimensions      the dimensions of the door
     * @param originRoom      the room where the door starts
     * @param destinationRoom the room where the door leads
     */
    public DoorImpl(final Optional<Point2D> position, final String name, 
                    final Dimensions dimensions, final Room originRoom, final Room destinationRoom) {
        super(position, name, dimensions);
        this.originRoom = originRoom;
        this.destinationRoom = destinationRoom;
        this.unlocked = false; 
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
     * Defines the interaction behavior when the player interacts with the door.
     */
    @Override
    public void onAction() {
        this.unlocked = true; 
    }

    /**
     * Checks if the door is currently unlocked.
     *
     * @return {@code true} if the door is unlocked, {@code false} otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return this.unlocked; 
    }
}
