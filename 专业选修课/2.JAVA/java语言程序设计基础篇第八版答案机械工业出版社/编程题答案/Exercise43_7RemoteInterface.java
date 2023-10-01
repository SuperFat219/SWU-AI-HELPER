import java.rmi.*;

public interface Exercise43_7RemoteInterface extends Remote {
  // Connect clients to the server
  public boolean connect(Exercise43_7CallbackInterface client) throws
    RemoteException;

  // Receive messages from the client
  public void receive(java.lang.String message) throws
    RemoteException;
}
