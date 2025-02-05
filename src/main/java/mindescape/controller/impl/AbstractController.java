package mindescape.controller.impl;

import mindescape.controller.api.Controller;
import mindescape.controller.api.UserInput;
import mindescape.model.api.Model;
import mindescape.view.api.View;

public abstract class AbstractController implements Controller {
    private final View view;
    private final Model model;
    private boolean running = true;

    public AbstractController(final View view, final Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    public abstract void handleInput(final UserInput input);

    public abstract void loop();

}
