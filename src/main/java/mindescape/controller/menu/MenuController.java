package mindescape.controller.menu;

import mindescape.controller.api.UserInput;
import mindescape.controller.impl.AbstractController;
import mindescape.model.api.Model;
import mindescape.view.api.View;
import mindescape.view.menu.MenuView;

public class MenuController extends AbstractController {

    public MenuController(final View view, final Model model) {
        super(view, model);
        new MenuView(this);
    }
    
    @Override
    public void handleInput(final UserInput input) {
    }

    @Override
    public void loop() {
    }

    public void startGame() {

    }

    public void loadGame() {
    }

    public void quitGame() {
        System.exit(0);
    }

}
