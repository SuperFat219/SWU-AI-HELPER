import java.awt.*;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class Exercise38_6 extends JApplet {
  boolean isStandalone = false;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();

  MyTableModel tableModel = new MyTableModel();

  /**Get a parameter value*/
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  /**Construct the applet*/
  public Exercise38_6() {
  }
  /**Initialize the applet*/
  public void init() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Component initialization*/
  private void jbInit() throws Exception {
    this.setSize(new Dimension(400,300));
    this.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
    jTable1.setModel(tableModel);
    jTable1.setRowHeight(50);

    tableModel.setColumnIdentifiers(new Object[]{"Name", "Flag",
      "Description"});

    // Set custom renderer for displaying images
    TableColumn flag = jTable1.getColumn("Flag");
    flag.setCellRenderer(new MyImageCellRenderer());

    getData();
  }

  private void getData() throws Exception {
    // Load the driver
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded");

    // Establish connection
//    Connection connection = DriverManager.getConnection
//      ("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl",
//       "scott", "tiger");
    Connection connection = DriverManager.getConnection
      ("jdbc:mysql://localhost/javabook", "scott", "tiger");

    System.out.println("Database connected");

    // Create a statement for static SQL
    Statement stmt = connection.createStatement();

    ResultSet rs = stmt.executeQuery("select * from Country");

    Vector tableVector = new Vector();

    while (rs.next()) {
      Vector vector = new Vector();
      vector.add(rs.getString(1));

      Blob blob = rs.getBlob(2);
      ImageIcon imageIcon = new ImageIcon(
        blob.getBytes(1, (int)blob.length()));

      vector.add(imageIcon);
      vector.add(rs.getString(3));
      tableModel.addRow(vector);
    }
  }

  /**Main method*/
  public static void main(String[] args) {
    Exercise38_6 applet = new Exercise38_6();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setTitle("Exercise38_6");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class MyImageCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
  /** Override this method in DefaultTableCellRenderer */
  public Component getTableCellRendererComponent
    (JTable table, Object value, boolean isSelected,
     boolean isFocused, int row, int column) {
    Image image = ((ImageIcon)value).getImage();
    ImageViewer imageViewer = new ImageViewer(image);
    return imageViewer;
  }
}
