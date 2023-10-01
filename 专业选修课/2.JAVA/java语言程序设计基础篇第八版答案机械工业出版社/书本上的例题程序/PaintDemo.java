import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PaintDemo extends JApplet {
  public PaintDemo() {
    add(new ShapePanel());
  }

  class ShapePanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
  
      Graphics2D g2d = (Graphics2D) g;
  
      g2d.setPaint(new GradientPaint(10, 10, Color.RED, 40, 40,
        Color.BLUE, true));
      g2d.fill(new Rectangle2D.Double(10, 10, 90, 70));
      g2d.setFont(new Font("Serif", Font.BOLD, 50));
      g2d.drawString("GradientPaint", 10, 120);
  
      g2d.translate(100, 0);
      g2d.setPaint(new GradientPaint(10, 10, Color.YELLOW, 40, 40,
        Color.BLACK));
      g2d.fill(new Rectangle2D.Double(10, 10, 90, 70));
  
      g2d.translate(100, 0);
      g2d.setPaint(Color.YELLOW);
      g2d.fill(new Rectangle2D.Double(10, 10, 90, 70));
  
      try {
        java.net.URL url = 
          getClass().getClassLoader().getResource("image/ca.gif");
        java.awt.image.BufferedImage image = ImageIO.read(url);
        TexturePaint texturePaint = new TexturePaint(image, 
          new Rectangle2D.Double(10, 10, 100, 70));     
        g2d.translate(130, 0);
        g2d.setPaint(texturePaint);
        g2d.fill(new Ellipse2D.Double(10, 10, 100, 70));  
        
        texturePaint = new TexturePaint(image, 
          new Rectangle2D.Double(10, 10, 50, 70));     
        g2d.translate(110, 0);
        g2d.setPaint(texturePaint);
        g2d.fill(new Ellipse2D.Double(10, 10, 100, 70));  

        texturePaint = new TexturePaint(image, 
          new Rectangle2D.Double(10, 10, 50, 35));     
        g2d.translate(110, 0);
        g2d.setPaint(texturePaint);
        g2d.fill(new Ellipse2D.Double(10, 10, 100, 70));  
        g2d.drawString("TexturePaint", -190, 120);        
      }
      catch (java.io.IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /** Main method */
  public static void main(String[] args) {
    PaintDemo applet = new PaintDemo();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("PaintDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(700, 160);
    frame.setVisible(true);
  }
}