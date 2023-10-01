import javax.swing.*;
import java.awt.event.*;

public class SimpleEventDemoInnerClass extends JFrame {
  public SimpleEventDemoInnerClass() {
    // Create two buttons
    JButton jbtOK = new JButton("OK");
    JButton jbtCancel = new JButton("Cancel");

    // Create a panel to hold buttons
    JPanel panel = new JPanel();
    panel.add(jbtOK);
    panel.add(jbtCancel);

    add(panel); // Add panel to the frame

    // Register listeners
    ListenerClass listener = new ListenerClass();
    jbtOK.addActionListener(listener);
    jbtCancel.addActionListener(listener);
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new SimpleEventDemoInnerClass();
    frame.setTitle("SimpleEventDemoInnerClass");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(220, 80);
    frame.setVisible(true);
  }

  private static class ListenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("The " + e.getActionCommand() + " button "
        + "is clicked at\n  " + new java.util.Date(e.getWhen()));
    }
  }
}
