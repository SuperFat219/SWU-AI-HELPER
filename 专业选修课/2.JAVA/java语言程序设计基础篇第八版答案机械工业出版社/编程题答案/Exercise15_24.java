import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise15_24 extends JFrame { 
  private BeanMachinePanel paintPanel = new BeanMachinePanel();
  
  public Exercise15_24() {
    add(paintPanel);
  }

  static class BeanMachinePanel extends JPanel {
    final static int HGAP = 20;
    final static int VGAP = 20;
    final static int RADIUS = 5;
    final static int LENGTH_OF_SLOTS = 40;
    final static int LENGTH_OF_OPENNING = 15;
    final static int Y_FOR_FIRST_NAIL = 50;
    final static int NUMBER_OF_SLOTS = 9;
    final static int NUMBER_OF_ROWS = NUMBER_OF_SLOTS - 2;
    
    public BeanMachinePanel() {    

    }

    /** Paint message */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      int y = Y_FOR_FIRST_NAIL;
      int xCenter = getWidth() / 2;
      
      // Draw pegs in multiple lines
      g.setColor(Color.GREEN);
      for (int i = 0; i < NUMBER_OF_ROWS; i++) {
        y += VGAP;
        for (int k = 0; k <= i; k++) {
          g.fillOval(xCenter - i * HGAP / 2 + k * HGAP - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        }
      }
      
      // Draw vertical lines for slots
      g.setColor(Color.BLACK);
      y = y + RADIUS;
      for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
        int x = xCenter - (NUMBER_OF_ROWS - 1) * HGAP / 2 + (i - 1) * HGAP;
        g.drawLine(x, y, x, y + LENGTH_OF_SLOTS);
      }
      
      // Draw a horizontal line for bottom
      g.drawLine(xCenter - (NUMBER_OF_ROWS - 1) * HGAP / 2 - HGAP, y + LENGTH_OF_SLOTS, 
        xCenter - (NUMBER_OF_ROWS - 1) * HGAP / 2 + NUMBER_OF_ROWS * HGAP, y + LENGTH_OF_SLOTS);
      // Draw two side lines
      g.drawLine(xCenter + HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS, xCenter - (NUMBER_OF_ROWS - 1) * HGAP / 2 + NUMBER_OF_ROWS * HGAP, y);
      g.drawLine(xCenter - HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS, xCenter - (NUMBER_OF_ROWS - 1) * HGAP / 2 - HGAP, y);
      // Draw two vertical lines for the openning
      g.drawLine(xCenter - HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS, xCenter - HGAP / 2, Y_FOR_FIRST_NAIL - LENGTH_OF_OPENNING);
      g.drawLine(xCenter + HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS, xCenter + HGAP / 2, Y_FOR_FIRST_NAIL - LENGTH_OF_OPENNING);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new Exercise15_24();
    frame.setTitle("Exercise15_24");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}