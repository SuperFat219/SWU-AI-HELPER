import java.awt.*;
import javax.swing.*;

public class Exercise15_18 extends JFrame {
  public Exercise15_18() {
    setLayout(new GridLayout(1, 2));
    StillClock clock1 = new StillClock(4, 20, 45);
    StillClock clock2 = new StillClock(22, 46, 15);
    add(clock1);
    add(clock2);
  }

  public static void main(String[] args) {
    Exercise15_18 frame = new Exercise15_18();
    frame.setTitle("Exercise15_18");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
