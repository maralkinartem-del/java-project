public class CaesarCipher {

    public String encrypt(String text, int key) {
        return shift(text, key);
    }

    public String decrypt(String text, int key) {
        return shift(text, -key);
    }

    private String shift(String text, int shift) {
        StringBuilder result = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String alphabet = Alphabet.forChar(ch);

            if (alphabet != null) {
                int originalIndex = alphabet.indexOf(Character.toUpperCase(ch));
                if (originalIndex != -1) {
                    int newIndex = (originalIndex + shift) % alphabet.length();
                    if (newIndex < 0) {
                        newIndex += alphabet.length();
                    }
                    char newChar = alphabet.charAt(newIndex);
                    result.append(Character.isLowerCase(ch)
                            ? Character.toLowerCase(newChar)
                            : newChar);
                } else {
                    result.append(ch);
                }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}