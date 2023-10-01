import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class StudentServerInterfaceImpl 
    extends UnicastRemoteObject 
    implements StudentServerInterface {
  // Stores scores in a map indexed by name
  private HashMap<String, Double> scores = 
    new HashMap<String, Double>();

  public StudentServerInterfaceImpl() throws RemoteException {
    initializeStudent();
  }

  /** Initialize student information */
  protected void initializeStudent() {
    scores.put("John", new Double(90.5));
    scores.put("Michael", new Double(100));
    scores.put("Michelle", new Double(98.5));
  }

  /** Implement the findScore method from the 
   * Student interface */
  public double findScore(String name) throws RemoteException {
    Double d = (Double)scores.get(name);

    if (d == null) {
      System.out.println("Student " + name + " is not found ");
      return -1;
    }
    else {
      System.out.println("Student " + name + "\'s score is "
        + d.doubleValue());
      return d.doubleValue();
    }
  }
}
