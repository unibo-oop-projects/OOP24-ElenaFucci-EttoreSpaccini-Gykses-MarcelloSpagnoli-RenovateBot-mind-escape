package mindescape.view.api;

import java.awt.Graphics;
import java.util.Map;

import mindescape.model.world.core.api.Point2D;

/**
 * Interface that models an animated player renderer.
 */
public interface AnimatedPlayerRenderer {

    /**
     * Sets the position of the player on the rendered room.
     * @param pos the position of the player.
     */
    void setPosition(Point2D pos); 

    /**
     * Draws the player sprite at the given position.
     * 
     * @param g The graphics context
     * @param offset The offset value for rendering
     * @param scaling The scaling factor
     * @param keys The map of pressed keys
     */
    void draw(Graphics g, int offset, double scaling, Map<Integer, Boolean> keys);
}


