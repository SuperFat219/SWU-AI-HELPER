import javax.swing.*;
import java.awt.*;

public class Exercise20_20 extends JApplet {
  public Exercise20_20() {
    add(new CirclePanel());
  }

  static class CirclePanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      int radius = (int)(Math.min(getWidth(), getHeight()) * 0.9 / 2);
      int centerx = getWidth() / 2;
      int centery = getHeight() / 2;

      displayOvals(g, radius, centerx, centery);
    }

    private static void displayOvals(Graphics g, int radius,
        int centerx, int centery) {
      if (radius >= 5) {
        g.drawOval(centerx - radius, centery - radius, 2 * radius,
          2 * radius);

        displayOvals(g, radius - 5, centerx, centery);
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise20_20");
    Exercise20_20 applet = new Exercise20_20();
    frame.add(applet);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
