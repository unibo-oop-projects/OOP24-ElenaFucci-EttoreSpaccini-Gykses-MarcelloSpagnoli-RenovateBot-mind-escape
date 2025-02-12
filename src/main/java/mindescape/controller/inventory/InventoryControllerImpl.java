package mindescape.controller.inventory;

import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.JPanel;

import mindescape.controller.core.api.ClickableController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.inventory.api.Inventory;
import mindescape.view.inventory.InventoryViewImpl;
import mindescape.model.world.items.interactable.api.Pickable;


/**
 * Implementation of the InventoryController interface.
 * This class handles the interactions with the inventory view and the main controller.
 * It manages the inventory and updates the view accordingly.
 */
public class InventoryControllerImpl implements ClickableController {

    private final InventoryViewImpl view;
    private final Inventory inventory;
    private final MainController mainController;

    /**
     * Constructs an InventoryControllerImpl with the specified inventory and main controller.
     *
     * @param inventory the inventory to be managed by this controller
     * @param mainController the main controller to be associated with this inventory controller
     */
    public InventoryControllerImpl(Inventory inventory, final MainController mainController) {
        this.inventory = inventory;
        this.view = new InventoryViewImpl(this);
        this.mainController = mainController;
    }

    /**
     * Retrieves the current inventory.
     *
     * @return the inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Handles the input provided to the inventory controller.
     *
     * @param input the input object to be handled, expected to be an integer representing a key event.
     * @throws IllegalArgumentException if the input is not an integer.
     * @throws NullPointerException if the input is null.
     */
    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(input);
        if ((int) input == KeyEvent.VK_I) {
            this.quit();                                 
        }
    }

    /**
     * Retrieves the name of the controller.
     *
     * @return the name of the controller as a String.
     */
    @Override
    public String getName() {
        return ControllerName.INVENTORY.getName();
    }

    /**
     * Retrieves the JPanel associated with the current view.
     *
     * @return the JPanel from the view.
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    /**
     * Quits the current controller and sets the main controller to the WORLD controller.
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD);
    }

    /**
     * Determines if the current state can be saved.
     *
     * @return true if the current state can be saved, false otherwise.
     */
    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * Retrieves the model associated with this controller.
     *
     * @return the model associated with this controller, or {@code null} if no model is set.
     */
    @Override
    public Model getModel() {
        return null;
    }

    /**
     * Starts the inventory controller by updating the inventory buttons in the view
     * with the current items in the inventory. It also prints the contents of the 
     * inventory to the console.
     */
    @Override
    public void start() {
        this.view.updateInventoryButtons(inventory.getItems());
        System.out.println("L'inventario contiene: " + inventory.getItems().toString());
    }

    /**
     * Handles the event when an item is clicked.
     * Retrieves the description of the clicked item and updates the view with this description.
     *
     * @param item the item that was clicked, which implements the Pickable interface
     */
    public void handleItemClick(Pickable item) {
        String description = item.getDescription();
        this.view.updateDescription(description);
    }
}
