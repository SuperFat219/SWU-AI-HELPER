/**
 *  Instructions on how to run this exercise:
 *  1. Step RMI registry from the class directory for this exercise
 *     C:\exercise>start rmiregistry
 *  2. Register Server with RMIRegistry
 *     C:\exercise>start java Exercise43_2RegisterWithRMIServer
 *  3. Run a client (multiple times)
 *     C:\exercise>start java Exercise43_2RemoteInterfaceClient
 */
import java.rmi.registry.*;

public class Exercise43_2RegisterWithRMIServer {
  /** Main method */
  public static void main(String[] args) {
    try {
      Exercise43_2RemoteInterface obj = new Exercise43_2RemoteInterfaceImpl();
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("Exercise43_2RemoteInterfaceImpl", obj);
      System.out.println("Exercise42_2 RMI server " + obj + " registered");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
