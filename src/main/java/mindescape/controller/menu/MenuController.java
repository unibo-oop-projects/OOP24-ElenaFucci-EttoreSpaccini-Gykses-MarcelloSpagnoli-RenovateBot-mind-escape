package mindescape.controller.menu;

import javax.swing.JPanel;
import mindescape.controller.api.ClickableController;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.View;
import mindescape.view.menu.MenuView;

public class MenuController implements ClickableController {
    private final View menuView;
    private final MenuModel menuModel;
    private final MainController mainController;
    private static final long serialVersionUID = 1L;
    private final String name = "Menu";

    public MenuController(final MainController mainController) {
       this.mainController = mainController;
        this.menuModel = new MenuModel();
        this.menuView = new MenuView(this);
    }

    @Override
    public void handleInput(Object input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JPanel getPanel() {
        this.menuView.getPanel();
    }
}
