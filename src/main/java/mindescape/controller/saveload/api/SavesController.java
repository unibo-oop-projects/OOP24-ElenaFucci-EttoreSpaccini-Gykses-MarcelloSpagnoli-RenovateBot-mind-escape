package mindescape.controller.saveload.api;

import mindescape.controller.core.api.ClickableController;

/**
 * Interface for save file management.
 */
public interface SavesController extends ClickableController {

    /**
     * Loads a save file based on the provided index.
     *
     * @param index the index of the save file to load
     */
    void loadSaveFile(int index);

    /**
     * Updates the view to reflect the current state of the model.
     * This method should be called whenever there are changes in the model
     * that need to be displayed in the view.
     */
    void updateView();
}
