package server;

import shared.ChatService;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private List<String> messages = new ArrayList<>();
    private Map<String, String> users = Map.of(
        "admin", "admin123",
        "user1", "pass1",
        "user2", "pass2"
    );

    public ChatServiceImpl() throws RemoteException {}

    @Override
    public boolean login(String username, String password) throws RemoteException {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    @Override
    public synchronized void sendMessage(String user, String msg) throws RemoteException {
        if (msg.length() > 200 || msg.toLowerCase().contains("putain")) return;
        messages.add(user + ": " + msg);
    }

    @Override
    public synchronized List<String> getMessages() throws RemoteException {
        return new ArrayList<>(messages);
    }
}