import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16_16 extends JFrame {
  public Exercise16_16() {
    add(new FlashLabel("Welcome to Java"));
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new Exercise16_16();
    frame.setTitle("Exercise16_16");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the frame
    frame.setSize(200, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class FlashLabel extends JPanel {
    private boolean show = true;
    private Timer timer = new Timer(200, 
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          repaint();
        }
      });

    String label = "Welcome to Java";

    public FlashLabel(String label) {
      this.label = label;
      timer.start();
    }

    public void setLabel(String label) {
      this.label = label;
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (show)
        g.drawString(label, 20, 20);
      show = !show;
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 50);
    }
  }
}
