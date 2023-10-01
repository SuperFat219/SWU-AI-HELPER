import java.awt.*;
import javax.swing.*;

public class Exercise15_12 extends JFrame {
  public Exercise15_12() {
    setLayout(new BorderLayout());
    add(new DrawSine(), BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    Exercise15_12 frame = new Exercise15_12();
    frame.setSize(400, 300);
    frame.setTitle("Exercise15_12");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Inner class
  class DrawSine extends JPanel {
    double f(double x) {
      return Math.sin(x);
    }

    // Draw the function
    public void drawFunction() {
      repaint();
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawLine(10, 100, 380, 100);
      g.drawLine(200, 30, 200, 190);

      // Draw arrows
      g.drawLine(380, 100, 370, 90);
      g.drawLine(380, 100, 370, 110);
      g.drawLine(200, 30, 190, 40);
      g.drawLine(200, 30, 210, 40);

      // Draw x, y axises
      g.drawString("X", 360, 80);
      g.drawString("Y", 220, 40);

      // Draw function
      Polygon p = new Polygon();

      for (int x = -100; x <= 100; x++) {
        p.addPoint(x + 200, 100 - (int)(50 * f((x / 100.0) * 2 * Math.PI)));
      }

      g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
      g.drawString("-2\u03c0", 95, 115);
      g.drawString("2\u03c0", 305, 115);
      g.drawString("0", 200, 115);
    }
  }
}

