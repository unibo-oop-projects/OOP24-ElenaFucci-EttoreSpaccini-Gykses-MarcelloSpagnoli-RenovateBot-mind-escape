package mindescape.controller.menu;

import java.util.Objects;

import javax.swing.JPanel;

import mindescape.controller.core.api.ClickableController;
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
        Objects.requireNonNull(input);
        switch ((String) input) {
            case Options.NEW_GAME -> this.mainController.switchToGame();
            case Options.LOAD_GAME -> System.out.println("TODO: Load game"); // TODO: Load game from file
            case Options.QUIT -> System.exit(0);
            default -> throw new IllegalArgumentException("Invalid input: " + input);
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

    private static class Options {
        public static final String NEW_GAME = "NEW_GAME";
        public static final String LOAD_GAME = "LOAD_GAME";
        public static final String QUIT = "QUIT";
    }
}
