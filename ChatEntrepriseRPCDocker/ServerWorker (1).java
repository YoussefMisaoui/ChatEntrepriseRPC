// Thread handling client connections and broadcasting
import java.io.*;
import java.net.*;

public class ServerWorker extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter out;

    public ServerWorker(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        try (
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
        ) {
            this.out = new PrintWriter(output, true);
            String clientMessage;

            while ((clientMessage = reader.readLine()) != null) {
                ChatServer.broadcast(clientMessage, null);
            }
        } catch (IOException ex) {
            System.out.println("Client disconnected");
        } finally {
            server.removeClient(null);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}
