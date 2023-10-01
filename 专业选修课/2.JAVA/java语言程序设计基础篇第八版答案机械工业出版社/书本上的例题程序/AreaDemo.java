import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class AreaDemo extends JApplet {
  public AreaDemo() {
    add(new ShapePanel());
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
  
      Graphics2D g2d = (Graphics2D)g; // Get Graphics2D
  
      // Create two shapes
      Shape shape1 = new Ellipse2D.Double(0, 0, 50, 50);
      Shape shape2 = new Ellipse2D.Double(25, 0, 50, 50);
      g2d.translate(10, 10); // Translate to a new origin     
      g2d.draw(shape1); // Draw the shape
      g2d.draw(shape2); // Draw the shape
      
      Area area1 = new Area(shape1); // Create an area
      Area area2 = new Area(shape2);
      area1.add(area2); // Add area2 to area1
      g2d.translate(100, 0); // Translate to a new origin     
      g2d.draw(area1); // Draw the outline of the shape in the area

      g2d.translate(100, 0); // Translate to a new origin     
      g2d.fill(area1); // Fill the shape in the area

      area1 = new Area(shape1);
      area1.subtract(area2); // Subtract area2 from area1
      g2d.translate(100, 0); // Translate to a new origin     
      g2d.fill(area1); // Fill the shape in the area

      area1 = new Area(shape1);
      area1.intersect(area2); // Intersection of area2 with area1
      g2d.translate(100, 0); // Translate to a new origin     
      g2d.fill(area1); // Fill the shape in the area

      area1 = new Area(shape1);
      area1.exclusiveOr(area2); // Exclusive or of area2 with area1
      g2d.translate(100, 0); // Translate to a new origin     
      g2d.fill(area1); // Fill the shape in the area
    }
  }

  /** Main method */
  public static void main(String[] args) {
    AreaDemo applet = new AreaDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Test");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(450, 160);
    frame.setVisible(true);
  }
}