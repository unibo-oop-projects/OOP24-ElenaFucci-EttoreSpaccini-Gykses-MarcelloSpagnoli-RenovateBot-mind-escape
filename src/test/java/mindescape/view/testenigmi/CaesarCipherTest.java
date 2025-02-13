package mindescape.view.testenigmi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.caesarcipher.api.CaesarCipherController;
import mindescape.controller.caesarcipher.impl.CaesarCipherControllerImpl;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;

/**
 * Test class for the CaesarCipher enigma View.
 */
final class CaesarCipherTest {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;

    private CaesarCipherTest() {
    }

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final CaesarCipherController controller = new CaesarCipherControllerImpl(
                        new CaesarCipherModelImpl("Caesar Cipher", 3), 
                        null
                    );

            final JFrame frame = new JFrame("Caesar Cipher Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.add(controller.getPanel());
            frame.setVisible(true);
        });
    }
}
