import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16_18 extends JFrame {
  public Exercise16_18() {
    add(new MovingCircleByArrowKey());
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise16_18 frame = new Exercise16_18();
    frame.setTitle("Exercise16_18");
    frame.setSize(200, 100);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

// Displaying a moving circle
class MovingCircleByArrowKey extends JPanel {
  private int x = 20;
  private int y = 20;
  public static int RADIUS = 10;

  public MovingCircleByArrowKey() {   
    this.setFocusable(true);
    
    this.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: y += 10; break;
            case KeyEvent.VK_UP: y -= 10; break;
            case KeyEvent.VK_LEFT: x -= 10; break;
            case KeyEvent.VK_RIGHT: x += 10; break;
            default: ;
          }

          repaint();
      }
    });
  }

  /** Paint message */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
  }
}
