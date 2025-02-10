package mindescape.controller.inventory;

import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.text.View;

import mindescape.controller.core.api.ClickableController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.impl.InventoryImpl;
import mindescape.view.inventory.InventoryViewImpl;

public class InventoryControllerImpl implements ClickableController {

    private final MainController maincController;
    private final InventoryViewImpl view;
    private final Inventory inventory;

    public InventoryControllerImpl(MainController mainController) {
        this.maincController = mainController;
        this.inventory = new InventoryImpl();
        this.view = new InventoryViewImpl(this);
    }

    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(input);
        if ((UserInput)input == UserInput.INVENTORY) {
            this.quit();                                 
        }
    }

    @Override
    public String getName() {
        return ControllerName.INVENTORY.getName();
    }

    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    @Override
    public void quit() {
        this.maincController.setController(ControllerName.WORLD);
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public void start() {
    }

}
