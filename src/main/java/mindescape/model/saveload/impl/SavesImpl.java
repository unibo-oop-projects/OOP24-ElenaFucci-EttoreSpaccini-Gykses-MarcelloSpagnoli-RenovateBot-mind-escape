package mindescape.model.saveload.impl;

import java.io.File;
import java.util.Arrays;
import com.google.common.collect.Ordering;
import java.util.List;
import mindescape.model.saveload.api.Saves;

/**
 * Implementation of the Saves interface.
 */
public class SavesImpl implements Saves {

    private final File savesDirectory = new File("saves");

    /**
     * Constructor for the SavesImpl class.
     * This constructor checks if the saves directory exists.
     * If the directory does not exist, it creates the necessary directories.
     */
    public SavesImpl() {
        if (!savesDirectory.exists()) {
            try {
                savesDirectory.mkdirs();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> getSortedSaveFiles() {
        final var files = savesDirectory.listFiles((dir, name) -> name.endsWith(".sav"));

        if (files == null || files.length == 0) {
            return List.of();
        }
        return Ordering.natural()
            .onResultOf(File::lastModified)
            .reverse()
            .sortedCopy(Arrays.asList(files));
    }
}
