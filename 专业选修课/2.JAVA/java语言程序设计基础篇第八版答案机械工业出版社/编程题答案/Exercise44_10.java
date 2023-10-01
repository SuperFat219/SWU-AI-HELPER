import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Exercise44_10 extends JApplet {
  public Exercise44_10() {
    add(new ShapePanel());   
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D

      g2d.translate(50, 160);
      
      g2d.setFont(new Font("Serif", Font.PLAIN, 25));
      g2d.draw(new Line2D.Double(-20, 0, 420, 0));      
      g2d.draw(new Line2D.Double(400, -10, 420, 0));
      g2d.draw(new Line2D.Double(400, 10, 420, 0));
      g2d.drawString("x", 410, 30);
      
      g2d.draw(new Line2D.Double(0, 55, 0, -155));
      g2d.draw(new Line2D.Double(10, -135, 0, -155));
      g2d.draw(new Line2D.Double(-10, -135, 0, -155));
      g2d.drawString("y", -20, -140);
                  
      g2d.scale(1.0, -0.00005);
      double x1 = 0;
      double y1 = 0;
      for (double x2 = 1; x2 < 420; x2++) {
        double y2 = 50 * x2 * x2;
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        x1 = x2;
        y1 = y2;
      }    
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    Exercise44_10 applet = new Exercise44_10();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise44_10");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(480, 180);
    frame.setVisible(true);
  }
}
