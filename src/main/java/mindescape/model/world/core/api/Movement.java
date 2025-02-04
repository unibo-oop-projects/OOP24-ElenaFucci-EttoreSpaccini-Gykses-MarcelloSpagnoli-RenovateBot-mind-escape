package mindescape.model.world.core.api;

/**
 * The {@code Movement} enum represents the possible directions 
 * in which an entity can move within the game.
 * <ul>
 *   <li>{@link #Up} - Move upwards</li>
 *   <li>{@link #Down} - Move downwards</li>
 *   <li>{@link #Left} - Move to the left</li>
 *   <li>{@link #Right} - Move to the right</li>
 * </ul>
 */
public enum Movement {
    /**
     * Move upwards.
     */
    UP(0, -1), 
    /**
     * Move downwards.
     */
    DOWN(0, 1), 
    /**
     * Move to the left.
     */
    LEFT(-1, 0), 
    /**
     * Move to the right.
     */
    RIGHT(1, 0);

    private final double x;
    private final double y;

    Movement(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the movement.
     * 
     * @return {@code double} x-coordinate of the movement
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the movement.
     * 
     * @return {@code double} y-coordinate of the movement
     */
    public double getY() {
        return this.y;
    }
}
