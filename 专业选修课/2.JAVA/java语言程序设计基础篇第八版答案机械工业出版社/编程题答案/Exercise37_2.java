import javax.swing.*;

import java.sql.*;
import java.awt.*;

public class Exercise37_2 extends javax.swing.JApplet {
  String[] dataName;
  double[] data;

  /** Creates new form Exercise37_2 */
  public Exercise37_2() {
    try {
      // Declare driver and connection string
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
      String connectionString = "jdbc:odbc:exampleMDBDataSource";
      /* For Oracle
      String driver = "oracle.jdbc.driver.OracleDriver";
      String connectionString =
      "jdbc:oracle:thin:scott/tiger@liang.armstrong.edu:1521:oracle";
      */
      // Load the Oracle JDBC Thin driver
      Class.forName(driver);

      // Connect to the sample database
      Connection conn = DriverManager.getConnection(connectionString);
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(
        "select deptId, count(*) from Student where deptId is not null group by deptId;");

      // Count rows
      int count = 0;
      while (rs.next()) {
        count++;
      }

      dataName = new String[count];
      data = new double[count];

      // We have to obtain the result set again
      rs = stmt.executeQuery(
        "select deptId, count(*) from Student where deptId is not null group by deptId;");
      int i = 0;
      while (rs.next()) {
        dataName[i] = rs.getString(1);
        data[i] = rs.getInt(2);
        i++;
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    chartModel1 = new ChartModel();
    chartModel1.setChartData(dataName, data);
    pieChart1 = new PieChart();
    barChart1 = new BarChart();

    setLayout(new java.awt.GridLayout(1, 2));
    pieChart1.setModel(chartModel1);
    add(pieChart1);
    barChart1.setModel(chartModel1);
    add(barChart1);
  }

  /**Main method*/
  public static void main(String[] args) {
    Exercise37_2 applet = new Exercise37_2();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise37_2");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private PieChart pieChart1;
  private ChartModel chartModel1;
  private BarChart barChart1;
  // End of variables declaration//GEN-END:variables

}
