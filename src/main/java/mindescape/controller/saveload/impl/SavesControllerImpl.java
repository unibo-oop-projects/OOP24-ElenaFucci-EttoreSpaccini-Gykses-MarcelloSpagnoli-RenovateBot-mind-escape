package mindescape.controller.saveload.impl;

import java.io.File;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.controller.saveload.api.SavesController;
import mindescape.model.api.Model;
import mindescape.model.saveload.api.Saves;
import mindescape.model.saveload.impl.SavesImpl;
import mindescape.model.saveload.util.SaveManager;
import mindescape.view.saveload.SavesView;

/**
 * Controller for the Saves screen.
 * 
 * <p>Handles the user input for the Saves screen and updates the view accordingly.</p>
 */
public final class SavesControllerImpl implements SavesController {

    private final Saves model;
    private final SavesView view;
    private final MainController mainController;
    private final String name = ControllerName.LOAD.getName();

    /**
     * Controller class responsible for handling save and load operations.
     * It initializes the model and view components and updates the view.
     *
     * @param mainController the main controller instance used to manage the overall application flow
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The main controller needs to be exposed to the caller")
    public SavesControllerImpl(final MainController mainController) {
        this.model = new SavesImpl();
        this.view = new SavesView(this);
        this.mainController = mainController;
        this.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleInput(final Object input) {
        Objects.requireNonNull(input);
        if (!(input instanceof String)) {
            throw new IllegalArgumentException("Invalid input type");
        }
        if ("LOAD_GAME".equals((String) input)) {
            loadSaveFile();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadSaveFile(final int index) {
        final List<File> saveFiles = model.getSortedSaveFiles();

        if (index >= 0 && index < saveFiles.size()) {
            this.load(saveFiles.get(index));
        }
    }

    /**
     * Loads the game status from the specified save file.
     * If the save file is invalid or an error occurs during loading,
     * the controller is set to the WORLD controller with a null parameter.
     *
     * @param saveFile the file from which to load the game status
     */
    private void load(final File saveFile) {
        try {
            final var saveData = SaveManager.loadGameStatus(saveFile);
            this.mainController.loadGame(saveData);
        } catch (IllegalArgumentException e) {
            this.mainController.setController(ControllerName.WORLD, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        this.view.updateSaveFiles(model.getSortedSaveFiles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.mainController.setController(ControllerName.MENU, null);
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
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
    }

    /**
     * Loads the save files from the model and updates the view.
     */
    private void loadSaveFile() {
        final var saveFiles = model.getSortedSaveFiles();

        if (!saveFiles.isEmpty()) {
            view.updateSaveFiles(saveFiles);
        }
    }
}
