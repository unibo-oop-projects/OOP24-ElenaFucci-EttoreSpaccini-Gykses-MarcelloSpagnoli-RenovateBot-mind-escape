package mindescape.controller.inventory;

import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mindescape.controller.core.api.ClickableController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.inventory.api.Inventory;
import mindescape.model.inventory.impl.InventoryImpl;
import mindescape.view.inventory.InventoryViewImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.items.interactable.impl.PickableImpl;

/**
 * The InventoryControllerImpl class implements the ClickableController interface
 * and manages the inventory view and inventory model. It handles user input,
 * updates the inventory view, and interacts with the main controller.
 */
public class InventoryControllerImpl implements ClickableController {

    private final InventoryViewImpl view;
    private final Inventory inventory;
    private final MainController mainController;

    /**
     * Constructs an InventoryControllerImpl object.
     *
     * @param mainController the main controller that coordinates the overall application
     */
    public InventoryControllerImpl(final MainController mainController) {
        this.inventory = new InventoryImpl();
        this.view = new InventoryViewImpl(this);
        this.mainController = mainController;
    }

    /**
     * Handles the input provided to the inventory controller.
     *
     * @param input the input object to be handled, must not be null
     * @throws IllegalArgumentException if the input is not of the expected type
     * @throws NullPointerException if the input is null
     */
    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(input);
        if ((int) input == KeyEvent.VK_I) {
            this.quit();                                 
        }
    }

    /**
     * Returns the name of the inventory.
     *
     * @return a string representing the name of the inventory, which is "INVENTORY".
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
     * @return true if the state can be saved, false otherwise.
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
     * Starts the inventory controller.
     * This method is intended to initialize and begin any processes or operations
     * related to the inventory management within the application.
     */
    @Override
    public void start() {
    }

    /**
     * Adds a pickable item to the inventory and updates the inventory view.
     *
     * @param pickable the item to be added to the inventory
     */
    public void addItemToInventory(Pickable pickable) {
        this.inventory.addItems(pickable);
        this.view.updateInventoryButtons(inventory.getItems());
    }

    /**
     * Handles the event when an item is clicked.
     * Retrieves the description of the clicked item and updates the view with this description.
     *
     * @param item the item that was clicked, must implement the Pickable interface
     */
    public void handleItemClick(Pickable item) {
        String description = item.getDescription();
        this.view.updateDescription(description);
    }

    
    public static void main(String[] args) {
        // Crea il controller dell'inventario senza il MainController
        InventoryControllerImpl inventoryController = new InventoryControllerImpl(null);
        
        // Crea una finestra JFrame
        JFrame frame = new JFrame("Inventario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Imposta la dimensione della finestra
        frame.setLocationRelativeTo(null);  // Posiziona la finestra al centro dello schermo
        frame.setResizable(true);  // Rende la finestra ridimensionabile
        
        // Aggiungi il JPanel della vista dell'inventario alla finestra
        frame.add(inventoryController.getPanel());
        
        // Mostra la finestra
        frame.setVisible(true);
        
        // Simuliamo l'aggiunta di oggetti all'inventario
        Dimensions dimensions1 = new Dimensions(2.0, 3.0);
        Dimensions dimensions2 = new Dimensions(1.0, 1.5);
        Optional<Point2D> position1 = Optional.of(new Point2D(5.0, 10.0));
        Optional<Point2D> position2 = Optional.of(new Point2D(15.0, 20.0));

        Pickable item1 = new PickableImpl(position1, "Chiave", dimensions1, "Una piccola chiave di ferro.", 1);
        Pickable item2 = new PickableImpl(position2, "Mappa", dimensions2, "Una mappa che mostra la posizione delle stanze.", 2);
        
        inventoryController.addItemToInventory(item1);
        inventoryController.addItemToInventory(item2);
    }
}

