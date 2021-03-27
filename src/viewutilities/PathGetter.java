package viewutilities;

import java.io.File;

public class PathGetter {
    private final String sep = File.separator;

    /**
     * @param path : un portable path
     * @return return the input path with the portable path separator instead "/"
     */
    public String getPortablePath(final String path) {
        return path.replaceAll("//", "//");
    }
}
