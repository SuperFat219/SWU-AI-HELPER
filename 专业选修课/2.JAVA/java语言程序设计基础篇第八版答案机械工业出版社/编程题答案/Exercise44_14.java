import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Exercise44_14 extends JApplet {
  public Exercise44_14() {
    add(new ShapePanel());   
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D
      
      g2d.translate(40, 40);
      
      Path2D path = new Path2D.Double();
      
      path.moveTo(0, 0);
      path.lineTo(0, 70);
      path.quadTo(50, 115, 100, 70);
      path.lineTo(100, 0);
      path.quadTo(50, 45, 0, 0);
          
      g2d.fill(path);
      
      g2d.draw(new QuadCurve2D.Double(0, 0, 50, -45, 100, 0));
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    Exercise44_14 applet = new Exercise44_14();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise44_14");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(220, 180);
    frame.setVisible(true);
  }
}
