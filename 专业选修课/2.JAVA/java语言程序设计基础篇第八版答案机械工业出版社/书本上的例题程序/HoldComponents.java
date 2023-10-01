import javax.swing.*;

public class HoldComponents {
  public static void main(String[] args) {
    JFrame frame = new JFrame();

    // Add a button to frame
    JButton jbtOK = new JButton("OK");
    frame.add(jbtOK);

    frame.setTitle("Window 1");
    frame.setSize(200, 150);
    frame.setLocation(200, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
