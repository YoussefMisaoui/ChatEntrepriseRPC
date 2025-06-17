// Swing GUI with colored interface and notification
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MainFrame extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;
    private Timer inactivityTimer;

    public MainFrame(String username) {
        setTitle("Chat - " + username);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(240, 240, 240));
        JScrollPane scroll = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty() && msg.length() < 200 && MessageFilter.isValid(msg)) {
                out.println(username + ": " + msg);
                chatArea.append("Moi: " + msg + "\n");
                inputField.setText("");
                resetInactivityTimer();
            }
        });

        add(scroll, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        chatArea.append(msg + "\n");
                    }
                } catch (IOException ignored) {}
            }).start();

            // Charger l'historique
            try (BufferedReader reader = new BufferedReader(new FileReader("history.log"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    chatArea.append(line + "\n");
                }
            } catch (IOException ignored) {}

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Connexion refusée.");
            System.exit(0);
        }

        initInactivityTimer();
    }

    private void initInactivityTimer() {
        inactivityTimer = new Timer(120000, e -> {
            JOptionPane.showMessageDialog(this, "Déconnecté pour inactivité.");
            System.exit(0);
        });
        inactivityTimer.setRepeats(false);
        inactivityTimer.start();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { resetInactivityTimer(); }
        });
        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { resetInactivityTimer(); }
        });
    }

    private void resetInactivityTimer() {
        if (inactivityTimer != null) inactivityTimer.restart();
    }
}
