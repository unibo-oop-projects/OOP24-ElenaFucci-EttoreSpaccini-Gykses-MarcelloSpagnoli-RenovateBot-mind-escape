package mindescape.controller.core.api;

public interface LoopController extends Controller {

    /**
     * Starts the game loop.
     */
    void loop();

    /**
     * Stops the game loop.
     */
    void quit();
}
