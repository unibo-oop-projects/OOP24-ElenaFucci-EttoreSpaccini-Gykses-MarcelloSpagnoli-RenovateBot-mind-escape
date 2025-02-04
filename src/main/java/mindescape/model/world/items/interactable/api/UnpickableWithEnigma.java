package mindescape.model.world.items.interactable.api;

import mindescape.model.enigma.api.Enigma;

/**
 * Represents an unpickable object associated with an {@link Enigma}.
 * <p>
 * Classes implementing this interface provide functionality for objects 
 * that cannot be picked up and are linked to a puzzle or enigma.
 * </p>
 * <p>
 * This interface extends {@link Unpickable}, inheriting its properties 
 * and behaviors related to unpickable objects.
 * </p>
 *
 * @see Unpickable
 * @see Enigma
 */
public interface UnpickableWithEnigma extends Unpickable {

    /**
     * Retrieves the enigma associated with this unpickable object.
     *
     * @return the {@link Enigma} linked to this object
     */
    Enigma getEnigma();
}
