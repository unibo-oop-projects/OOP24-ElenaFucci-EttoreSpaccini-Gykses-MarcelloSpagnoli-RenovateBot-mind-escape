package mindescape.model.world.items.api;

import mindescape.model.world.player.api.Player;
/**
 * Represents an object that can be interacted with in the game.
 * This interface extends the Unmovable interface, indicating that
 * the object cannot be moved but can be interacted with.
 * 
 * <p>Classes implementing this interface should provide the implementation
 * for the {@code onAction} method, which defines the behavior when an
 * action is performed on the object.</p>
 * 
 * @see Unmovable
 */
public interface Interactable extends Unmovable {

    /**
     * Performs an action when a player interacts with this item.
     *
     * @param player the player who is interacting with the item
     * @return true if the action was successful, false otherwise
     */
    boolean onAction(Player player);
}
