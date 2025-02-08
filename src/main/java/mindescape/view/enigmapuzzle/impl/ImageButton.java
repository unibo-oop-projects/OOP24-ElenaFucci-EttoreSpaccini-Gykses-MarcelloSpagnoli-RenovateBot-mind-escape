package mindescape.view.enigmapuzzle.impl;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    private Image image;

    public ImageButton() {
        super();
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
