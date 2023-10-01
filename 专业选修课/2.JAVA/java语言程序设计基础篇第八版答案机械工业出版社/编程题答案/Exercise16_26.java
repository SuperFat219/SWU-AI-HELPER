import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16_26 extends JFrame {
  public Exercise16_26() {
    add(new MovingCircle());
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise16_26 frame = new Exercise16_26();
    frame.setTitle("Exercise16_26");
    frame.setSize(200, 100);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

// Displaying a moving circle
class MovingCircle extends JPanel {
  private int x = 20;
  private int y = 20;
  public static int RADIUS = 10;

  public MovingCircle() {    
    this.addMouseMotionListener(new MouseAdapter() {
      public void mouseDragged(MouseEvent e) { 
        if (insideCircle(e.getX(), e.getY())) {
          x = e.getX();
          y = e.getY();
          repaint();
        }
      }
    });
  }

  public boolean insideCircle(int px, int py) {
    return distance(px, py, x, y) < RADIUS;
  }

  public static double distance(
      double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }

  /** Paint message */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
  }
}
