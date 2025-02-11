package mindescape.model.world.items.interactable.api;

import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;
import java.util.Optional;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;

/**
 * The {@code InteractableFactory} interface defines methods for creating interactable objects, including pickable 
 * items, doors, and objects requiring enigmas or specific items to interact with.
 *
 * <p>Implementations should handle the instantiation and integration of these objects in the game environment.</p>
 */
public interface InteractableFactory {

    /**
     * Creates a pickable object with the given parameters.
     *
     * @param name        the name of the pickable object
     * @param position    the position of the pickable object in the game world
     * @param dimensions  the dimensions of the pickable object
     * @param description a description of the pickable object
     * @param id          the unique identifier of the pickable object
     * @return a new instance of {@link Pickable}
     */
    Pickable createPickable(String name, Optional<Point2D> position, Dimensions dimensions, 
                            String description, int id);

    /**
     * Creates a simple door that is always open and does not require unlocking.
     *
     * @param name        the name of the door
     * @param position    the position of the door in the game world
     * @param dimensions  the dimensions of the door
     * @param destinationRoom the room connected to the door as the destination
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new instance of {@link Door}
     */
    Door createSimpleDoor(String name, Optional<Point2D> position, Dimensions dimensions, 
                          Room destinationRoom, Optional<Point2D> destinationPosition); 

    /**
     * Creates a door that can be unlocked using a specific pickable item.
     *
     * @param name           the name of the door
     * @param position       the position of the door in the game world
     * @param dimensions     the dimensions of the door
     * @param keyItem_id     the ID of the pickable item required to unlock the door
     * @param destinationRoom the room connected to the door as the destination
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new instance of {@link Door}
     */
    Door createDoorLockedWithPickable(String name, Optional<Point2D> position, Dimensions dimensions,
                                      int keyItem_id, Room destinationRoom, Optional<Point2D> destinationPosition);

    /**
     * Creates a door that requires solving an enigma to unlock.
     *
     * @param name           the name of the door
     * @param position       the position of the door in the game world
     * @param dimensions     the dimensions of the door
     * @param enigma         the enigma required to unlock the door
     * @param destinationRoom the room connected to the door as the destination
     * @param destinationPosition the position in the destination room where the player will be placed
     * @return a new instance of {@link Door} with an enigma requirement
     */
    Door createDoorLockedWithEnigma(String name, Optional<Point2D> position, Dimensions dimensions,
                                    Enigma enigma, Room destinationRoom, Optional<Point2D> destinationPosition);

    /**
     * Creates an unpickable object requiring an enigma to unlock, optionally rewarding a pickable item upon solving.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the unpickable object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param enigma     the enigma required to unlock the object
     * @param reward     an {@link Optional} pickable item rewarded upon solving, or empty if none
     * @return a new instance of {@link Unpickable} with an enigma requirement
     */
    Unpickable createUnpickableWithEnigma(String name, Optional<Point2D> position,
                                          Dimensions dimensions, Enigma enigma, Optional<Pickable> reward);

    /**
     * Creates an unpickable object requiring a specific pickable item to unlock, optionally rewarding another item.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the unpickable object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param keyItem_id the ID of the pickable item required to unlock the object
     * @param reward     the {@link Pickable} item rewarded after unlocking
     * @return a new instance of {@link Unpickable} requiring a pickable item to unlock
     */
    Unpickable createLockedUnpickable(String name, Optional<Point2D> position,
                                      Dimensions dimensions, int keyItem_id, Pickable reward);

    /**
     * Creates an unpickable object that does not require unlocking but can optionally provide a pickable item.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the unpickable object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param reward     an {@link Optional} pickable item as a reward, or empty if none
     * @return a new instance of {@link Unpickable} with an optional reward
     */
    Unpickable createUnpickable(String name, Optional<Point2D> position,
                                Dimensions dimensions, Optional<Pickable> reward);
}
