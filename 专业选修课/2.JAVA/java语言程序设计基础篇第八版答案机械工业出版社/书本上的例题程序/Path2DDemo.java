import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Path2DDemo extends JApplet {
  public Path2DDemo() {
    add(new ShapePanel());
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g;
      Path2D path = new Path2D.Double();
      path.moveTo(100, 100);
      path.curveTo(150, 50, 250, 150, 300, 100);
      path.moveTo(100, 100);
      path.curveTo(150, 150, 250, 50, 300, 100);
      path.lineTo(200, 20);
      path.closePath();
      
      g2d.draw(path);
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    JApplet applet = new Path2DDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Path2DDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(400, 150);
    frame.setVisible(true);
  }
}
