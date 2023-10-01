import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class Exercise16_20 extends JFrame { 
  private PaintPanel paintPanel = new PaintPanel();
  
  public Exercise16_20() {
    add(paintPanel);
  }

  static class PaintPanel extends JPanel {
    private MyRectangle2D rectangle = new MyRectangle2D(100, 60, 100, 40);
    private boolean isInside = false;
    private Point mousePoint = new Point(0, 0); 
    public static int RADIUS = 50;
    

    public PaintPanel() {    
      this.addMouseMotionListener(new MouseAdapter() {
        public void mouseMoved(MouseEvent e) { 
          if (rectangle.contains(e.getX(), e.getY())) 
            isInside = true;
          else
            isInside = false;
          
          mousePoint.x = e.getX();
          mousePoint.y = e.getY();
          
          repaint();
        }
      });
    }

    /** Paint message */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawRect((int)(rectangle.getX()) - (int)(rectangle.getWidth() / 2), 
        (int)(rectangle.getY()) - (int)(rectangle.getHeight() / 2),
        (int)(rectangle.getWidth()), (int)(rectangle.getHeight()));
      
      g.drawString(isInside ? "Mouse point is in the rectangle" :
        "Mouse point is not in the rectangle", 
        mousePoint.x, mousePoint.y);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new Exercise16_20();
    frame.setTitle("Exercise16_20");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
