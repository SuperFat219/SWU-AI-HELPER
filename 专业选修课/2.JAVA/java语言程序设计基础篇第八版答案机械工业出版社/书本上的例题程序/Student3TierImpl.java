import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;

public class Student3TierImpl extends UnicastRemoteObject
    implements StudentServerInterface {
  // Use prepared statement for querying DB
  private PreparedStatement pstmt;

  /** Constructs Student3TierImpl object and exports it on
   * default port.
   */
  public Student3TierImpl() throws RemoteException {
    initializeDB();
  }

  /** Constructs Student3TierImpl object and exports it on
   * specified port.
   * @param port The port for exporting
   */
  public Student3TierImpl(int port) throws RemoteException {
    super(port);
    initializeDB();
  }

  /** Load JDBC driver, establish connection and 
   * create statement */
  protected void initializeDB() {
    try {
      // Load the JDBC driver
      // Class.forName("oracle.jdbc.driver.OracleDriver");
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      System.out.println("Driver registered");

      // Establish connection
      /*Connection conn = DriverManager.getConnection
        ("jdbc:oracle:thin:@drake.armstrong.edu:1521:orcl",
          "scott", "tiger");  */
      Connection conn = DriverManager.getConnection
        ("jdbc:odbc:exampleMDBDataSource", "", "" );
      System.out.println("Database connected");

      // Create a prepared statement for querying DB
      pstmt = conn.prepareStatement(
        "select * from Scores where name = ?");
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
  }

  /** Return the score for specified the name
   * Return -1 if score is not found.
   */
  public double findScore(String name) throws RemoteException {
    double score = -1;
    try {
      // Set the specified name in the prepared statement
      pstmt.setString(1, name);

      // Execute the prepared statement
      ResultSet rs = pstmt.executeQuery();

      // Retrieve the score
      if (rs.next()) {
        if (rs.getBoolean(3))
          score = rs.getDouble(2);
      }
    }
    catch (SQLException ex) {
      System.out.println(ex);
    }

    System.out.println(name + "\'s score is " + score);
    return score;
  }
}
