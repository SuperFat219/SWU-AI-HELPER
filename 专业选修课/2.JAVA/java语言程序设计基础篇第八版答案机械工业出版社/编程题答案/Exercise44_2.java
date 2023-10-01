import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class Exercise44_2 extends JApplet {
  public Exercise44_2() {
    add(new ShapePanel());   
  }

  class ShapePanel extends JPanel {
    private Point p = new Point(20, 20);
    private Rectangle2D rectangle = new Rectangle2D.Double(40, 40, 40, 60);
    private double radius = 20;
    
    ShapePanel() {
      this.setFocusable(true);
      this.addMouseMotionListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
          p.setLocation(e.getX(), e.getY());
          repaint();
        }
      });
      this.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_UP)
            radius += 5;
          else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            radius -= 5;
          
          repaint();
        }
      });
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D)g; // Create Graphics2D
      g2d.draw(rectangle);
      Ellipse2D ellipse = new Ellipse2D.Double(p.getX(), p.getY(), radius, radius);
      g2d.draw(ellipse);
      
      if (ellipse.contains(rectangle))
        g2d.drawString("The circle contains the rectangle", (int)p.getX(), (int)p.getY());
      else if (ellipse.intersects(rectangle))
        g2d.drawString("The circle intersects the rectangle", (int)p.getX(), (int)p.getY());
      else    
        g2d.drawString("The circle is outside of the rectangle", (int)p.getX(), (int)p.getY());
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    Exercise44_2 applet = new Exercise44_2();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise44_2");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(350, 360);
    frame.setVisible(true);
  }
}
