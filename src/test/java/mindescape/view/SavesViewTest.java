package mindescape.view;

import javax.swing.JFrame;
import mindescape.controller.saveload.SavesController;

public class SavesViewTest {
    public static void main(final String[] args) {
        JFrame frame = new JFrame();
        final SavesController save = new SavesController(null);
        frame.add(save.getPanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
