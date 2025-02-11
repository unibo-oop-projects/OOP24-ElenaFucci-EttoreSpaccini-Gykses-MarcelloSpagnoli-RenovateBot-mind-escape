package mindescape.view.world;

import java.awt.Color;
import java.awt.Graphics;

import mindescape.model.world.core.api.Point2D;


public class PlayerView {

    int x;
    int y;

    public PlayerView(Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 16, 16);
    }

    public void setPosition(Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
    }

    public void draw(Graphics g, int offset, double scaling) {
        g.setColor(Color.RED);
        g.fillRect((int)((x * scaling) + offset), (int) (y * scaling), (int)(scaling * 16), (int)(scaling * 16));
    }
}
