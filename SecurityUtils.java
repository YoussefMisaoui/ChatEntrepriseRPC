// Utilities for security features (placeholder)
public class SecurityUtils {
    // Placeholder pour ajouter des fonctions de hachage ou de vérification plus tard
    public static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode()); // Simulé (non sécurisé !)
    }

    public static boolean validatePassword(String input, String storedHash) {
        return hashPassword(input).equals(storedHash);
    }
}
