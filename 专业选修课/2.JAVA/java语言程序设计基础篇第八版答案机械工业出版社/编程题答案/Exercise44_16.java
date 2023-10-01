import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Exercise44_16 extends JApplet {
  public Exercise44_16() {
    add(new ShapePanel());   
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D
      
      g2d.translate(125, 70);
      
      Ellipse2D oval = new Ellipse2D.Double(-100, -50, 200, 100);
      Ellipse2D circle1 = new Ellipse2D.Double(-70, -15, 50, 30);
      Ellipse2D circle2 = new Ellipse2D.Double(20, -15, 50, 30);
            
      Area area1 = new Area(oval);
      Area area2 = new Area(circle1);     
      Area area3 = new Area(circle2);  
      area1.subtract(area2);
      area1.subtract(area3);
      
      g2d.fill(area1);
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    Exercise44_16 applet = new Exercise44_16();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise44_16");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(250, 180);
    frame.setVisible(true);
  }
}
