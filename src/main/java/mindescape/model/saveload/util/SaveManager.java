package mindescape.model.saveload.util;

import mindescape.model.world.api.World;
import mindescape.model.world.impl.WorldImpl;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

/**
 * The SaveManager class provides methods to save and load the game state.
 * 
 * <p>This class handles the serialization and deserialization of the {@code World} object,
 * allowing the game state to be saved to and loaded from files. The save files are stored
 * in a directory specified by the {@code SAVE_FOLDER} constant.</p>
 * 
 * <p>Note: The {@code World} class and its dependencies must be serializable for the save
 * and load operations to work correctly.</p>
 * 
 * @see World
 */
public class SaveManager {

    private static final String SAVE_FOLDER = "saves";

    /**
     * Saves the current game status to a file.
     *
     * <p>This method serializes the given {@code World} object and saves it to a file
     * named after the player's username in the specified save folder. If the save
     * directory does not exist, it will be created.</p>
     *
     * @param world the {@code World} object representing the current game state
     * @throws NullPointerException if the {@code World} object is null
     */
    public static void saveGameStatus(final World world) throws NullPointerException {
        Objects.requireNonNull(world, "World object cannot be null");
        var username = world.getPlayer().getName();
        File saveDir = new File(SAVE_FOLDER);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        File saveFile = new File(SAVE_FOLDER, username + ".sav");

        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(world.getPlayer());
            oos.writeObject(world.getRooms());
        } catch (final IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
    
    /**
     * Loads the game status from the specified save file.
     *
     * @param saveFile the file from which to load the game status
     * @return the loaded World object, or null if an error occurs
     * @throws NullPointerException if the save file is null
     * @throws IllegalArgumentException if the save file does not exist
     */
    public static World loadGameStatus(final File saveFile) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(saveFile, "Save file cannot be null");

        if (!saveFile.exists()) {
            throw new IllegalArgumentException("Save file does not exist");
        }
    
        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            var player = (Player) ois.readObject();
            @SuppressWarnings("unchecked")
            var rooms = (List<Room>) ois.readObject();
            return new WorldImpl(rooms, player);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}