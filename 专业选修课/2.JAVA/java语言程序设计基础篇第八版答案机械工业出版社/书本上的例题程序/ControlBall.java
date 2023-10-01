import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlBall extends JFrame {
  private JButton jbtEnlarge = new JButton("Enlarge");
  private JButton jbtShrink = new JButton("Shrink");
  private BallCanvas canvas = new BallCanvas();
  
  public ControlBall() {
    JPanel panel = new JPanel(); // Use the panel to group buttons
    panel.add(jbtEnlarge);
    panel.add(jbtShrink);
    
    this.add(canvas, BorderLayout.CENTER); // Add canvas to center
    this.add(panel, BorderLayout.SOUTH); // Add buttons to the frame
    
    jbtEnlarge.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        canvas.enlarge();
      }
    });

    jbtShrink.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        canvas.shrink();
      }
    });    
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new ControlBall();
    frame.setTitle("ControlBall");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
  
  public static class BallCanvas extends JPanel {
     private int radius = 5; // Default ball radius
    
    /** Enlarge the ball */
    public void enlarge() {
      radius += 1; 
      repaint();
    }
    
    /** Shrink the ball */
    public void shrink() {
      radius -= 1; 
      repaint();
    }
    
    /** Repaint the ball */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 
        2 * radius, 2 * radius);
    }
  }
}
