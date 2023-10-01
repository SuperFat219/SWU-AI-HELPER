import java.rmi.*;

public interface Exercise43_7CallbackInterface extends Remote {
    // Server sends messages to the clients
    public void receive(java.lang.String message) throws RemoteException;
}
