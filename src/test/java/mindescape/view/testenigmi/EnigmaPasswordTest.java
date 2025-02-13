package mindescape.view.testenigmi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;

/**
 * This class is a test class for the EnigmaPassword component.
 */
final class EnigmaPasswordTest {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private EnigmaPasswordTest() {
    }

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
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
