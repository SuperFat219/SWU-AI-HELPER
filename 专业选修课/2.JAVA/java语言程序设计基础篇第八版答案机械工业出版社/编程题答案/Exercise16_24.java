import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise16_24 extends JFrame { 
  private JButton jbtStart = new JButton("Start");
  private JButton jbtStop = new JButton("Stop");
  private ClockAnimation clock = new ClockAnimation();
  
  public Exercise16_24() {
    JPanel panel = new JPanel(); // Use the panel to group buttons
    panel.add(jbtStart);
    panel.add(jbtStop);
    
    this.add(clock, BorderLayout.CENTER); // Add canvas to center
    this.add(panel, BorderLayout.SOUTH); // Add buttons to the frame
    
    jbtStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.start();
      }
    });

    jbtStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.stop();
      }
    });    
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new Exercise16_24();
    frame.setTitle("Exercise16_24");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
  
  public class ClockAnimation extends StillClock {
    Timer timer = new Timer(1000, new TimerListener());

    public ClockAnimation() {
      // Create a timer with delay 1000 ms
      timer.start();
    }

    public void start() {
      timer.start();
    }

    public void stop() {
      timer.stop();
    }
    
    private class TimerListener implements ActionListener {
      /** Handle the action event */
      public void actionPerformed(ActionEvent e) {
        // Set new time and repaint the clock to display current time
        setCurrentTime();
        repaint();
      }
    }
  }
}