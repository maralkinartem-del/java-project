import java.io.IOException;

public class StatisticalAnalyzer {

    private final CaesarCipher cipher;
    private final FileHandler fileHandler;

    public StatisticalAnalyzer() {
        this.cipher = new CaesarCipher();
        this.fileHandler = new FileHandler();
    }

    public int guessKey(String text) {
        String alphabet = Alphabet.detect(text);
        char mostFreqEncrypted = Alphabet.findMostFrequentChar(text, alphabet);
        char presumedMostFreq = Alphabet.mostFrequentLetter(alphabet);

        int encryptedIndex = alphabet.indexOf(mostFreqEncrypted);
        int presumedIndex = alphabet.indexOf(presumedMostFreq);

        return (encryptedIndex - presumedIndex + alphabet.length()) % alphabet.length();
    }

    public void decrypt(String inputFile, String outputFile) throws IOException {
        String text = fileHandler.readFile(inputFile);
        int key = guessKey(text);

        System.out.println("Предполагаемый ключ: " + key);
        String decrypted = cipher.decrypt(text, key);
        fileHandler.writeFile(outputFile, decrypted);

        String alphabet = Alphabet.detect(text);
        System.out.println("Если текст нечитаемый, попробуйте ключи "
                + (key + 1) % alphabet.length() + " или "
                + (key - 1 + alphabet.length()) % alphabet.length());
    }
}