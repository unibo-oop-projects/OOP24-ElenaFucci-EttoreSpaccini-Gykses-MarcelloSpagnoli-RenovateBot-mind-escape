package mindescape.view;

import javax.swing.JFrame;

import mindescape.controller.saveload.api.SavesController;
import mindescape.controller.saveload.impl.SavesControllerImpl;

public class SavesViewTest {
    public static void main(final String[] args) {
        JFrame frame = new JFrame();
        final SavesController save = new SavesControllerImpl(null);
        frame.add(save.getPanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
