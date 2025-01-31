package mindescape.model.world.items.interactable.api;

import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.core.api.Dimensions;

public interface InteractableFactory {

  /**
     * Creates a pickable object with the given parameters.
     *
     * @param name       the name of the pickable object
     * @param position   the position of the pickable object in the game world
     * @param dimensions the dimensions of the pickable object
     * @return a new instance of Pickable
     */
    Pickable createPickable(final String name, final Point2D position, final Dimensions dimensions);

    /**
     * Creates a door that can be unlocked using a specific pickable item.
     *
     * @param name            the name of the door
     * @param position        the position of the door in the game world
     * @param dimensions      the dimensions of the door
     * @param pickable             the pickable item required to unlock the door
     * @param originRoom      the starting room where the door is located
     * @param destinationRoom the destination room that the door leads to
     * @return a new instance of Door
     */
    Door createDoor(final String name, final Point2D position, final Dimensions dimensions, final Pickable pickable, final Room originRoom, final Room destinationRoom);

    /**
     * Creates a decorated door that requires solving an enigma to unlock.
     *
     * @param name            the name of the door
     * @param position        the position of the door in the game world
     * @param dimensions      the dimensions of the door
     * @param enigma          the enigma required to unlock the door
     * @param originRoom      the starting room where the door is located
     * @param destinationRoom the destination room that the door leads to
     * @return a new instance of a decorated Door with an enigma requirement
     */
    Door createDoorWithEnigma(final String name, final Point2D position, final Dimensions dimensions, final Enigma enigma, final Room originRoom, final Room destinationRoom);

    /**
     * Creates an unpickable object that requires solving an enigma to be unlocked.
     *
     * @param name       the name of the unpickable object
     * @param position   the position of the unpickable object in the game world
     * @param dimensions the dimensions of the unpickable object
     * @param enigma     the enigma required to unlock the object
     * @return a new instance of an Unpickable with an enigma requirement
     */
    Unpickable createUnpickableWithEnigma(final String name, final Point2D position, final Dimensions dimensions, final Enigma enigma);
    
} 
