import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner;
    private final CaesarCipher cipher;
    private final FileHandler fileHandler;
    private final Validator validator;
    private final BruteForceDecryptor bruteForce;
    private final StatisticalAnalyzer analyzer;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.cipher = new CaesarCipher();
        this.fileHandler = new FileHandler();
        this.validator = new Validator();
        this.bruteForce = new BruteForceDecryptor();
        this.analyzer = new StatisticalAnalyzer();
    }

    public void start() {
        System.out.println("=== Шифр Цезаря ===");
        while (true) {
            printMenu();
            int choice = readInt("Выберите действие: ");
            switch (choice) {
                case 1 -> handleEncrypt();
                case 2 -> handleDecryptWithKey();
                case 3 -> handleBruteForce();
                case 4 -> handleStatisticalAnalysis();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный пункт меню.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с ключом");
        System.out.println("3. Расшифровать перебором (brute force)");
        System.out.println("4. Расшифровать статистическим анализом");
        System.out.println("0. Выход");
    }

    private void handleEncrypt() {
        String inputFile = readFileName("Введите путь к исходному файлу: ");
        if (!validateFile(inputFile)) return;

        String outputFile = readFileName("Введите путь для сохранения зашифрованного файла: ");
        int key = readInt("Введите ключ сдвига: ");

        try {
            String text = fileHandler.readFile(inputFile);
            String encrypted = cipher.encrypt(text, key);
            fileHandler.writeFile(outputFile, encrypted);
            System.out.println("Файл успешно зашифрован: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleDecryptWithKey() {
        String inputFile = readFileName("Введите путь к зашифрованному файлу: ");
        if (!validateFile(inputFile)) return;

        String outputFile = readFileName("Введите путь для сохранения расшифрованного файла: ");
        int key = readInt("Введите ключ сдвига: ");

        try {
            String text = fileHandler.readFile(inputFile);
            String decrypted = cipher.decrypt(text, key);
            fileHandler.writeFile(outputFile, decrypted);
            System.out.println("Файл успешно расшифрован: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleBruteForce() {
        String inputFile = readFileName("Введите путь к зашифрованному файлу: ");
        if (!validateFile(inputFile)) return;

        String outputDir = readFileName("Введите папку для сохранения вариантов: ");

        try {
            bruteForce.decrypt(inputFile, outputDir);
            System.out.println("Готово! Все варианты сохранены в: " + outputDir);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleStatisticalAnalysis() {
        String inputFile = readFileName("Введите путь к зашифрованному файлу: ");
        if (!validateFile(inputFile)) return;

        String outputFile = readFileName("Введите путь для сохранения расшифрованного файла: ");

        try {
            analyzer.decrypt(inputFile, outputFile);
            System.out.println("Файл расшифрован и сохранён: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private boolean validateFile(String filePath) {
        if (!validator.fileExists(filePath)) {
            System.out.println("Ошибка: файл не существует или недоступен.");
            return false;
        }
        return true;
    }

    private String readFileName(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return validator.parseKey(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
}