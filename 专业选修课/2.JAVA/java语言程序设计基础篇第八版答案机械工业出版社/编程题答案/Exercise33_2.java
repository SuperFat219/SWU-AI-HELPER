// Exercise33_2: Demonstrate GridLayout properties
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise33_2 extends JApplet {
  boolean isStandalone = false;
  JPanel jpComponents = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jpRowColumn = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextField jtfRows = new JTextField();
  JTextField jtfColumns = new JTextField();
  GridLayout gridLayout3 = new GridLayout();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JPanel jpGaps = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JTextField jtfHGap = new JTextField();
  JTextField jtfVGap = new JTextField();
  GridLayout gridLayout4 = new GridLayout();
  GridLayout gridLayout5 = new GridLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  GridLayout gridLayout = new GridLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;

  //Construct the applet
  public Exercise33_2() {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setSize(new Dimension(400,300));
    jPanel2.setLayout(gridLayout1);
    jLabel1.setText("Rows");
    jLabel2.setText("Columns");
    jPanel3.setLayout(gridLayout2);
    gridLayout2.setRows(2);
    jpRowColumn.setLayout(borderLayout1);
    jPanel4.setLayout(gridLayout3);
    gridLayout3.setRows(2);
    jLabel3.setText("HGap");
    jLabel4.setText("VGap");
    jpGaps.setLayout(borderLayout2);
    gridLayout4.setRows(2);
    gridLayout5.setRows(2);
    jPanel5.setLayout(gridLayout4);
    jPanel6.setLayout(gridLayout5);
    jpComponents.setLayout(gridLayout);
    jtfRows.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtfRows_actionPerformed(e);
      }
    });
    jtfColumns.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtfColumns_actionPerformed(e);
      }
    });
    jtfHGap.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtfHGap_actionPerformed(e);
      }
    });
    jtfVGap.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtfVGap_actionPerformed(e);
      }
    });
    jpComponents.setBorder(titledBorder1);
    jPanel2.setBorder(titledBorder2);
    titledBorder1.setTitle("Container of GridLayout");
    titledBorder2.setTitle("GridLayout Properties");
    add(jpComponents, BorderLayout.CENTER);
    add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jpRowColumn, null);
    jpRowColumn.add(jPanel3, BorderLayout.WEST);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jpRowColumn.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jtfRows, null);
    jPanel4.add(jtfColumns, null);
    jPanel2.add(jpGaps, null);
    jpGaps.add(jPanel5, BorderLayout.WEST);
    jPanel5.add(jLabel3, null);
    jPanel5.add(jLabel4, null);
    jpGaps.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jtfHGap, null);
    jPanel6.add(jtfVGap, null);

    // Add 15 buttons to jpComponents
    for (int i = 0; i < 15; i++)
      jpComponents.add(new JButton("Component " + i));
  }

  void jtfRows_actionPerformed(ActionEvent e) {
    int rows = new Integer(jtfRows.getText()).intValue();
    gridLayout.setRows(rows);
    jpComponents.revalidate();
  }

  void jtfColumns_actionPerformed(ActionEvent e) {
    int columns = new Integer(jtfColumns.getText()).intValue();
    gridLayout.setColumns(columns);
    jpComponents.revalidate();
  }

  void jtfHGap_actionPerformed(ActionEvent e) {
    int hgap = new Integer(jtfHGap.getText()).intValue();
    gridLayout.setHgap(hgap);
    jpComponents.revalidate();
  }

  void jtfVGap_actionPerformed(ActionEvent e) {
    int vgap = new Integer(jtfVGap.getText()).intValue();
    gridLayout.setVgap(vgap);
    jpComponents.revalidate();
  }

  public static void main(String[] args) {
    Exercise33_2 applet = new Exercise33_2();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("Exercise33_2");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
