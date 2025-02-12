package mindescape.model.saveload.api;

import java.io.File;
import java.util.List;
import mindescape.model.api.Model;

/**
 * The Saves interface provides a method to retrieve a list of save files.
 */
public interface Saves extends Model {

    /**
     * Retrieves a list of save files sorted by their last modified date in descending order.
     *
     * @return a list of sorted save files.
     */
    List<File> getSortedSaveFiles();

}
