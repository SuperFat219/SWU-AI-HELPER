import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CurveDemo extends JApplet {
  public CurveDemo() {
    add(new CurvePanel());
  }
  
  static class CurvePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
    
      Graphics2D g2d = (Graphics2D)g;
    
      // Draw a quadratic curve
      g2d.draw(new QuadCurve2D.Double(10, 80, 40, 20, 150, 56));
      g2d.fillOval(40 + 3, 20 + 3, 6, 6);
      g2d.drawString("Control point", 40 + 5, 20);
      
      // Draw a cubic curve
      g2d.draw(new CubicCurve2D.Double
        (200, 80, 240, 20, 350, 156, 450, 80));
      g2d.fillOval(240 + 3, 20 + 3, 6, 6);
      g2d.drawString("Control point 1", 240 + 3, 20);
      g2d.fillOval(350 + 3, 156 + 3, 6, 6);
      g2d.drawString("Control point 2", 350 + 3, 156 + 3);
    }
  }

  /** Main method */
  public static void main(String[] args) {
    CurveDemo applet = new CurveDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("CurveDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(400, 130);
    frame.setVisible(true);
  }
}
