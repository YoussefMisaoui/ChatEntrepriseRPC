// Placeholder for test client
public class TestClient {
    public static void main(String[] args) {
        // Test automatisé simple
        try {
            java.net.Socket socket = new java.net.Socket("localhost", 12345);
            java.io.PrintWriter out = new java.io.PrintWriter(socket.getOutputStream(), true);
            out.println("TEST BOT: Bonjour à tous !");
            socket.close();
        } catch (Exception e) {
            System.out.println("Erreur test client: " + e.getMessage());
        }
    }
}
