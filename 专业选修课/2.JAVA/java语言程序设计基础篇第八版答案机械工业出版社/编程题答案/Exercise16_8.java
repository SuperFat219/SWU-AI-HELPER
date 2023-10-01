import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise16_8 extends JFrame {
  public Exercise16_8() {
    add(new DisplayCoordinatePanelONE());
  }

  /**Main method*/
  public static void main(String[] args) {
    // Create a frame
    Exercise16_8 frame = new Exercise16_8();
    frame.setTitle("Exercise16_8");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

/* Version 1: Display mouse position when the mouse is clicked */
class DisplayCoordinatePanelONE extends JPanel {
  // Point is a Java object for representing points in a plane.
  // p.x and p.y are the x and y coordinates.
  private Point p = new Point(0, 0);

  public DisplayCoordinatePanelONE() {
    addMouseListener(new MouseAdapter() {
      // When mouse is clicked, the mouse pointer location is
      // captured in the point p.
      public void mouseClicked(MouseEvent e) {
        p.x = e.getX();
        p.y = e.getY();
        repaint();
      }
    });
  }

  // Draw a small solid square around the point
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawString("(" + p.x + ", " + p.y + ")", p.x, p.y);
  }
}

/* Version 2: Display mouse position when the mouse is pressed.
   The display disappears when the mouse is released. */
class DisplayCoordinatePanelTWO extends JPanel {
  // Point is a Java object for representing points in a plane.
  // p.x and p.y are the x and y coordinates.
  private Point p = new Point(0, 0);
  private boolean pressed = false;

  public DisplayCoordinatePanelTWO() {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        p.x = e.getX();
        p.y = e.getY();
        pressed = true;
        repaint();
      }
      
      public void mouseReleased(MouseEvent e) {
    	pressed = false;
    	repaint();
      }
    });
  }

  // Draw a small solid square around the point
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (pressed) {
      g.drawString("(" + p.x + ", " + p.y + ")", p.x, p.y);
    }
  }
}
