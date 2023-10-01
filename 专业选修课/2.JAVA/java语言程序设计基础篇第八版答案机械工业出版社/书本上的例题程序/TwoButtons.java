import javax.swing.*;
import java.awt.*;

public class TwoButtons {
  public static void main(String[] args) {
    JFrame frame = new JFrame();

    // Set FlowLayout for the frame
    FlowLayout layout = new FlowLayout();
    frame.setLayout(layout);

    // Add two buttons to frame
    JButton jbtOK = new JButton("OK");
    JButton jbtCancel = new JButton("Cancel");
    frame.add(jbtOK);
    frame.add(jbtCancel);

    frame.setTitle("Window 1");
    frame.setSize(200, 150);
    frame.setLocation(200, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
