import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Exercise18_14 extends JApplet {
  // Declare three clock panels
  private ClockControl clockControl1, clockControl2, clockControl3;

  // Declare group control buttons
  private JButton jbtResumeAll, jbtSuspendAll;

  /** This main method enables the applet to run as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise18_14");

    // Create an instance of the applet
    Exercise18_14 applet = new Exercise18_14();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(600, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /** Initialize the applet */
  public void init() {
    // Panel p1 for holding three clocks
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(1, 3));

    // Create a clock for Berlin
    p1.add(clockControl1 = new ClockControl());

    // Create a clock for San Francisco
    p1.add(clockControl2 = new ClockControl());

    // Create a clock for Taichung
    p1.add(clockControl3 = new ClockControl());

    // Panel p2 for holding two group control buttons
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.add(jbtResumeAll = new JButton("Resume All"));
    p2.add(jbtSuspendAll = new JButton("Suspend All"));

    // Add panel p1 and p2 into the applet
    setLayout(new BorderLayout());
    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    // Register listeners
    jbtResumeAll.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
	      // Start all clocks
	      clockControl1.resume();
	      clockControl2.resume();
	      clockControl3.resume();    	
    	}
    });
    jbtSuspendAll.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		clockControl1.suspend();
	      clockControl2.suspend();
	      clockControl3.suspend();  
    	}
    });
  }
}

class ClockControl extends JPanel {
  private Clock clock = new Clock();
  private JButton jbtSuspend = new JButton("Suspend");
  private JButton jbtResume = new JButton("Resume");

  public ClockControl() {
    // Group buttons in a panel
    JPanel panel = new JPanel();
    panel.add(jbtSuspend);
    panel.add(jbtResume);

    // Add clock and buttons to the panel
    setLayout(new BorderLayout());
    add(clock, BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);

    // Register listeners
    jbtSuspend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.suspend();
      }    	
    });
    jbtResume.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.resume();
      }    	
    });
  }

  public void suspend() {
    clock.suspend();
  }

  public void resume() {
    clock.resume();
  }

  class Clock extends StillClock {
    private Timer timer = new Timer(1000, new Listener());
    
    public Clock() {
      timer.start();
    }

    class Listener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        // Set new time and repaint the clock to display current time
        setCurrentTime();
        repaint();
      }
    }

    public void suspend() {
      timer.stop();
    }

    public void resume() {
      timer.start();
   }
  }
}
