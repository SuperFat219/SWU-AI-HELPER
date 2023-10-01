import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;

public class Exercise36_2 extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Country", "Capital", "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] rowData = {
    {"USA", "Washington DC", new Integer(280), new Boolean(true)},
    {"Canada", "Ottawa", new Integer(32), new Boolean(true)},
    {"United Kingdom", "London", new Integer(60), new Boolean(true)},
    {"Germany", "Berlin", new Integer(83), new Boolean(true)},
    {"France", "Paris", new Integer(60), new Boolean(true)},
    {"Norway", "Oslo", new Double(4.5), new Boolean(true)},
    {"India", "New Deli", new Integer(1046), new Boolean(true)}
  };

  // Create a table model
  private DefaultTableModel tableModel = new DefaultTableModel(
     rowData, columnNames);

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

  public Exercise36_2() {
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
        if (jTable1.getSelectedRow() >= 0)
          tableModel.insertRow(jTable1.getSelectedRow(),
            new java.util.Vector());
        else
          tableModel.addRow(new java.util.Vector());
      }
    });

    jbtAddColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("New Column Name");
        tableModel.addColumn(name, new java.util.Vector());
      }
    });

    jTable1.addKeyListener(new KeyAdapter() {
       public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_DELETE) {
           int[] selectedRows = jTable1.getSelectedRows();
           for (int i = selectedRows.length - 1; i >= 0; i--)
             tableModel.removeRow(selectedRows[i]);

         }
       }
    });

    jbtDeleteRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int[] selectedRows = jTable1.getSelectedRows();
        int[] selectedColumns = jTable1.getSelectedColumns();

        for (int i = selectedRows.length - 1; i >= 0; i--)
          tableModel.removeRow(selectedRows[i]);

        TableColumnModel columnModel = jTable1.getColumnModel();

        for (int j = selectedColumns.length - 1; j >= 0; j--) {
          TableColumn tableColumn =
              columnModel.getColumn(selectedColumns[j]);
          columnModel.removeColumn(tableColumn);
        }
      }
    });

    jbtDeleteColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int[] selectedColumns = jTable1.getSelectedColumns();
        TableColumnModel columnModel = jTable1.getColumnModel();

        for (int j = selectedColumns.length - 1; j >= 0; j--) {
          TableColumn tableColumn =
              columnModel.getColumn(selectedColumns[j]);
          columnModel.removeColumn(tableColumn);
        }
      }
    });

    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("tablemodel.dat"));
          out.writeObject(tableModel.getDataVector());
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
        tableModel.setRowCount(0);
      }
    });

    jbtRestore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("tablemodel.dat"));
          Vector rowData = (Vector)in.readObject();
          Vector columnNames = (Vector)in.readObject();
          tableModel.setDataVector(rowData, columnNames);
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
          (String) jcboSelectionMode.getSelectedItem();

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
    Vector columnNames = new Vector();

    for (int i = 0; i < jTable1.getColumnCount(); i++)
      columnNames.add(jTable1.getColumnName(i));

    return columnNames;
  }

  public static void main(String[] args) {
    Exercise36_2 applet = new Exercise36_2();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise36_2");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(500, 220);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
