package mindescape.model.world.items.interactable.api;

import mindescape.model.world.items.api.Unmovable;
import mindescape.model.world.player.api.Player;

/**
 * Represents an object in the game world that can be interacted with.
 * <p>
 * This interface extends the {@link Unmovable} interface, indicating that
 * the object cannot be moved but can be interacted with through specific actions.
 * </p>
 * <p>
 * Classes implementing this interface must provide an implementation
 * for the {@link #onAction(Player)} method, which defines the behavior when an
 * action is performed on the object by a player.
 * </p>
 *
 * @see Unmovable
 */
public interface Interactable extends Unmovable {

    /**
     * Performs an action on the interactable object.
     * <p>
     * The specific behavior of this action depends on the implementation.
     * It may involve unlocking mechanisms, triggering events, changing
     * the state of the object, or interacting with the player's inventory.
     * </p>
     *
     * @param player the {@link Player} interacting with the object. The player's
     *               attributes, such as inventory or status, may influence the outcome
     *               of the interaction.
     */
    void onAction(Player player);
}