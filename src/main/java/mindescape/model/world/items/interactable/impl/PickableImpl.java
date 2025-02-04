package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.player.api.Player;

/**
 * Represents a concrete implementation of the {@link Pickable} interface.
 * <p>
 * This class defines an item within the game world that can be picked up
 * by the player. It extends {@link GameObjectImpl}, inheriting common
 * properties such as position, name, and dimensions.
 * </p>
 * <p>
 * The {@code PickableImpl} tracks whether the item has been picked up and
 * provides a description for additional context within the game.
 * </p>
 *
 * @see Pickable
 * @see GameObjectImpl
 */
public class PickableImpl extends GameObjectImpl implements Pickable {

    private final String description;

    /**
     * Constructs a pickable item with a specified position, name,
     * dimensions, and description.
     *
     * @param position    the optional position of the item in the game world
     * @param name        the name of the item
     * @param dimensions  the dimensions of the item
     * @param description a brief description of the item
     */
    public PickableImpl(final Optional<Point2D> position, final String name,
                        final Dimensions dimensions, final String description) {
        super(position, name, dimensions);
        this.description = description;
    }

    /**
     * Defines the interaction behavior when the player interacts with the item.
     * <p>
     * This action adds the item to the player's inventory and removes it from
     * the current room.
     * </p>
     *
     * @param player the player interacting with the item
     */
    @Override
    public void onAction(final Player player) {
        player.getInventory().addItems(this);
        player.getCurrentRoom().removeGameObject(this);
    }

    /**
     * Retrieves the description of the item.
     *
     * @return the description of the item
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
