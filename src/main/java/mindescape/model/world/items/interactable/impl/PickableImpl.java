package mindescape.model.world.items.interactable.impl;

import java.util.Optional;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.items.interactable.api.Pickable;

/**
 * Represents a concrete implementation of the {@link Pickable} interface.
 * This class defines an item within the game world that can be picked up by the player.
 * It extends {@link GameObjectImpl}, inheriting common properties such as position, name, and dimensions.
 *
 * <p>The {@code PickableImpl} tracks whether the item has been picked up and provides
 * a description for additional context within the game.</p>
 *
 * @see Pickable
 * @see GameObjectImpl
 */
public class PickableImpl extends GameObjectImpl implements Pickable {

    private final String description;
    private boolean picked;

    /**
     * Constructs a pickable item with a specified position, name, dimensions, and description.
     *
     * @param position    the optional position of the item in the game world
     * @param name        the name of the item
     * @param dimensions  the dimensions of the item
     * @param description a brief description of the item
     */
    public PickableImpl(Optional<Point2D> position, String name, Dimensions dimensions, String description) {
        super(position, name, dimensions);
        this.description = description;
        this.picked = false;
    }

    /**
     * Defines the interaction behavior when the player interacts with the item.
     * This action marks the item as picked.
     */
    @Override
    public void onAction() {
        this.picked = true;
    }

    /**
     * Checks if the item has been picked up by the player.
     *
     * @return {@code true} if the item has been picked, {@code false} otherwise.
     */
    @Override
    public boolean isPicked() {
        return this.picked;
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
