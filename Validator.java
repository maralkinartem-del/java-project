import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {

    public boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && Files.isReadable(path);
    }

    public int parseKey(String input) throws NumberFormatException {
        return Integer.parseInt(input.trim());
    }

    public boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}