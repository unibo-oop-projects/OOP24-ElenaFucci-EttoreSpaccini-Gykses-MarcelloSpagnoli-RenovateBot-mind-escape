package mindescape.model.saveload.impl;

import java.io.File;
import java.util.Arrays;
import com.google.common.collect.Ordering;
import java.util.List;
import mindescape.model.saveload.api.Saves;

public class SavesImpl implements Saves {
    private final File savesDirectory = new File("saves");

    public SavesImpl() {
        if (!savesDirectory.exists()) {
            savesDirectory.mkdirs();
        }
    }

    @Override
    public List<File> getSortedSaveFiles() {
        var files = savesDirectory.listFiles((dir, name) -> name.endsWith(".sav"));

        if (files == null || files.length == 0) {
            return List.of();
        }
        return Ordering.natural()
            .onResultOf(File::lastModified)
            .reverse()
            .sortedCopy(Arrays.asList(files));
    }
}
