import java.awt.*;
import javax.swing.*;

public class Exercise15_6 extends JFrame {
  public static void main(String[] args) {
    JFrame frame = new Exercise15_6();
    frame.setSize(300, 300);
    frame.setTitle("Exercise15_6");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public Exercise15_6() {
    setLayout(new GridLayout(2, 2));
    add(new FigurePanel(FigurePanel.ARC));
    add(new FigurePanel(FigurePanel.POLYGON));
    add(new FigurePanel(FigurePanel.ARC, true));
    add(new FigurePanel(FigurePanel.POLYGON, true));
  }
}

class RectPanel extends JPanel {
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Set new color
    g.setColor(Color.red);

    // Draw a rectangle
    g.drawRect(5, 5, getWidth()/2-10, getHeight()/2-10);

    // Draw a rounded rectangle
    g.drawRoundRect(getWidth()/2+5, 5,
      getWidth()/2-10, getHeight()/2-10, 60, 30);

    // Change the color to cyan
    g.setColor(Color.cyan);

    // Draw a 3D rectangle
    g.fill3DRect(5, getHeight()/2+5, getWidth()/2-10,
      getHeight()/2-10, true);

    // Draw a raised 3D rectangle
    g.fill3DRect(getWidth()/2+5, getHeight()/2+5, getWidth()/2-10,
      getHeight()/2-10, false);
  }
}

class OvalsPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawOval(5, 5, getWidth()/2-10, getHeight()/2-10);
    g.setColor(Color.red);
    g.drawOval(getWidth()/2+5, 5, getWidth()/2-10, getHeight()/2-10);
    g.setColor(Color.yellow);
    g.fillOval(5, getHeight()/2+5, getWidth()/2-10,
      getHeight()/2-10);
    g.setColor(Color.orange);
    g.fillOval(getWidth()/2+5, getHeight()/2+5, getWidth()/2-10,
      getHeight()/2-10);
  }
}

class ArcsPanel extends JPanel {
  // Draw four blazes of a fan
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int xCenter = getSize().width/2;
    int yCenter = getSize().height/2;
    int radius =
      (int)(Math.min(getSize().width, getSize().height)*0.4);

    int x = xCenter - radius;
    int y = yCenter - radius;

    g.fillArc(x, y, 2*radius, 2*radius, 0, 30);
    g.fillArc(x, y, 2*radius, 2*radius, 90, 30);
    g.fillArc(x, y, 2*radius, 2*radius, 180, 30);
    g.fillArc(x, y, 2*radius, 2*radius, 270, 30);
  }
}

class PolygonsPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int xCenter = getSize().width/2;
    int yCenter = getSize().height/2;
    int radius =
      (int)(Math.min(getSize().width, getSize().height)*0.4);

    // Create a Polygon object
    Polygon polygon = new Polygon();

    // Add points to the polygon
    polygon.addPoint(xCenter + radius, yCenter);
    polygon.addPoint((int)(xCenter + radius*Math.cos(2*Math.PI/6)),
      (int)(yCenter - radius*Math.sin(2*Math.PI/6)));
    polygon.addPoint((int)(xCenter + radius*Math.cos(2*2*Math.PI/6)),
      (int)(yCenter - radius*Math.sin(2*2*Math.PI/6)));
    polygon.addPoint((int)(xCenter + radius*Math.cos(3*2*Math.PI/6)),
      (int)(yCenter - radius*Math.sin(3*2*Math.PI/6)));
    polygon.addPoint((int)(xCenter + radius*Math.cos(4*2*Math.PI/6)),
      (int)(yCenter - radius*Math.sin(4*2*Math.PI/6)));
    polygon.addPoint((int)(xCenter + radius*Math.cos(5*2*Math.PI/6)),
      (int)(yCenter - radius*Math.sin(5*2*Math.PI/6)));

    // Draw the polygon
    g.drawPolygon(polygon);
  }
}

