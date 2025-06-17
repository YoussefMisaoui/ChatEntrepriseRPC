// Login authentication interface
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static final HashMap<String, String> users = new HashMap<>();

    public LoginFrame() {
        // Utilisateurs autorisés (username => password)
        users.put("admin", "admin123");
        users.put("user1", "pass1");
        users.put("user2", "pass2");

        setTitle("Connexion");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Entrer");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                dispose();
                new MainFrame(username).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Nom d'utilisateur :"));
        panel.add(usernameField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);
    }
}
