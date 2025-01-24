package mindescape.controller.api;

import java.io.File;

public interface WorldController {
    
    void processInput(UserInput input);
    void saveToFile(File file);
    boolean loadFromFile(File file);
    void newGame();
    void quitGame();
}
