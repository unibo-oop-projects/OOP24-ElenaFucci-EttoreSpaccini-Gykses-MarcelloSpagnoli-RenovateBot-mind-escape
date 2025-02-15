package mindescape.model.world.items.interactable.api;

import mindescape.model.world.core.impl.GameObjectImpl;
import mindescape.model.world.player.api.Player;

/**
 * An abstract decorator class for doors, extending {@link GameObjectImpl} and implementing {@link Door}.
 * This class allows additional functionalities to be added to a base {@link Door} instance dynamically.
 */
public abstract class AbstractDoorDecorator extends GameObjectImpl implements Door {

    private static final long serialVersionUID = 1L;

    /**
     * The base door instance being decorated.
     */
    private final Door baseDoor;

    /**
     * Constructs an {@code AbstractDoorDecorator} by wrapping an existing {@link Door} instance.
     *
     * @param baseDoor the door to be decorated.
     */
    public AbstractDoorDecorator(final Door baseDoor) {
        super(baseDoor.getPosition(), baseDoor.getName(), baseDoor.getDimensions());
        this.baseDoor = baseDoor;
    }

    /**
     * Performs the action associated with this door when interacted with by a {@link Player}.
     * If the door is unlocked, it delegates the action to the base door.
     *
     * @param player the player interacting with the door.
     */
    @Override
    public void onAction(final Player player) {
        if (this.isUnlocked()) {
            this.baseDoor.onAction(player);
        }
    }

    /**
     * Checks whether the decorated door is unlocked.
     *
     * @return {@code true} if the door is unlocked, {@code false} otherwise.
     */
    @Override
    public abstract boolean isUnlocked(); 
}
