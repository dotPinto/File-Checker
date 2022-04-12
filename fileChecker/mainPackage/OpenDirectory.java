package mainPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class OpenDirectory {

    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
        try (Stream<Path> entries = Files.list(path)) {
            return !entries.findFirst().isPresent();
        }
    }
    
        return false;
    }

    public String getString(Path path) throws IOException {
        return path.toString();
    }
}
