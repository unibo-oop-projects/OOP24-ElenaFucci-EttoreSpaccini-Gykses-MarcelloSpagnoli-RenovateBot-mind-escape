package mindescape.controller.enigmapassword.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mindescape.controller.enigmapassword.api.EnigmaPasswordController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.enigma.enigmapassword.EnigmaPasswordModel;
import mindescape.model.enigma.impl.EnigmaPasswordModelImpl;
import mindescape.view.enigmapassword.api.EnigmaPasswordView;
import mindescape.view.enigmapassword.impl.EnigmaPasswordViewImpl;

public class EnigmaPasswordControllerImpl implements EnigmaPasswordController {

    private final MainController mainController;
    private final EnigmaPasswordModel model; 
    private final EnigmaPasswordView view; 

    public EnigmaPasswordControllerImpl(final EnigmaPasswordModel model, final MainController mainController) {
        this.mainController = mainController;
        this.model = model; 
        this.view = new EnigmaPasswordViewImpl(this); 
    }

    @Override
    public void handleInput(final Object input) {
        if (input instanceof String) {
            this.view.showResult(this.model.hit(input)); 
        }
    }

    @Override
    public String getName() {
        return this.model.getName(); 
    }

    @Override
    public JPanel getPanel() {
        return this.view.getPanel(); 
    }

    @Override
    public void quit() {
        this.mainController.setController(this.mainController.findController("World"));
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        EnigmaPasswordModel model = new EnigmaPasswordModelImpl("First Room Enigma", "secret");
        EnigmaPasswordController controller = new EnigmaPasswordControllerImpl(model, null);

        JFrame frame = new JFrame("Enigma Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(controller.getPanel());
        frame.setVisible(true);
    });
}
}