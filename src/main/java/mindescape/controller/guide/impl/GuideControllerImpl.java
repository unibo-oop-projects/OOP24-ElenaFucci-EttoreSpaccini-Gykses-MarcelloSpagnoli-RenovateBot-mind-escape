package mindescape.controller.guide.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mindescape.controller.core.api.ControllerName;
import mindescape.controller.guide.api.GuideController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.guide.api.GuideView;
import mindescape.view.guide.impl.GuideViewImpl;

public class GuideControllerImpl implements GuideController {

    private final MainController mainController;
    private final GuideView guideView;

    public GuideControllerImpl(final MainController mainController) {
        this.mainController = mainController;
        this.guideView = new GuideViewImpl(this);
    }

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
    }

    @Override
    public String getName() {
        return "Guide";
    }

    @Override
    public JPanel getPanel() {
        return this.guideView.getPanel();
    }

    @Override
    public void quit() {
        this.mainController.setController(ControllerName.MENU, null);
    }

    @Override
    public boolean canSave() {
        return true; 
    }

    @Override
    public Model getModel() {
        return null; 
    }

    @Override
    public void start() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Guide Controller");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            
            GuideController guideController = new GuideControllerImpl(null);
            frame.add(guideController.getPanel());
            
            frame.setVisible(true);
        });
    }
    
}
