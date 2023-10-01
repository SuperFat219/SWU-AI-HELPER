import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise16_34 extends JFrame {
  private HangmanPanel canvas = new HangmanPanel();
  
  public Exercise16_34() {   
    this.add(canvas, BorderLayout.CENTER); // Add canvas to center  
  }
  
  public static void main(String[] args) {
    JFrame frame = new Exercise16_34();
    frame.setTitle("Exercise16_34");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(250, 280);
    frame.setVisible(true);
  }
  
  class HangmanPanel extends JPanel {
    int palindromeRadius = 150;
    int ballRadius = 10;
    int leftAngle = 120;
    int rightAngle = 60;
    int angle = leftAngle; // Start from leftAngle
    int angleDelta = 1; // Swing interval
    int delay = 100;
    
    Timer timer = new Timer(delay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });
    
    HangmanPanel() {
       timer.start();
      
      this.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: delay += 10; break;
            case KeyEvent.VK_UP: delay -= 10; break;
            case KeyEvent.VK_S: timer.stop(); break;
            case KeyEvent.VK_R: timer.start(); break;
          }

          if (delay < 0) delay = 10; // delay cannot be negative
          
          timer.setDelay(delay);
      }});
      
      // Set focus
      setFocusable(true);
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      g.drawArc(20, 200, 80, 40, 0, 180); // Draw the base
      g.drawLine(20 + 40, 200, 20 + 40, 20); // Draw the pole
      g.drawLine(20 + 40, 20, 20 + 40 + 100, 20); // Draw the hanger

      if (angle < rightAngle)
        angleDelta = 1; // Swing to the left
      else if (angle > leftAngle)
        angleDelta = -1; // Swing to the right
            
      angle += angleDelta;

      int x1 = 20 + 40 + 100;
      int y1 = 20;
      int radius = 20;
      int x = x1 + (int)(radius * Math.cos(Math.toRadians(angle)));
      int y = y1 + (int)(radius * Math.sin(Math.toRadians(angle)));
      g.drawLine(20 + 40 + 100, 20, x, y); // Draw the hanger

      // Draw the circle
      double length = 20 + 20;
      x = x1 + (int)(length * Math.cos(Math.toRadians(angle)));
      y = y1 + (int)(length * Math.sin(Math.toRadians(angle)));      
      g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius); // Draw the hanger

      // Draw the left arm      
      length = distance(x1, y1, 
          20 + 40 + 100 - (int)(radius * Math.cos(Math.toRadians(45))),
          40 + radius + (int)(radius * Math.sin(Math.toRadians(45))));    
      double angle1 = Math.toDegrees(Math.asin(
          radius * Math.cos(Math.toRadians(45)) / length));
      int x2 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      int y2 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1)));      
      
      length = (int)distance(x1, y1, 
          20 + 40 + 100 - 60, 40 + radius + 60);
      angle1 = Math.toDegrees(Math.asin(60 / length));
      int x3 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      int y3 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1)));      
     
      g.drawLine(x2, y2, x3, y3);
      
      // Draw the right arm
      length = distance(x1, y1, 
          20 + 40 + 100 + (int)(radius * Math.cos(Math.toRadians(45))),
          40 + radius + (int)(radius * Math.sin(Math.toRadians(45))));    
      angle1 = - Math.toDegrees(Math.asin(
          radius * Math.cos(Math.toRadians(45)) / length));
      x2 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      y2 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1)));      
      
      length = (int)distance(x1, y1, 
          20 + 40 + 100 + 60, 40 + radius + 60);
      angle1 = -Math.toDegrees(Math.asin(60 / length));
      x3 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      y3 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1))); 
      
      g.drawLine(x2, y2, x3, y3);

      // Draw the body
      length = 40 + 20;
      x2 = x1 + (int)(length * Math.cos(Math.toRadians(angle)));
      y2 = y1 + (int)(length * Math.sin(Math.toRadians(angle)));
      
      length = 40 + 20 + 60;
      x3 = x1 + (int)(length * Math.cos(Math.toRadians(angle)));
      y3 = y1 + (int)(length * Math.sin(Math.toRadians(angle)));
      
      g.drawLine(x2, y2, x3, y3);

      // Draw the left leg
      length = distance(x1, y1, 
          20 + 40 + 100 - 40, 40 + radius + 80 + 40);     
      angle1 = Math.toDegrees(Math.asin(
          40.0 / length));
      int x4 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      int y4 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1)));            
      g.drawLine(x3, y3, x4, y4);

      // Draw the right leg
      angle1 = -Math.toDegrees(Math.asin(
          40.0 / length));
      x4 = x1 + (int)(length * Math.cos(Math.toRadians(angle + angle1)));
      y4 = y1 + (int)(length * Math.sin(Math.toRadians(angle + angle1)));            
      g.drawLine(x3, y3, x4, y4);
    }
  }
  
  /** Compute the distance between two points (x1, y1) and (x2, y2)*/
  public static double distance(
      double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }
}