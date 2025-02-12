package mindescape.controller.saveload;

import java.io.File;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;
import mindescape.controller.core.api.ClickableController;
import mindescape.controller.core.api.ControllerName;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.model.api.Model;
import mindescape.model.saveload.api.Saves;
import mindescape.model.saveload.impl.SavesImpl;
import mindescape.model.saveload.util.SaveManager;
import mindescape.view.saveload.SavesView;

public class SavesController implements ClickableController {
    private final Saves model;
    private final SavesView view;
    private final MainController mainController;
    private final String name = "LoadGame";

    public SavesController(final MainController mainController) {
        this.model = new SavesImpl();
        this.view = new SavesView(this);
        this.mainController = mainController;
        updateView();
    }

    @Override
    public void handleInput(final Object input) throws IllegalArgumentException {
        Objects.requireNonNull(input);
        if (!(input instanceof String)) {
            throw new IllegalArgumentException("Invalid input type");
        }
        if (((String) input).equals("LOAD_GAME")) {
            loadSaveFile();
        }
    }

    public void loadSaveFile() {
        var saveFiles = model.getSortedSaveFiles();

        if (!saveFiles.isEmpty()) {
            view.updateSaveFiles(saveFiles);
        }
    }

    public void loadSaveFile(final int index) {
        List<File> saveFiles = model.getSortedSaveFiles();

        if (index >= 0 && index < saveFiles.size()) {
            load(saveFiles.get(index));
        }
    }

    private void load(final File saveFile) {
        try {
            var saveData = SaveManager.loadGameStatus(saveFile);
            mainController.loadGame(saveData);
        } catch (NullPointerException | IllegalArgumentException e) {
            // mainController.newGame();
        }
    }

    public void updateView() {
        view.updateSaveFiles(model.getSortedSaveFiles());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JPanel getPanel() {
        return this.view.getPanel();
    }

    @Override
    public void quit() {
        this.mainController.setController(ControllerName.MENU, null);
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public Model getModel() {
        //TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start() {
    }
}
