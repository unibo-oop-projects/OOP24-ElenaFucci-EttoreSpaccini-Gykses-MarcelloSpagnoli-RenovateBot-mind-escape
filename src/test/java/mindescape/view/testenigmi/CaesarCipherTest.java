package mindescape.view.testenigmi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.caesarcipher.api.CaesarCipherController;
import mindescape.controller.caesarcipher.impl.CaesarCipherControllerImpl;
import mindescape.model.enigma.caesarcipher.impl.CaesarCipherModelImpl;

public class CaesarCipherTest {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            CaesarCipherController controller = new CaesarCipherControllerImpl(
                        new CaesarCipherModelImpl("Caesar Cipher", 3), 
                        null
                    );

            JFrame frame = new JFrame("Caesar Cipher Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(controller.getPanel());
            frame.setVisible(true);
        });
    }
}
