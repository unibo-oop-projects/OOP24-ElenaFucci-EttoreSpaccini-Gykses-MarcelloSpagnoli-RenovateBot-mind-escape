package mindescape.model.saveload;

import mindescape.model.world.api.World;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SaveManager {

    private static final String SAVE_FOLDER = "saves";

    /**
     * Saves the current status of the game world.
     *
     * @param world    the current state of the game world to be saved
     * @param username the username associated with the save file
     */
    public static void saveGameStatus(final World world) {
        var username = world.getPlayer().getName();
        File saveDir = new File(SAVE_FOLDER);
        if (!saveDir.exists()) {
            saveDir.mkdirs(); // Crea la cartella se non esiste
        }

        File saveFile = new File(SAVE_FOLDER, username + ".sav");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(world);
            System.out.println("Game saved successfully for " + username);
        } catch (IOException e) { }
    }

    
    public static World loadGameStatus(final File saveFile) {
        Objects.requireNonNull(saveFile, "Save file cannot be null");

        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Save file does not exist");
        }
    
        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            return (World) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}