package mindescape.controller.menu;

import javax.swing.JPanel;
import mindescape.controller.api.ClickableController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.View;
import mindescape.view.menu.MenuView;

public class MenuController implements ClickableController {

    private final static String NAME = "Menu";
    private final View menuView;
    private final MainController mainController;
    private static final long serialVersionUID = 1L;

    public MenuController(final MainController mainController) {
        this.mainController = mainController;
        this.menuView = new MenuView(this);
    }

    @Override
    public void handleInput(final Object input) {
        switch ((String) input) {
            case Options.NEW_GAME:
                System.out.println("Starting new game..."); // TODO: remove
                this.mainController.switchToGame();
                break;
            case Options.LOAD_GAME:
                System.out.println("Loading game..."); // TODO: remove
                break;
            case Options.QUIT:
                System.exit(0);
                break;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public JPanel getPanel() {
        return this.menuView.getPanel();
    }

    private class Options {
        public static final String NEW_GAME = "NEW_GAME";
        public static final String LOAD_GAME = "LOAD_GAME";
        public static final String QUIT = "QUIT";
    }
}
