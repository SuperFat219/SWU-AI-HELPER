import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Exercise44_12 extends JApplet {
  public Exercise44_12() {
    add(new ShapePanel());   
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D

      Stroke stroke = new BasicStroke(4.0f, BasicStroke.CAP_SQUARE,
          BasicStroke.JOIN_ROUND, 1.0f, new float[]{8}, 0);      
      g2d.setStroke(stroke);
      
      g2d.setPaint(new GradientPaint(10, 10, Color.RED, 40, 40,
          Color.YELLOW, true));

      g2d.translate(110, 110);
      g2d.fill(new Ellipse2D.Double(-50, -50, 100, 100));
      
      Shape line = new Line2D.Double(0, -55, 0, -85);
      for (int i = 0; i < 16; i++) { 
        g2d.draw(line);
        g2d.rotate(2 * Math.PI / 16);
      }
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    Exercise44_12 applet = new Exercise44_12();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise44_12");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(250, 250);
    frame.setVisible(true);
  }
}
