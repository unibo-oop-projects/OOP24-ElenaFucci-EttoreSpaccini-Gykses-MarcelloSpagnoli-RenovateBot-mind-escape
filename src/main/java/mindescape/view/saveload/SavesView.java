package mindescape.view.saveload;

import mindescape.controller.saveload.SavesController;
import mindescape.view.api.View;
import mindescape.view.utils.ViewUtils;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class SavesView extends JPanel implements View {
    private final SavesController controller;
    private final DefaultListModel<String> saveListModel;
    private final JList<String> saveList;
    private final JButton loadButton;
    private final JButton menuButton;

    public SavesView(final SavesController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(20, 20, 20));

        this.saveListModel = new DefaultListModel<>();
        this.saveList = new JList<>(saveListModel);
        styleSaveList();

        final JScrollPane scrollPane = new JScrollPane(saveList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));

        loadButton = ViewUtils.createStyledButton("Load Save");
        loadButton.addActionListener(e -> this.loadSelectedSave());
        
        this.menuButton = ViewUtils.createStyledButton("Menu");
        this.menuButton.addActionListener(e -> this.controller.quit());

        final JPanel buttonPanel = ViewUtils.createStyledPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(menuButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleSaveList() {
        saveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        saveList.setBackground(new Color(0, 0, 0));
        saveList.setForeground(new Color(255, 215, 0));
        saveList.setFont(new Font("Arial", Font.PLAIN, 16));
        saveList.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
    }

    public void updateSaveFiles(List<File> saveFiles) {
        saveListModel.clear();

        if (saveFiles.isEmpty()) {
            saveListModel.addElement("No save files found.");
            loadButton.setEnabled(false);
        } else {
            for (File file : saveFiles) {
                saveListModel.addElement(file.getName());
            }
            loadButton.setEnabled(true);
        }
    }

    private void loadSelectedSave() {
        int selectedIndex = saveList.getSelectedIndex();
        if (selectedIndex != -1 && !saveListModel.get(selectedIndex).equals("No save files found.")) {
            controller.loadSaveFile(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void draw() {
    }
}
