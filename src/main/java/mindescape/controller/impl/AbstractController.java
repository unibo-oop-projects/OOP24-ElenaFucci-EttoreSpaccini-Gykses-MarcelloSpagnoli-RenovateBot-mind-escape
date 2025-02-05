package mindescape.controller.impl;

import mindescape.controller.api.Controller;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.api.View;

public abstract class AbstractController implements Controller {
    private final View view;
    private final Model model;
    private final MainController mainController;

    public AbstractController(final View view, final Model model, final MainController mainController) {
        this.view = view;
        this.model = model;
        this.mainController = mainController;
    }

    public abstract void handleInput(final Object input);

    public abstract void loop();

    public abstract void quit();

}
