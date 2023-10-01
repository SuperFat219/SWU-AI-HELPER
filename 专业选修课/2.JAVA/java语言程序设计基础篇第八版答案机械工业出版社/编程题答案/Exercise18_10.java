import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Exercise18_10 extends JApplet {
  public Exercise18_10() {
    add(new TemperatureHistogram(), BorderLayout.CENTER);
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame(
      "Exercise18_10: Temperature Histogram");

    // Create an instance of the applet
    Exercise18_10 applet = new Exercise18_10();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(500, 180);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class TemperatureHistogram extends JPanel {
  private double temperature[] = new double[24];
  private boolean firstTime = true;
  private double newTemp;
  private int current = 23;
  private int interval, width, individualWidth, height;

  public TemperatureHistogram() {
    for (int i=0; i<temperature.length; i++)
      temperature[i] = 0; //Math.random()*40 + 50;

    // Simulating temperatures of 24 hours;
    // It should be 1000*60*60, but for testing 1000 is used here
    Timer timer = new Timer(1000, new Listener());
    timer.start();
  }

  public void paintComponent(Graphics g) {
    //super.paintComponent(g);

    width = getSize().width;
    height = getSize().height;

    interval = (width-40)/24;
    individualWidth = (int)(((width-40)/24)*0.60);

    if (firstTime) {
      firstTime = false;

      int x = 30;

      for (int i=0; i<24; i++) {
        // g.drawRect(x, height-45-(int)temperature[i], individualWidth, (int)temperature[i]);
        g.drawString(i+"", x, height-35 );
        x += interval;
      }

      FontMetrics fm = g.getFontMetrics();
      String title = "Current Time: "+new Date();
      g.drawLine(10, height-45, width-10, height-45);
      g.drawString(title, (width-fm.stringWidth(title))/2, height-20);
    }
    else {
      int previous = (current-1+24)%24;
      g.setColor(getBackground()); //Color.white);
      g.fillRect(interval*previous+30, height-45-(int)temperature[previous],
        individualWidth, (int)temperature[previous]);

      g.setColor(Color.black);
      g.drawRect(interval*previous+30, height-45-(int)temperature[previous],
        individualWidth, (int)temperature[previous]);

      g.setColor(getBackground());
      g.drawRect(interval*current+30, height-45-(int)temperature[current],
        individualWidth, (int)temperature[current]);

      g.setColor(Color.red);
      temperature[current] = newTemp;
      g.fillRect(interval*current+30, height-45-(int)temperature[current],
        individualWidth, (int)temperature[current]);
    }
  }

  class Listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      current = (current+1)%24;
      newTemp = (int)(Math.random()*40 + 50);
      repaint();
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(300, 100);
  }
}
