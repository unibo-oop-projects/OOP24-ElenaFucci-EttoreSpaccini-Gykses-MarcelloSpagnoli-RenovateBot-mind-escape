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
    public void handleInput(final Object input) throws IllegalArgumentException {
        Objects.requireNonNull(input);
        switch ((String) input) {
            case Options.NEW_GAME -> this.mainController.setController(this.mainController.findController("InsertName"));
            case Options.LOAD_GAME -> this.mainController.setController(this.mainController.findController("LoadGame"));
            case Options.QUIT -> this.quit();
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

    @Override
    public void quit() {
        System.exit(0);
    }

}
