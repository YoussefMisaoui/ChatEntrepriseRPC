package client;

import shared.ChatService;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatService chat = (ChatService) registry.lookup("ChatService");

            String username = JOptionPane.showInputDialog("Nom d'utilisateur :");
            String password = JOptionPane.showInputDialog("Mot de passe :");

            if (chat.login(username, password)) {
                SwingUtilities.invokeLater(() -> {
                    new ChatClientGUI(chat, username).setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Identifiants incorrects.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connexion au serveur échouée.");
            e.printStackTrace();
        }
    }
}