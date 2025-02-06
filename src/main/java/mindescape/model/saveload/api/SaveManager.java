package mindescape.model.saveload.api;

import mindescape.model.world.api.World;

public interface SaveManager {
    
    /**
     * Saves the current status of the game world.
     *
     * @param world the current state of the game world to be saved
     */
    void saveGameStatus(World world);

    /**
     * Loads the current game status from a saved state.
     * This method is responsible for retrieving and restoring the game's state
     * from a previously saved file.
     */
    void loadGameStatus();

}
