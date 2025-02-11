package mindescape.controller.menu;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mindescape.controller.core.api.ClickableController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.view.api.View;
import mindescape.view.menu.MenuView;

/**
 * Controller for the main menu.
 */
public class MenuController implements ClickableController {

    private static class Options {
        public static final String NEW_GAME = "NEW_GAME";
        public static final String LOAD_GAME = "LOAD_GAME";
        public static final String QUIT = "QUIT";
    }

    private final static String NAME = ControllerName.MENU.getName();
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
            case Options.NEW_GAME -> this.newGame();
            case Options.LOAD_GAME -> this.loadGame();
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

    @Override
    public void quit() {
        System.exit(0);
    }

    private void newGame() {
        while (true) {
            final String playerName = JOptionPane.showInputDialog(null, "Enter your name:", "New Game", JOptionPane.QUESTION_MESSAGE);
            // If the user cancels the input dialog, playerName will be null and we break the loop 
            if (playerName == null) {
                return;
            }

            if (playerName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.mainController.setPlayerName(playerName);
                this.mainController.setController(ControllerName.WORLD);
                return; 
            } 
        }
    }
    
    /**
     * Loads the game state from a saved file.
     */
    private void loadGame() {
        this.mainController.setController(ControllerName.LOAD);
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public Model getModel() {
        //TODO: Auto-generated method stub
        return null;
    }

    @Override
    public void start() {
    }

}
