import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;
import com.sun.rowset.CachedRowSetImpl;
import javax.sql.RowSet;
import java.sql.*;

public class TableModelDemo extends JApplet {
  // Create table column names
  private String[] columnNames = {"Country", "Capital",
    "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] rowData = { {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true}, {"United Kingdom", "London", 60, true},
    {"Germany", "Berlin", 83, true}, {"France", "Paris", 60, true},
    {"Norway", "Oslo", 4.5, true}, {"India", "New Deli", 1046, true}
  };

  // Create a table model
  private RowSetTableModel tableModel = new RowSetTableModel();

  // Create a table
  private JTable jTable1 = new JTable(tableModel);

  // Create buttons
  private JButton jbtAddRow = new JButton("Add New Row");
  private JButton jbtAddColumn = new JButton("Add New Column");
  private JButton jbtDeleteRow = new JButton("Delete Selected Row");
  private JButton jbtDeleteColumn = new JButton(
    "Delete Selected Column");
  private JButton jbtSave = new JButton("Save");
  private JButton jbtClear = new JButton("Clear");
  private JButton jbtRestore = new JButton("Restore");

  // Create a combo box for selection modes
  private JComboBox jcboSelectionMode =
    new JComboBox(new String[] {"SINGLE_SELECTION",
    "SINGLE_INTERVAL_SELECTION", "MULTIPLE_INTERVAL_SELECTION"});

  // Create check boxes
  private JCheckBox jchkRowSelectionAllowed =
    new JCheckBox("RowSelectionAllowed", true);
  private JCheckBox jchkColumnSelectionAllowed =
    new JCheckBox("ColumnSelectionAllowed", false);

  RowSet rowSet;

  public TableModelDemo() {

    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      Class.forName("oracle.jdbc.driver.OracleDriver");
      System.out.println("Driver loaded");

      // Create a row set
      rowSet = new CachedRowSetImpl();

      // Set RowSet properties
//      rowSet.setUrl("jdbc:mysql://localhost/javabook");
      rowSet.setUrl("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl");
      rowSet.setUsername("scott");
      rowSet.setPassword("tiger");

      rowSet.setCommand("select * from StateCapital");

      rowSet.setConcurrency(ResultSet.CONCUR_UPDATABLE);
      rowSet.execute();
      tableModel.setRowSet(rowSet);
      rowSet.addRowSetListener(tableModel);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 2));
    panel1.add(jbtAddRow);
    panel1.add(jbtAddColumn);
    panel1.add(jbtDeleteRow);
    panel1.add(jbtDeleteColumn);

    JPanel panel2 = new JPanel();
    panel2.add(jbtSave);
    panel2.add(jbtClear);
    panel2.add(jbtRestore);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new BorderLayout(5, 0));
    panel3.add(new JLabel("Selection Mode"), BorderLayout.WEST);
    panel3.add(jcboSelectionMode, BorderLayout.CENTER);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel4.add(jchkRowSelectionAllowed);
    panel4.add(jchkColumnSelectionAllowed);

    JPanel panel5 = new JPanel();
    panel5.setLayout(new GridLayout(2, 1));
    panel5.add(panel3);
    panel5.add(panel4);

    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);
    panel6.add(panel2, BorderLayout.CENTER);

    add(panel5, BorderLayout.NORTH);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel6, BorderLayout.SOUTH);

    // Initialize table selection mode
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jbtAddRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rowSet.absolute(2);
          rowSet.moveToInsertRow();
          rowSet.updateString("state", "Georia");
          rowSet.updateString("capital", "Atlanta");
          rowSet.insertRow();
          ((CachedRowSetImpl)rowSet).acceptChanges();
          rowSet.moveToCurrentRow();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }


//        if (jTable1.getSelectedRow() >= 0)
//          tableModel.insertRow(jTable1.getSelectedRow(),
//            new java.util.Vector());
//        else
//          tableModel.addRow(new java.util.Vector());
      }
    });

    jbtAddColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
//        String name = JOptionPane.showInputDialog("New Column Name");
//        tableModel.addColumn(name, new java.util.Vector());
      }
    });

    jbtDeleteRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0) {
          try {
            rowSet.absolute(jTable1.getSelectedRow() + 1);
            rowSet.deleteRow();
            ((CachedRowSetImpl)rowSet).acceptChanges();
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });

    jbtDeleteColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedColumn() >= 0) {
          TableColumnModel columnModel = jTable1.getColumnModel();
          TableColumn tableColumn =
            columnModel.getColumn(jTable1.getSelectedColumn());
          columnModel.removeColumn(tableColumn);
        }
      }
    });

    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("tablemodel.dat"));
//          out.writeObject(tableModel.getDataVector());
          out.writeObject(getColumnNames());
          out.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    jbtClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
//        tableModel.setRowCount(0);
      }
    });

    jbtRestore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("tablemodel.dat"));
          Vector rowData = (Vector)in.readObject();
          Vector columnNames = (Vector)in.readObject();
//          tableModel.setDataVector(rowData, columnNames);
          in.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    jchkRowSelectionAllowed.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setRowSelectionAllowed(
          jchkRowSelectionAllowed.isSelected());
      }
    });

    jchkColumnSelectionAllowed.addActionListener(
      new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setColumnSelectionAllowed(
          jchkColumnSelectionAllowed.isSelected());
      }
    });

    jcboSelectionMode.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedItem =
          (String)jcboSelectionMode.getSelectedItem();

        if (selectedItem.equals("SINGLE_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        else if (selectedItem.equals("SINGLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        else if (selectedItem.equals("MULTIPLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      }
    });
  }

  private Vector getColumnNames() {
    Vector<String> columnNames = new Vector<String>();

    for (int i = 0; i < jTable1.getColumnCount(); i++)
      columnNames.add(jTable1.getColumnName(i));

    return columnNames;
  }

  public static void main(String[] args) {
    TableModelDemo applet = new TableModelDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TableModelDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(500, 220);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
