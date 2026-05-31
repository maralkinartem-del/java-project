import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    private static final int BUFFER_SIZE = 8192; // 8 KB

    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            char[] buffer = new char[BUFFER_SIZE];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, charsRead);
            }
        }
        return content.toString();
    }

    public void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }

    public void createDirectory(String dirPath) throws IOException {
        java.nio.file.Files.createDirectories(java.nio.file.Paths.get(dirPath));
    }
}