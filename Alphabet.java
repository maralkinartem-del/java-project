import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    public static final String RU = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String EN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String detect(String text) {
        for (char ch : text.toCharArray()) {
            String alphabet = forChar(ch);
            if (alphabet != null) {
                return alphabet;
            }
        }
        return RU; // По умолчанию русский
    }

    public static String forChar(char ch) {
        if (Character.isLetter(ch)) {
            if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                return RU;
            } else if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
                return EN;
            }
        }
        return null;
    }

    public static boolean isRussian(String alphabet) {
        return RU.equals(alphabet);
    }

    public static char mostFrequentLetter(String alphabet) {
        return isRussian(alphabet) ? 'Е' : 'E';
    }

    public static char findMostFrequentChar(String text, String alphabet) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray()) {
            char upper = Character.toUpperCase(ch);
            if (alphabet.indexOf(upper) != -1) {
                freq.put(upper, freq.getOrDefault(upper, 0) + 1);
            }
        }
        if (freq.isEmpty()) {
            return alphabet.charAt(0);
        }
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}