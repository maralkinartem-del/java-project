import java.io.File;
import java.io.IOException;

public class BruteForceDecryptor {

    private final CaesarCipher cipher;
    private final FileHandler fileHandler;

    public BruteForceDecryptor() {
        this.cipher = new CaesarCipher();
        this.fileHandler = new FileHandler();
    }

    public void decrypt(String inputFile, String outputDir) throws IOException {
        String text = fileHandler.readFile(inputFile);
        String alphabet = Alphabet.detect(text);

        fileHandler.createDirectory(outputDir);

        System.out.println("Создаю " + alphabet.length() + " вариантов расшифровки...");
        for (int key = 1; key <= alphabet.length(); key++) {
            String decrypted = cipher.decrypt(text, key);
            String fileName = outputDir + File.separator + "decrypted_key_" + key + ".txt";
            fileHandler.writeFile(fileName, decrypted);
        }
    }
}