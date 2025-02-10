package mindescape.view.testenigmi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mindescape.controller.enigmapassword.impl.EnigmaPasswordControllerImpl;
import mindescape.model.enigma.enigmapassword.impl.EnigmaPasswordModelImpl;

public class EnigmaPasswordTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final var controller = new EnigmaPasswordControllerImpl(new EnigmaPasswordModelImpl("Enigma Password", "123"), null);

            JFrame frame = new JFrame("Enigma Password Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(controller.getPanel());
            frame.setVisible(true);
        });
    }
}
