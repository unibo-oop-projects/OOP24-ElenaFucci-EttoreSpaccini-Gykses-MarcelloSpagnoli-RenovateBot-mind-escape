package mindescape.model.world.items.interactable.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

/**
 * Represents a concrete implementation of the {@link Door} interface.
 * <p>
 * Defines a door that connects two rooms within the game world. Extends 
 * {@link GameObjectImpl}, inheriting properties like position, name, 
 * and dimensions.
 * </p>
 * <p>
 * The door enables transitioning between an origin room and a destination room. 
 * Unlocking mechanisms can be extended in subclasses or modified at runtime 
 * using decorators.
 * </p>
 *
 * @see Door
 * @see GameObjectImpl
 */
public final class DoorImpl extends GameObjectImpl implements Door {

    private static final long serialVersionUID = 1L;

    private final Room destinationRoom;
    private final Point2D destinationPosition;

    /**
     * Constructs a door with a given position, name, dimensions, and destination room.
     *
     * @param position        the position of the door in the game world
     * @param name            the name of the door
     * @param dimensions      the dimensions of the door
     * @param destinationRoom the room connected as the destination through the door
     * @param destinationPosition the position in the destination room where the player will be placed
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The destination room has to be exposed for the game logic")
    public DoorImpl(final Point2D position, final String name,
                    final Dimensions dimensions, final Room destinationRoom, final Point2D destinationPosition) {
        super(position, name, dimensions);
        this.destinationRoom = destinationRoom;
        this.destinationPosition = destinationPosition;
    }

    /**
     * Defines the interaction behavior when the player interacts with the door.
     * <p>
     * Switches the player's current room to the destination room connected 
     * by the door.
     * </p>
     *
     * @param player the player interacting with the door
     */
    @Override
    public void onAction(final Player player) {
        player.getCurrentRoom().removeGameObject(player);
        player.setCurrentRoom(this.destinationRoom);
        player.getCurrentRoom().addGameObject(player);
        player.setPosition(destinationPosition);
    }

    /**
     * Checks if the door is unlocked.
     *
     * @return true if the door is unlocked, false otherwise.
     */
    @Override
    public boolean isUnlocked() {
        return true; 
    }
}
