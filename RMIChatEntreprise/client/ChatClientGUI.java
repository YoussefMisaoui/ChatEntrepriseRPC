package client;

import shared.ChatService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.List;

public class ChatClientGUI extends JFrame {
    private ChatService chat;
    private JTextArea messageArea;
    private JTextField inputField;
    private String username;
    private Timer inactivityTimer;
    private final int TIMEOUT = 2 * 60 * 1000;

    public ChatClientGUI(ChatService chat, String username) {
        this.chat = chat;
        this.username = username;

        setTitle("Chat RMI - " + username);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        inputField = new JTextField();
        JButton sendButton = new JButton("Envoyer");

        ActionListener sendAction = e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                try {
                    chat.sendMessage(username, msg);
                    inputField.setText("");
                    refreshMessages();
                    resetInactivityTimer();
                } catch (RemoteException ex) {
                    showError("Erreur d'envoi");
                }
            }
        };

        inputField.addActionListener(sendAction);
        sendButton.addActionListener(sendAction);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                resetInactivityTimer();
            }
        });
        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                resetInactivityTimer();
            }
        });

        startInactivityTimer();
        refreshMessagesLoop();
    }

    private void refreshMessagesLoop() {
        new Timer(1000, e -> refreshMessages()).start();
    }

    private void refreshMessages() {
        try {
            List<String> messages = chat.getMessages();
            messageArea.setText(String.join("\n", messages));
        } catch (RemoteException e) {
            messageArea.setText("Erreur chargement messages");
        }
    }

    private void startInactivityTimer() {
        inactivityTimer = new Timer(TIMEOUT, e -> {
            JOptionPane.showMessageDialog(this, "Déconnecté pour inactivité.");
            System.exit(0);
        });
        inactivityTimer.setRepeats(false);
        inactivityTimer.start();
    }

    private void resetInactivityTimer() {
        if (inactivityTimer != null) inactivityTimer.restart();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}