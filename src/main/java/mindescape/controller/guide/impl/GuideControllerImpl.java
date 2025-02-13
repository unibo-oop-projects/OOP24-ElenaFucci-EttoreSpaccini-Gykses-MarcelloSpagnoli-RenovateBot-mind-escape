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
public final class GuideControllerImpl implements GuideController {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ControllerName.GUIDE.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.guideView.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.MENU, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSave() {
        return false; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getModel() {
        return null; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
    }
}
