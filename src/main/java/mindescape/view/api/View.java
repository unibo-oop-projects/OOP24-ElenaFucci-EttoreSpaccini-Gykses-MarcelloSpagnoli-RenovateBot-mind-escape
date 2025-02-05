package mindescape.view.api;
import javax.swing.JPanel;

/**
 * The {@code View} interface provides the methods to update the view in the application.
 * Implementations of this interface should define the behavior for starting and updating the view.
 */
public interface View {

    /**
     * Updates the view. 
     */
    void draw();

    /**
     * Returns the panel of the view.
     * @return {@code JPanel} of the view.
     */
    JPanel getPanel();
}
