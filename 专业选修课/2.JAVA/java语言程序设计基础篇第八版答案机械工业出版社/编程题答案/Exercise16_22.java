import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise16_22 extends JFrame { 
  final static int NUMBER_OF_SLOTS = 9;
  final static int NUMBER_OF_ROWS = NUMBER_OF_SLOTS - 2;

  private int shift = 0;
  private int[] slots = new int[NUMBER_OF_SLOTS];
  private int numberOfBallsDropped = 0;
  private int moveCount = 0;
  private int position = 0;
 
  private BeanMachinePanel paintPanel = new BeanMachinePanel();
  private Timer timer = new Timer(200, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      moveCount++;
      if (moveCount <= NUMBER_OF_ROWS) {
        if (Math.random() < 0.5) 
          paintPanel.moveRedBallLeft();
        else {
          paintPanel.moveRedBallRight();
          position++;
        }
      }
      else {
        slots[position]++;
        paintPanel.startRedBall();
        shift = 0;
        moveCount = 0;
        position = 0;
        
        numberOfBallsDropped++;
        if (numberOfBallsDropped == 10) {
          timer.stop();
          paintPanel.hideRedBall();
        }
      }
    }
  });
  
  public Exercise16_22() {
    add(paintPanel);
    timer.start();
  }

  class BeanMachinePanel extends JPanel {
    final static int HGAP = 20;
    final static int VGAP = 20;
    final static int RADIUS = 5;
    final static int LENGTH_OF_SLOTS = 40;
    final static int LENGTH_OF_OPENNING = 15;
    final static int Y_FOR_FIRST_NAIL = 50;
    final static int RED_BALL_START_Y = Y_FOR_FIRST_NAIL - RADIUS;

    private int yRed = RED_BALL_START_Y;
    private boolean hideRedBall = false;
    
    /** Move the red ball down left */
    public void moveRedBallLeft() {
      shift -= HGAP / 2;
      yRed += VGAP;
      repaint();
    }

    /** Move the red ball down right */
    public void moveRedBallRight() {
      shift += HGAP / 2;
      yRed += VGAP;
      repaint();
    }
    
    /** Move the red ball down right */
    public void startRedBall() {
      yRed = RED_BALL_START_Y;
      hideRedBall = false;
      repaint();
    }
    
    /** Move the red ball down right */
    public void hideRedBall() {
      hideRedBall = true;
      repaint();
    }
    
    /** Paint message */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      int y = Y_FOR_FIRST_NAIL;
      int xCenter = getWidth() / 2;

      // Draw the red ball
      if (!hideRedBall) {
        g.setColor(Color.RED);
        int xRed = xCenter + shift;
        g.fillOval(xRed - RADIUS, yRed - RADIUS, 2 * RADIUS, 2 * RADIUS);
      }
      
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
      
      // Paint the balls in the slots
      g.setColor(Color.RED);      
      for (int i = 0; i < slots.length; i++) {
        int x = xCenter - (NUMBER_OF_ROWS) * HGAP / 2 + i * HGAP;
        for (int j = 0; j < slots[i]; j++) {
          g.fillOval(x - RADIUS, y + LENGTH_OF_SLOTS - 2 * RADIUS - j * 2 * RADIUS, 2 * RADIUS, 2 * RADIUS);
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new Exercise16_22();
    frame.setTitle("Exercise16_22");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}