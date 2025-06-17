// Login authentication interface
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class MainFrame extends JFrame {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private JTextArea messageArea;
    private JTextField inputField;
    private String username;

    public MainFrame(String username) {
        this.username = username;
        setTitle("Chat - Utilisateur : " + username);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(messageArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> sendMessage());

        add(scroll, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        // ✅ Connexion au serveur RPC dans Docker
        try {
            // 🟢 Option 1 : si tu as lancé Docker avec --network host
            socket = new Socket("localhost", 8080);

            // 🔴 Option 2 : si tu n'utilises pas --network host, remplace par IP Docker comme "172.17.0.2"
            // socket = new Socket("172.17.0.2", 8080);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println(username); // envoyer le nom d'utilisateur au serveur

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        messageArea.append(msg + "\n");
                    }
                } catch (IOException e) {
                    showError("Connexion perdue avec le serveur.");
                }
            }).start();

        } catch (IOException e) {
            showError("Connexion refusée.\nVérifie que le serveur est actif.");
        }
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty()) {
            writer.println(msg);
            inputField.setText("");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
