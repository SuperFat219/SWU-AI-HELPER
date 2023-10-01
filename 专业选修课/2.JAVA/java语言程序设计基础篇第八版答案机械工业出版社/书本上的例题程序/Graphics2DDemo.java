import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Graphics2DDemo extends JApplet {
  public Graphics2DDemo() {
    add(new ShapePanel());
  }
  
  static class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
    
      Graphics2D g2d = (Graphics2D)g;
    
      g2d.draw(new Line2D.Double(10, 10, 40, 80));
      g2d.draw(new Rectangle2D.Double(50, 10, 30, 70));
      g2d.fill(new Rectangle2D.Double(90, 10, 30, 70));
      g2d.fill(new RoundRectangle2D.Double(130, 10, 30, 70, 20, 30));
      g2d.draw(new Ellipse2D.Double(170, 10, 30, 70));
      g2d.draw(
        new Arc2D.Double(220, 10, 30, 70, 0, 270, Arc2D.OPEN));
      g2d.draw(new Arc2D.Double(260, 10, 30, 70, 0, 270, Arc2D.PIE));
      g2d.draw(
        new Arc2D.Double(300, 10, 30, 70, 0, 270, Arc2D.CHORD));
    }
  }

  /** Main method */
  public static void main(String[] args) {
    Graphics2DDemo applet = new Graphics2DDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Graphics2DDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(400, 130);
    frame.setVisible(true);
  }
}
