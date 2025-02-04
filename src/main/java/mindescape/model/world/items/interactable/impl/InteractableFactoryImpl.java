package mindescape.model.world.items.interactable.impl;

import java.util.Optional;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Door;
import mindescape.model.world.items.interactable.api.InteractableFactory;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.api.Unpickable;
import mindescape.model.world.rooms.api.Room;

/**
 * Implementation of the {@link InteractableFactory} interface.
 * <p>
 * Provides methods to create interactable objects in the game world, such as 
 * pickable items, doors (locked with enigmas or items), and unpickable objects 
 * with specific behaviors.
 * </p>
 *
 * @see InteractableFactory
 */
public class InteractableFactoryImpl implements InteractableFactory {

    /**
     * Creates a pickable object with the given parameters.
     *
     * @param name        the name of the pickable object
     * @param position    the optional position of the object in the game world
     * @param dimensions  the dimensions of the pickable object
     * @param description the description of the pickable object
     * @param id          the unique identifier of the pickable object
     * @return a new instance of {@link Pickable}
     */
    @Override
    public Pickable createPickable(final String name, final Optional<Point2D> position,
                                   final Dimensions dimensions, final String description, final Integer id) {
        return new PickableImpl(position, name, dimensions, description, id);
    }

    /**
     * Creates a door that can be unlocked using a specific pickable item.
     *
     * @param name            the name of the door
     * @param position        the optional position of the door in the game world
     * @param dimensions      the dimensions of the door
     * @param key_item_id     the ID of the item required to unlock the door
     * @param destinationRoom the room connected to the door as the destination
     * @return a new instance of {@link Door}
     */
    @Override
    public Door createDoorLockedWithPickable(final String name, final Optional<Point2D> position,
                                             final Dimensions dimensions, final Integer key_item_id,
                                             final Room destinationRoom) {
        return new DoorLockedWithPickable(
            new DoorImpl(position, name, dimensions, destinationRoom),
            key_item_id
        );
    }

    /**
     * Creates a door that requires solving an enigma to unlock.
     *
     * @param name            the name of the door
     * @param position        the optional position of the door in the game world
     * @param dimensions      the dimensions of the door
     * @param enigma          the enigma required to unlock the door
     * @param destinationRoom the room connected to the door as the destination
     * @return a new instance of {@link Door}
     */
    @Override
    public Door createDoorLockedWithEnigma(final String name, final Optional<Point2D> position,
                                           final Dimensions dimensions, final Enigma enigma,
                                           final Room destinationRoom) {
        return new DoorLockedWithEnigma(
            new DoorImpl(position, name, dimensions, destinationRoom),
            enigma
        );
    }

    /**
     * Creates an unpickable object that requires solving an enigma to unlock, 
     * optionally providing a reward upon solving it.
     *
     * @param name        the name of the unpickable object
     * @param position    the optional position of the object in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param enigma      the enigma required to unlock the object
     * @param reward      an optional pickable item rewarded after solving the enigma
     * @return a new instance of {@link Unpickable}
     */
    @Override
    public Unpickable createUnpickableWithEnigma(final String name, final Optional<Point2D> position,
                                                 final Dimensions dimensions, final Enigma enigma,
                                                 final Optional<Pickable> reward) {
        return new UnpickableWithEnigma(name, position, dimensions, enigma, reward);
    }

    /**
     * Creates an unpickable object that can be unlocked using a specific pickable item, 
     * rewarding the player with another item after unlocking.
     *
     * @param name        the name of the unpickable object
     * @param position    the optional position of the object in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param keyItem_id  the ID of the item required to unlock the object
     * @param reward      the pickable item rewarded after unlocking
     * @return a new instance of {@link Unpickable}
     */
    @Override
    public Unpickable createLockedUnpickable(final String name, final Optional<Point2D> position,
                                             final Dimensions dimensions, final Integer keyItem_id,
                                             final Pickable reward) {
        return new LockedUnpickable(name, position, dimensions, keyItem_id, reward);
    }

    /**
     * Creates an unpickable object that does not require unlocking, optionally 
     * providing a reward when interacted with.
     *
     * @param name        the name of the unpickable object
     * @param position    the optional position of the object in the game world
     * @param dimensions  the dimensions of the unpickable object
     * @param reward      an optional pickable item rewarded upon interaction
     * @return a new instance of {@link Unpickable}
     */
    @Override
    public Unpickable createUnpickable(final String name, final Optional<Point2D> position,
                                       final Dimensions dimensions, final Optional<Pickable> reward) {
        return new UnpickableImpl(name, position, dimensions, reward);
    }
}
