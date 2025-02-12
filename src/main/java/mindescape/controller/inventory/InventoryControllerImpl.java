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
 * La classe InventoryControllerImpl implementa l'interfaccia ClickableController
 * e gestisce la vista dell'inventario e il modello dell'inventario.
 */
public class InventoryControllerImpl implements ClickableController {

    private final InventoryViewImpl view;
    private final Inventory inventory;
    private final MainController mainController;

    /**
     * Costruisce un oggetto InventoryControllerImpl.
     * 
     * @param mainController il controller principale che coordina l'applicazione
     */
    public InventoryControllerImpl(Inventory inventory, final MainController mainController) {
        this.inventory = inventory;
        this.view = new InventoryViewImpl(this);
        this.mainController = mainController;
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gestisce l'input dell'utente per il controller dell'inventario.
     * Se l'utente preme 'I', chiude la vista dell'inventario e torna al mondo.
     */
    @Override
    public void handleInput(Object input) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(input);
        if ((int) input == KeyEvent.VK_I) {
            this.quit();                                 
        }
    }

    /**
     * Restituisce il nome del controller dell'inventario.
     * 
     * @return il nome del controller dell'inventario
     */
    @Override
    public String getName() {
        return ControllerName.INVENTORY.getName();
    }

    /**
     * Restituisce il JPanel associato alla vista dell'inventario.
     * 
     * @return il JPanel della vista
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    /**
     * Permette di uscire dal controller dell'inventario e passare al controller WORLD.
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.WORLD);
    }

    /**
     * Determina se lo stato può essere salvato.
     * 
     * @return true se lo stato può essere salvato
     */
    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * Restituisce il modello associato al controller dell'inventario.
     * 
     * @return il modello, che in questo caso è null
     */
    @Override
    public Model getModel() {
        return null;
    }

    /**
     * Avvia il controller dell'inventario.
     * Inizializza l'inventario e aggiorna i bottoni della vista.
     */
    @Override
    public void start() {
        // Ogni volta che l'inventario cambia, la vista viene aggiornata automaticamente
        // grazie all'Observer Pattern
        this.view.updateInventoryButtons(inventory.getItems());
        System.out.println("L'inventario contiene: " + inventory.getItems().toString());
    }

    /**
     * Gestisce il click su un oggetto nell'inventario.
     * Aggiorna la descrizione dell'oggetto selezionato nella vista.
     * 
     * @param item l'oggetto che è stato cliccato
     */
    public void handleItemClick(Pickable item) {
        String description = item.getDescription();
        this.view.updateDescription(description);
    }
}
