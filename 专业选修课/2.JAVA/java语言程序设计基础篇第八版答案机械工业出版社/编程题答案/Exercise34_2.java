import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise34_2 extends JApplet implements ActionListener {
  // Text fields for Number 1, Number 2, and Result
  private JTextField jtfNum1, jtfNum2, jtfResult;

  // Buttons "Add", "Subtract", "Multiply" and "Divide"
  private JButton jbtAdd, jbtSub, jbtMul, jbtDiv;

  // Menu items "Add", "Subtract", "Multiply","Divide" and "Close"
  private JMenuItem jmiAdd, jmiSub, jmiMul, jmiDiv, jmiClose;

  private JPopupMenu jPopupMenu1 = new JPopupMenu();

  public Exercise34_2() {
    // Add menu "Operation" to menu bar
    JMenu operationMenu = new JMenu("Operation");
    operationMenu.setMnemonic('O');
    jPopupMenu1.add(operationMenu);

    // Add menu "Exit" in menu bar
    JMenu exitMenu = new JMenu("Exit");
    exitMenu.setMnemonic('E');
    jPopupMenu1.add(exitMenu);

    // Add menu items with mnemonics to menu "Operation"
    operationMenu.add(jmiAdd= new JMenuItem("Add", 'A'));
    operationMenu.add(jmiSub = new JMenuItem("Subtract", 'S'));
    operationMenu.add(jmiMul = new JMenuItem("Multiply", 'M'));
    operationMenu.add(jmiDiv = new JMenuItem("Divide", 'D'));
    exitMenu.add(jmiClose = new JMenuItem("Close", 'C'));

    // Set keyboard accelerators
    jmiAdd.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    jmiSub.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    jmiMul.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    jmiDiv.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

    // Panel p1 to hold text fields and labels
    JPanel p1 = new JPanel();
    p1.setLayout(new FlowLayout());
    p1.add(new JLabel("Number 1"));
    p1.add(jtfNum1 = new JTextField(3));
    p1.add(new JLabel("Number 2"));
    p1.add(jtfNum2 = new JTextField(3));
    p1.add(new JLabel("Result"));
    p1.add(jtfResult = new JTextField(4));
    jtfResult.setEditable(false);

    // Panel p2 to hold buttons
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.add(jbtAdd = new JButton("Add"));
    p2.add(jbtSub = new JButton("Subtract"));
    p2.add(jbtMul = new JButton("Multiply"));
    p2.add(jbtDiv = new JButton("Divide"));

    add(p1, BorderLayout.NORTH);
    add(p2, BorderLayout.SOUTH);

    // Register listeners
    jbtAdd.addActionListener(this);
    jbtSub.addActionListener(this);
    jbtMul.addActionListener(this);
    jbtDiv.addActionListener(this);
    jmiAdd.addActionListener(this);
    jmiSub.addActionListener(this);
    jmiMul.addActionListener(this);
    jmiDiv.addActionListener(this);
    jmiClose.addActionListener(this);

    p1.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) { // For Motif
        showPopup(e);
      }

      public void mouseReleased(MouseEvent e) { // For Windows
        showPopup(e);
      }
    });
  }

  /** Display popup menu when triggered */
  private void showPopup(java.awt.event.MouseEvent evt) {
    if (evt.isPopupTrigger())
      jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
  }

  /** Handle ActionEvent from buttons and menu items */
  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();

    // Handle button events
    if (e.getSource() instanceof JButton) {
      if ("Add".equals(actionCommand))
        calculate('+');
      else if ("Subtract".equals(actionCommand))
        calculate('-');
      else if ("Multiply".equals(actionCommand))
        calculate('*');
      else if ("Divide".equals(actionCommand))
        calculate('/');
    }
    else if (e.getSource() instanceof JMenuItem) {
      // Handle menu item events
      if ("Add".equals(actionCommand))
        calculate('+');
      else if ("Subtract".equals(actionCommand))
        calculate('-');
      else if ("Multiply".equals(actionCommand))
        calculate('*');
      else if ("Divide".equals(actionCommand))
        calculate('/');
      else if ("Close".equals(actionCommand))
        System.exit(0);
    }
  }

  /** Calculate and show the result in jtfResult */
  private void calculate(char operator) {
    // Obtain Number 1 and Number 2
    int num1 = (Integer.parseInt(jtfNum1.getText().trim()));
    int num2 = (Integer.parseInt(jtfNum2.getText().trim()));
    int result = 0;

    // Perform selected operation
    switch (operator) {
      case '+': result = num1 + num2;
                break;
      case '-': result = num1 - num2;
                break;
      case '*': result = num1 * num2;
                break;
      case '/': result = num1 / num2;
    }

    // Set result in jtfResult
    jtfResult.setText(String.valueOf(result));
  }


  public static void main(String[] args) {
    Exercise34_2 applet = new Exercise34_2();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise34_2");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
