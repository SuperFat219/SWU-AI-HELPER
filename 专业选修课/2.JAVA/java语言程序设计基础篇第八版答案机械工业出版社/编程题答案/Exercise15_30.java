import java.awt.*;

import javax.swing.*;

public class Exercise15_30 extends JFrame {
  public Exercise15_30() {
    add(new DrawLineConnectingCircles());
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise15_30 frame = new Exercise15_30();
    frame.setTitle("Exercise15_30");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class DrawLineConnectingCircles extends JPanel {
	DrawLineConnectingCircles() {
	}
	
	protected void paintComponent(Graphics g) {  	
		super.paintComponent(g);
		
    int x1 = (int)(Math.random() * (getWidth() - 12));
    int y1 = (int)(Math.random() * (getHeight() - 12));
    int x2 = (int)(Math.random() * (getWidth() - 12));
    int y2 = (int)(Math.random() * (getHeight() - 12));
    
		int RADIUS = 15;
		
		g.setColor(Color.RED);
		g.drawOval(x1 - RADIUS, y1 - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g.drawOval(x2 - RADIUS, y2 - RADIUS, 2 * RADIUS, 2 * RADIUS);  		
		g.drawString("1", x1 - 6, y1 + 4);
		g.drawString("2", x2 - 6, y2 + 4);
		
		connectTwoCircles(g, x1, y1, x2, y2, RADIUS);
	}
	
  /** Connect two circles centered at (x1, y1) and (x2, y2) */
  private void connectTwoCircles(Graphics g, 
      int x1, int y1, int x2, int y2, int radius) {
  	double vGap = Math.abs(y2 - y1);
    double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    int x11 = (int)(x1 - radius * (x1 - x2) / d);
    int y11 = (int)(y1 - radius * (y1 - y2) / d);
    int x21 = (int)(x2 + radius * (x1 - x2) / d);
    int y21 = (int)(y2 + radius * (y1 - y2) / d);
    g.drawLine(x11, y11, x21, y21);
  }  	
  
	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
}
