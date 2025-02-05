package mindescape.view.api;

/**
 * The {@code View} interface provides the methods to start and update the view in the application.
 * Implementations of this interface should define the behavior for starting and updating the view.
 */
public interface View {
    /**
     * Starts the view. This method is responsible for initializing and displaying the view to the user.
     */
    void start();

    /**
     * Updates the view. 
     */
    void update();

    boolean toQuit();
}
