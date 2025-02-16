package mindescape.model.world.rooms.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.tiledreader.TiledReader;
import org.tiledreader.TiledResource;

/**
 * TiledReader implentation which works for both ide and jar.
 */
public final class MapReader extends TiledReader {

    private final Map<String, TiledResource> resources = new HashMap<>();

    @Override
    public String getCanonicalPath(final String path) {
        return path;
    }

    @Override
    public String joinPaths(final String basePath, final String relativePath) {
        return new File(basePath).toPath().resolveSibling(relativePath).toString();
    }

    @Override
    public InputStream getInputStream(final String path) {
        //ide
        final File file = new File(path);
        if (file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File non trovato nel file system: " + path, e); //NOPMD
            }
        }

        String correctPath = path.replace(File.separator, "/");
        correctPath = correctPath.replaceAll("^.*/../", "");
        // Jar
        final InputStream stream = getClass().getClassLoader().getResourceAsStream(correctPath);
        if (stream == null) {
            throw new RuntimeException("File non trovato nel JAR: " + correctPath); //NOPMD
        }
        return stream;
    }

    @Override
    public TiledResource getCachedResource(final String path) {
        return resources.get(path);
    }

    @Override
    protected void setCachedResource(final String path, final TiledResource resource) {
        resources.put(path, resource);
    }

    @Override
    protected void removeCachedResource(final String path) {
        resources.remove(path);
    }

    @Override
    protected void clearCachedResources() {
        resources.clear();
    }

}
