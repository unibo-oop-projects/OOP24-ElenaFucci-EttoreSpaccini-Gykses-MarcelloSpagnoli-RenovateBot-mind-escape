package mindescape.view.testenigmi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;

/**
 * This class is a test class for the EnigmaPassword component.
 * It initializes the EnigmaPasswordControllerImpl with a model containing
 * a title and a password, and sets up a JFrame to display the controller's panel.
 * 
 * The main method uses SwingUtilities.invokeLater to ensure that the GUI creation
 * and updates are performed on the Event Dispatch Thread.
 * 
 * The JFrame is configured with a title "Enigma Password Test", a default close operation,
 * and a size of 400x300 pixels. The controller's panel is added to the frame, and the frame
 * is made visible.
 */
final class EnigmaPasswordTest {

    private final static int WIDTH = 400;
    private final static int HEIGHT = 300;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final var controller = new EnigmaPasswordControllerImpl(
                    new EnigmaPasswordModelImpl("Enigma Password", "123"), 
                    null
                );

            final JFrame frame = new JFrame("Enigma Password Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.add(controller.getPanel());
            frame.setVisible(true);
        });
    }
}
