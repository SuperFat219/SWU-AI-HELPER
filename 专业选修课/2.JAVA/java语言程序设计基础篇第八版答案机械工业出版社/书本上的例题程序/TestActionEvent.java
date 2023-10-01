import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestActionEvent extends JFrame {
  private JButton jbtOK = new JButton("OK");
  private JButton jbtCancel = new JButton("Cancel");

  public TestActionEvent() {
    setLayout(new FlowLayout());

    add(jbtOK);
    add(jbtCancel);

    jbtOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("The " + e.getActionCommand() + " button "
          + "is clicked at\n  " + new java.util.Date(e.getWhen()));
      }
    });

    jbtCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("The " + e.getActionCommand() + " button "
          + "is clicked at\n  " + new java.util.Date(e.getWhen()));
      }
    });
  }

  /** Main method */
  public static void main(String[] args) {
    TestActionEvent frame = new TestActionEvent();
    frame.setTitle("TestActionEvent");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(220, 80);
    frame.setVisible(true);
  }
}
