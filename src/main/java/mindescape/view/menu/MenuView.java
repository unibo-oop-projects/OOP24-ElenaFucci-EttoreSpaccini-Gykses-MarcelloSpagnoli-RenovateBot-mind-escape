package mindescape.view.menu;

import javax.swing.JPanel;

import mindescape.controller.api.ClickableController;
import mindescape.view.api.View;

public class MenuView extends JPanel implements View {
    private static final long serialVersionUID = 1L;
    private final ClickableController menuController;

    public MenuView(final ClickableController menuController) {
        this.menuController = menuController;
    }


    @Override
    public void draw() {
        
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

}
