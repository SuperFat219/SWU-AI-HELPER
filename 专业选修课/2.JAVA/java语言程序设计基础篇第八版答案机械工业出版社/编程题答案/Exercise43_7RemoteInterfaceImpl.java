/**
 *  Instructions on how to run this exercise:
 *  1. Step RMI registry from the class directory for this exercise
 *     C:\exercise>start rmiregistry
 *  2. Register Server with RMIRegistry
 *     C:\exercise>start java Exercise43_7RemoteInterfaceImpl
 *  3. Run a client (multiple times)
 *     C:\exercise>start java Exercise43_7Client
 */

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;

public class Exercise43_7RemoteInterfaceImpl extends
  UnicastRemoteObject implements Exercise43_7RemoteInterface {

  // Stores callbacks for sending chat to all cients
  private ArrayList callBacks = new ArrayList();

  public Exercise43_7RemoteInterfaceImpl() throws RemoteException {
    super();
  }

  public Exercise43_7RemoteInterfaceImpl(int port) throws
    RemoteException {
    super(port);
  }

  public boolean connect(Exercise43_7CallbackInterface client) throws
    RemoteException {
    callBacks.add(client);
    client.receive("Connected to RMI Chat Server");
    return true;
  }

  public void receive(String message) throws RemoteException {
    // Log chat to the server
    System.out.println(message);

    // Send to all clients
    sendToAll(message);
  }

  // Used to send message to all clients
  void sendToAll(String message) {
    // Go through arraylist and send message to each client
    for (int i = 0; i < callBacks.size(); i++) {
      Exercise43_7CallbackInterface client = (
        Exercise43_7CallbackInterface)callBacks.get(i);
      try {
        // Write message
        client.receive(message);
      }
      catch (ConnectException ex) {
        // Removes client if there is a ConnectException
        callBacks.remove(client);
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
  }

  public static void main(String[] args) {
    try {
      Exercise43_7RemoteInterface server = new
        Exercise43_7RemoteInterfaceImpl();
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("ChatServer", server);
      System.out.println("ChatServer " + server + " registered");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
