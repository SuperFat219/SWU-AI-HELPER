import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16_6 extends JFrame {
  private DisplayPanel panel = new DisplayPanel();

  public Exercise16_6() {
    add(panel, BorderLayout.CENTER);
    panel.setFocusable(true);
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new Exercise16_6();
    frame.setTitle("Exercise16_6");
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  class DisplayPanel extends MessagePanel {
    private String m1 = "Java is fun";
    private String m2 = "Java is powerful";
    private boolean isM1;

    public DisplayPanel() {
      setCentered(true);
      this.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (isM1) setMessage(m1);
          else setMessage(m2);

          isM1 = !isM1;
        }
      });
    }
  }
}
