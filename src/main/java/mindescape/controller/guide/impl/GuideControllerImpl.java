package mindescape.controller.guide.impl;

import javax.swing.JPanel;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.guide.api.GuideController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.guide.api.GuideView;
import mindescape.view.guide.impl.GuideViewImpl;

/**
 * Implementation of the GuideController interface.
 */
public class GuideControllerImpl implements GuideController {

    private final MainController mainController;
    private final GuideView guideView;

    /**
     * Constructs a new GuideControllerImpl with the specified MainController.
     *
     * @param mainController the main controller to be used by this guide controller
     */
    public GuideControllerImpl(final MainController mainController) {
        this.mainController = mainController;
        this.guideView = new GuideViewImpl(this);
    }

    @Override
    public void handleInput(final Object input) throws IllegalArgumentException, NullPointerException {
    }

    @Override
    public String getName() {
        return ControllerName.GUIDE.getName();
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
        return false; 
    }

    @Override
    public Model getModel() {
        return null; 
    }

    @Override
    public void start() {
    }
}
