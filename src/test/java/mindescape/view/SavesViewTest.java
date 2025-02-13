package mindescape.view;

import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.saveload.api.SavesController;
import mindescape.controller.saveload.impl.SavesControllerImpl;

/**
 * This class is used to test the SavesView.
 */
final class SavesViewTest {

    private static final int WIDTH = 500;
    private static final int  HEIGHT = 500;

    private SavesViewTest() {
    }

    /**
     * Main method to test the SavesView.
     * @param args the arguments.
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            final SavesController save = new SavesControllerImpl(null);
            frame.add(save.getPanel());
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
        });        
    }

}
