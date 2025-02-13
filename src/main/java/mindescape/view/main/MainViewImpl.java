package mindescape.view.main;

import java.util.Map;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mindescape.controller.core.api.KeyMapper;
import mindescape.controller.core.api.UserInput;
import mindescape.controller.maincontroller.api.MainController;
import mindescape.view.api.MainView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The view for the main screen.
 */
public class MainViewImpl implements MainView {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final MainController mainController;
    private JPanel currentPanel;
    private final JFrame frame = new JFrame("Mind Escape");
    private final Map<Integer, UserInput> keyMapper = KeyMapper.getKeyMap();

    /**
     * Constructs a MainViewImpl object.
     *
     * @param controller the main controller to be used by this view
     *
     * Initializes the main view with a given controller, sets up the main frame,
     * and adds necessary listeners for key events and window closing events.
     */
    public MainViewImpl(final MainController controller) {
        this.mainController = controller;
        this.currentPanel = new JPanel(); // Initialize with an empty panel

        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                Objects.requireNonNull(e);
                final UserInput input = keyMapper.get(e.getKeyCode());
                if (input != null) {
                    mainController.getController().handleInput(input);
                }
            }
        });

        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a listener to the window to ask for saving before closing 
        this.frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                if (mainController.getController().canSave()) {
                    final var option = JOptionPane.showConfirmDialog(
                        frame, 
                        "Do you want to save before exiting?", 
                        "Save before exiting", 
                        JOptionPane.YES_NO_CANCEL_OPTION
                    );

                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            mainController.save();
                        } catch (IllegalStateException exception) {
                            JOptionPane.showMessageDialog(frame, "An error occurred while saving the game.");
                        }
                    } else if (option == JOptionPane.NO_OPTION) {
                        mainController.exit();
                    }
                }
            }
        });
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setResizable(true);
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPanel(final JPanel panel) {
        this.frame.remove(this.currentPanel);
        this.currentPanel = panel;
        this.frame.add(this.currentPanel);
        this.currentPanel.setVisible(true);
        this.currentPanel.setFocusable(true);
        this.currentPanel.requestFocusInWindow(); 
        this.show();
        this.currentPanel.revalidate();
        this.currentPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel.setVisible(true);
            frame.repaint();
            frame.revalidate();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void won() {
        JOptionPane.showMessageDialog(frame, "You won!");
    }
}
