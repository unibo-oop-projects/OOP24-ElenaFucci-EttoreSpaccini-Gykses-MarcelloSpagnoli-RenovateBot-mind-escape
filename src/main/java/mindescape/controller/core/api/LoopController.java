package mindescape.controller.core.api;

/**
 * The LoopController interface defines the contract for controlling the game loop.
 * It extends the Controller interface and provides methods to start and stop the game loop.
 */
public interface LoopController extends Controller {

    /**
     * Executes the main loop of the controller.
     * This method is intended to be repeatedly called to perform
     * the core operations of the controller.
     *
     * @throws InterruptedException if the thread executing the loop is interrupted.
     */
    void loop() throws InterruptedException;

    /**
     * Stops the game loop.
     */
    void quit();
}
