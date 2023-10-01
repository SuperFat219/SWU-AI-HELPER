import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RotationDemo extends JApplet {
  public RotationDemo() {
    add(new ShapePanel());
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g;
      Rectangle2D rectangle = new Rectangle2D.Double(20, 20, 50, 60);

      g2d.translate(150, 120); // Move origin to the center
      g2d.fill(new Ellipse2D.Double(-5, -5, 10, 10));
      java.util.Random random = new java.util.Random();
      for (int i = 0; i < 10; i++) {
        g2d.setColor(new Color(random.nextInt(256), 
          random.nextInt(256), random.nextInt(256)));
        g2d.draw(rectangle);
        g2d.rotate(Math.PI / 5);
      }
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    JApplet applet = new RotationDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("RotationDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}
