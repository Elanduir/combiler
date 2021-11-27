import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class FileHandler {
    ClassLoader loader;
    public FileHandler(){
        loader = super.getClass().getClassLoader();
    }
    public URL getFileURL(String filename) {
        return loader.getResource(filename);
    }

    public Path getFilePath(String filename) throws URISyntaxException {
        return Path.of(loader.getResource(filename).toURI());
    }
}
