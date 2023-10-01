import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Exercise17_14 extends JFrame {
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jList1 = new JList();

  private JPanel jpSelectionMode = new JPanel();
  private JComboBox jcboSelectionMode = new JComboBox();
  private JLabel jlblStatus = new JLabel();

  private JLabel jLabel1 = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();

  // Create an array of strings for country names
  private String[] countries = {"United States", "United Kingdom", "China",
    "Germany", "France", "Canada"};

  //Construct the applet
  public Exercise17_14() {
    this.setSize(new Dimension(400, 300));

    jList1.setListData(countries);
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jList1_valueChanged(e);
      }
    });
    jpSelectionMode.setLayout(borderLayout1);
    jcboSelectionMode.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jcboSelectionMode_actionPerformed(e);
      }
    });
    jlblStatus.setText("Status");
    jLabel1.setText("Choose Selection Mode");
    add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jList1, null);
    add(jpSelectionMode, BorderLayout.NORTH);
    jpSelectionMode.add(jLabel1, BorderLayout.WEST);
    jpSelectionMode.add(jcboSelectionMode, BorderLayout.CENTER);
    add(jlblStatus, BorderLayout.SOUTH);

    // Add selection modes to the combo box
    jcboSelectionMode.addItem("SINGLE_SELECTION");
    jcboSelectionMode.addItem("SINGLE_INTERVAL_SELECTION");
    jcboSelectionMode.addItem("MULTIPLE_INTERVAL_SELECTION");
  }

  //Main method
  public static void main(String[] args) {
    Exercise17_14 frame = new Exercise17_14();
    frame.setTitle("Exercise17_14");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Handler for selecting items from a combo box
  void jcboSelectionMode_actionPerformed(ActionEvent e) {
    String selectedMode =
    (String)jcboSelectionMode.getSelectedItem();

    if (selectedMode.equals("SINGLE_SELECTION"))
      jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    else if (selectedMode.equals("SINGLE_INTERVAL_SELECTION"))
      jList1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    if (selectedMode.equals("MULTIPLE_INTERVAL_SELECTION"))
      jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  }

  // Handler for item selection in the list
  void jList1_valueChanged(ListSelectionEvent e) {
    int[] indices = jList1.getSelectedIndices();
    Object[] selectedItems = jList1.getSelectedValues();
    String display = "";

    for (int i = 0; i < indices.length; i++) {
      display += (String)selectedItems[i] + " ";
    }

    jlblStatus.setText(display);
  }
}
