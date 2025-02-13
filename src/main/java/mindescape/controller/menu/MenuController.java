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

    /**
     * New game button action command.
     */
    public static final String NEW_GAME = "NEW_GAME";
    /**
     * Load game button action command.
     */
    public static final String LOAD_GAME = "LOAD_GAME";
    /**
     * Quit button action command.
     */
    public static final String QUIT = "QUIT";
    /**
     * Guide button action command.
     */
    public static final String GUIDE = "GUIDE";

    private final String name = ControllerName.MENU.getName();
    private final View menuView;
    private final MainController mainController;

    /**
     * Creates a new MenuController.
     * 
     * @param mainController the main controller
     */
    public MenuController(final MainController mainController) {
        this.mainController = mainController;
        this.menuView = new MenuView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        switch ((String) input) {
            case NEW_GAME -> this.newGame();
            case LOAD_GAME -> this.loadGame();
            case QUIT -> this.quit();
            case GUIDE -> this.guideAction();
            default -> throw new IllegalArgumentException("Invalid input: " + input);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.menuView.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * Starts a new game.
     */
    private void newGame() {
        while (true) {
            final String playerName = JOptionPane.showInputDialog(
                null, 
                "Enter your name:", 
                "New Game", 
                JOptionPane.QUESTION_MESSAGE
            );
            // If the user cancels the input dialog, playerName will be null and we break the loop 
            if (playerName == null) {
                return;
            }

            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.mainController.setPlayerName(playerName);
                this.mainController.setController(ControllerName.WORLD, null);
                return;
            }
        }
    }
    /**
     * Loads the game state from a saved file.
     */
    private void loadGame() {
        this.mainController.setController(ControllerName.LOAD, null);
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

    /**
     * Action to be executed when the guide button is clicked.
     */
    private void guideAction() {
        this.mainController.setController(ControllerName.GUIDE, null);
    }
}
