import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class Exercise43_7CallbackInterfaceImpl extends
     UnicastRemoteObject implements Exercise43_7CallbackInterface {
  // Client to be called by the server through callback
  private Exercise43_7Client thisClient;

  public Exercise43_7CallbackInterfaceImpl(Object client) throws RemoteException {
    thisClient = (Exercise43_7Client)client;
  }

  public void receive(String message) throws RemoteException {
    thisClient.getMessage(message);
  }
}
