package server;

import shared.ChatService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            ChatService service = new ChatServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", service);
            System.out.println("✅ Serveur RMI lancé sur le port 1099");
        } catch (Exception e) {
            System.err.println("❌ Erreur serveur RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}