package mindescape.api;

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
    UP, 
    /**
     * Move downwards.
     */
    DOWN, 
    /**
     * Move to the left.
     */
    LEFT, 
    /**
     * Move to the right.
     */
    RIGHT
}
