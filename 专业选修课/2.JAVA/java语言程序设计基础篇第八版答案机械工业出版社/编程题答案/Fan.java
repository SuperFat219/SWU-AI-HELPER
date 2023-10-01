import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fan extends JPanel implements ActionListener {
  private int xCenter, yCenter;
  private int fanRadius, bladeLength;
  private int angle = 100;
  private int direction = 1;
  private int speed = 10;
  protected Timer timer = new Timer(speed, this);

  public Fan() {
    timer.start();
  }

  public void reverse() {
    direction = -direction;
  }

  public void setSpeed(int ms) {
    speed = ms;
    timer.setDelay(speed);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Set clock radius, and center
    fanRadius = (int)(Math.min(getSize().width, getSize().height)*0.9*0.5);
    xCenter = (getSize().width)/2;
    yCenter = (getSize().height)/2;
    bladeLength = (int)(fanRadius*0.9);
    angle = (angle+direction)%360;

    //draw circle
    g.setColor(Color.black);
    g.drawOval(xCenter - fanRadius,yCenter - fanRadius,
      2*fanRadius, 2*fanRadius);

    //draw four blades
    drawBlade(g, angle);
    drawBlade(g, angle+90);
    drawBlade(g, angle+180);
    drawBlade(g, angle+270);
  }

  private void drawBlade(Graphics g, int angle) {
    g.setColor(Color.red);
    g.fillArc(xCenter-bladeLength, yCenter-bladeLength,
      2*bladeLength, 2*bladeLength, angle, 30);
  }
  
  public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
    repaint();
  }
}