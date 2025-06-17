package shared;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
    boolean login(String username, String password) throws RemoteException;
    void sendMessage(String user, String msg) throws RemoteException;
    List<String> getMessages() throws RemoteException;
}