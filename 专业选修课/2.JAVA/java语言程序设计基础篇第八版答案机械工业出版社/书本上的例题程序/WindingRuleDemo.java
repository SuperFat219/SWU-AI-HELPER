import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class WindingRuleDemo extends JApplet {
  public WindingRuleDemo() {
    add(new ShapePanel());
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
  
      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D
  
      g2d.translate(10, 10); // Translate to a new origin     
      g2d.draw(createAPath()); // Create and draw a path

      g2d.translate(160, 0); // Translate to a new origin
      Path2D path2 = createAPath(); // Create a path
      path2.setWindingRule(Path2D.WIND_EVEN_ODD); // Set a new rule
      g2d.fill(path2); // Create and fill a path
      
      g2d.translate(160, 0); // Translate to a new origin
      Path2D path3 = createAPath(); // Create a path
      path3.setWindingRule(Path2D.WIND_NON_ZERO); // Set a new rule
      g2d.fill(path3); // Create and fill a path
    }
    
    private Path2D createAPath() {
      // Define the outer rectangle
      Path2D path = new Path2D.Double();
      path.moveTo(0, 0);
      path.lineTo(0, 100);
      path.lineTo(100, 100);
      path.lineTo(100, 0);
      path.lineTo(0, 0);

      // Define the inner rectangle
      path.moveTo(30, 30);
      path.lineTo(30, 70);
      path.lineTo(70, 70);
      path.lineTo(70, 30);
      path.lineTo(30, 30);
      
      return path;
    }
  }

  /** Main method */
  public static void main(String[] args) {
    WindingRuleDemo applet = new WindingRuleDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("WindingRuleDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(450, 160);
    frame.setVisible(true);
  }
}