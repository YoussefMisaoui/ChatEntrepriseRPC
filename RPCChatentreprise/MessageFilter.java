// Class to filter forbidden characters and limit message length
public class MessageFilter {
    private static final String[] forbidden = { "putain", "merde", "..." }; // Exemples

    public static boolean isValid(String msg) {
        for (String word : forbidden) {
            if (msg.toLowerCase().contains(word)) return false;
        }
        return true;
    }
}
