package mindescape.model.world.items.interactable.impl;

import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.InteractableFactory;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.rooms.api.Room;

/**
 * Implementation of {@link InteractableFactory} for creating interactable objects such as pickable items, doors, 
 * and unpickable objects with specific behaviors.
 */
public final class InteractableFactoryImpl implements InteractableFactory {

    /**
     * Creates a pickable object with the given parameters.
     *
     * @param name        the name of the pickable object
     * @param position    the position in the game world
     * @param dimensions  the dimensions of the pickable object
     * @param description the description of the pickable object
     * @param id          the unique identifier of the pickable object
     * @return a new {@link Pickable} instance
     */
    @Override
    public Pickable createPickable(final String name, final Point2D position,
                                   final Dimensions dimensions, final String description, final int id) {
        return new PickableImpl(position, name, dimensions, description, id);
    }

    /**
     * Creates a door unlocked using a specific pickable item.
     *
     * @param name            the name of the door
     * @param position        the position in the game world
     * @param dimensions      the dimensions of the door
     * @param keyItemId     the ID of the item required to unlock the door
     * @param destinationRoom the destination room connected to the door
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new {@link Door} instance
     */
    @Override
    public Door createDoorLockedWithPickable(
        final String name, 
        final Point2D position,
        final Dimensions dimensions, 
        final int keyItemId,
        final Room destinationRoom, 
        final Point2D destinationPosition) {
            final var door = new DoorImpl(
                position, 
                name, 
                dimensions, 
                destinationRoom, 
                destinationPosition 
            );
            return new DoorLockedWithPickable(door, keyItemId);
    }

    /**
     * Creates a door that requires solving an enigma to unlock.
     *
     * @param name            the name of the door
     * @param position        the position in the game world
     * @param dimensions      the dimensions of the door
     * @param enigma          the enigma required to unlock the door
     * @param destinationRoom the destination room connected to the door
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new {@link Door} instance
     */
    @Override
    public Door createDoorLockedWithEnigma(final String name, final Point2D position,
                                           final Dimensions dimensions, final Enigma enigma,
                                           final Room destinationRoom, final Point2D destinationPosition) {
        return new DoorLockedWithEnigma(new DoorImpl(position, name, dimensions, destinationRoom, destinationPosition), enigma);
    }

    /**
     * Creates an unpickable object requiring an enigma to unlock, optionally providing a reward upon solving it.
     *
     * @param name        the name of the unpickable object
     * @param position    the position in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param enigma      the enigma required to unlock the object
     * @param reward      a pickable item rewarded after solving the enigma
     * @return a new {@link Unpickable} instance
     */
    @Override
    public Unpickable createUnpickableWithEnigma(final String name, final Point2D position,
                                                 final Dimensions dimensions, final Enigma enigma,
                                                 final Pickable reward) {
        return new UnpickableWithEnigmaImpl(name, position, dimensions, enigma, reward);
    }

    /**
     * Creates an unpickable object requiring a pickable item to unlock, optionally rewarding another item.
     *
     * @param name        the name of the unpickable object
     * @param position    the position in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param keyItemId  the ID of the item required to unlock the object
     * @param reward      the pickable item rewarded after unlocking
     * @return a new {@link Unpickable} instance
     */
    @Override
    public Unpickable createLockedUnpickable(final String name, final Point2D position,
                                             final Dimensions dimensions, final int keyItemId,
                                             final Pickable reward) {
        return new LockedUnpickable(name, position, dimensions, keyItemId, reward);
    }

    /**
     * Creates an unpickable object that does not require unlocking, optionally providing a reward.
     *
     * @param name        the name of the unpickable object
     * @param position    the position in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param reward      a  pickable item rewarded upon interaction
     * @return a new {@link Unpickable} instance
     */
    @Override
    public Unpickable createUnpickable(final String name, final Point2D position,
                                       final Dimensions dimensions, final Pickable reward) {
        return new UnpickableImpl(name, position, dimensions, reward);
    }

    /**
     * Creates a simple door that is always open and does not require unlocking.
     *
     * @param name            the name of the door
     * @param position        the position in the game world
     * @param dimensions      the dimensions of the door
     * @param destinationRoom the destination room connected to the door
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new {@link Door} instance
     */
    @Override
    public Door createSimpleDoor(final String name, final Point2D position,
                                 final Dimensions dimensions, final Room destinationRoom, 
                                 final Point2D destinationPosition) {
        return new DoorImpl(position, name, dimensions, destinationRoom, destinationPosition);
    }
}
