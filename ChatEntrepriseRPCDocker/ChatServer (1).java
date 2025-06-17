// Java server code with message history logging
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("Serveur de chat en cours d'exécution sur le port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String msg, ClientHandler sender) {
        logMessage(msg);
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) client.send(msg);
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void logMessage(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter("history.log", true))) {
            out.println(message);
        } catch (IOException e) {
            System.out.println("Erreur d'enregistrement : " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String msg;
            while ((msg = in.readLine()) != null) {
                ChatServer.broadcast(msg, this);
            }
        } catch (IOException e) {
            System.out.println("Client déconnecté.");
        } finally {
            ChatServer.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {}
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}
