package mindescape.controller.core.api;

/**
 * The LoopController interface defines the contract for controlling the game loop.
 * It extends the Controller interface and provides methods to start and stop the game loop.
 */
public interface LoopController extends Controller {

    /**
     * Stops the game loop.
     */
    void quit();


}
