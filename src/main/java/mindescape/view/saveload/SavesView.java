package mindescape.view.saveload;

import mindescape.controller.saveload.api.SavesController;
import mindescape.view.api.View;
import mindescape.view.utils.ViewUtils;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Date;

/**
 * The view for the saves screen.
 */
public final class SavesView extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOR = new Color(20, 20, 20);
    private static final Color BORDER_COLOR = new Color(255, 215, 0);
    private static final Color LIST_BACKGROUND_COLOR = new Color(0, 0, 0);
    private static final Color LIST_FOREGROUND_COLOR = new Color(255, 215, 0);
    private static final Font LIST_FONT = new Font("Arial", Font.BOLD, 18);
    private static final int BORDER_THICKNESS = 2;
    private static final int BUTTON_PANEL_PADDING = 10;
    private static final String NO_SAVES_MESSAGE = "No save files found.";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private final SavesController controller;
    private final DefaultListModel<String> saveListModel;
    private final JList<String> saveList;
    private final JButton loadButton;
    private final JButton menuButton;

    /**
     * Constructs the SavesView with a given controller.
     *
     * @param controller the SavesController instance that handles the logic for loading saves and quitting to the menu
     */
    public SavesView(final SavesController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);

        this.saveListModel = new DefaultListModel<>();
        this.saveList = new JList<>(saveListModel);
        styleSaveList();

        final JScrollPane scrollPane = new JScrollPane(saveList);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));

        loadButton = ViewUtils.createStyledButton("Load Save");
        loadButton.addActionListener(e -> this.loadSelectedSave());

        menuButton = ViewUtils.createStyledButton("Menu");
        menuButton.addActionListener(e -> this.controller.quit());

        final JPanel buttonPanel = ViewUtils.createStyledPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(BUTTON_PANEL_PADDING, BUTTON_PANEL_PADDING, BUTTON_PANEL_PADDING, BUTTON_PANEL_PADDING));
        buttonPanel.add(menuButton, BorderLayout.WEST);
        buttonPanel.add(loadButton, BorderLayout.EAST);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    private void styleSaveList() {
        saveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        saveList.setBackground(LIST_BACKGROUND_COLOR);
        saveList.setForeground(LIST_FOREGROUND_COLOR);
        saveList.setFont(LIST_FONT);
        saveList.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
    }

    public void updateSaveFiles(final List<File> saveFiles) {
        saveListModel.clear();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

        if (saveFiles.isEmpty()) {
            saveListModel.addElement(NO_SAVES_MESSAGE);
            loadButton.setEnabled(false);
        } else {
            for (final File file : saveFiles) {
                final String fileName = file.getName().replace(".sav", "");
                final String lastModified = dateFormat.format(new Date(file.lastModified()));
                saveListModel.addElement(fileName + "\t" + lastModified);
            }
            loadButton.setEnabled(true);
        }
    }

    private void loadSelectedSave() {
        final int selectedIndex = saveList.getSelectedIndex();
        if (!NO_SAVES_MESSAGE.equals(saveListModel.get(selectedIndex))) {
            controller.loadSaveFile(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }
}
