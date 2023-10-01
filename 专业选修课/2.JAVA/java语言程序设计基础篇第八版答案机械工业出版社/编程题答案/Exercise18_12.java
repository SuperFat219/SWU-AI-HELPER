import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise18_12 extends JApplet {
  private FanControl fanControl1 = new FanControl();
  private FanControl fanControl2 = new FanControl();
  private FanControl fanControl3 = new FanControl();
  private JButton jbtStartAll = new JButton("Start All");
  private JButton jbtStopAll = new JButton("Stop All");

  public void init() {
    // Create a panel to group three fan control panels
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(1, 3));
    panel1.add(fanControl1);
    panel1.add(fanControl2);
    panel1.add(fanControl3);

    // Create a panel to group two buttons
    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.add(jbtStartAll);
    panel2.add(jbtStopAll);

    // Add the panels into the applet
    add(panel1, BorderLayout.CENTER);
    add(panel2, BorderLayout.SOUTH);

    // Register listeners
    jbtStartAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fanControl1.start();
        fanControl2.start();
        fanControl3.start();  	
      }
    });
    jbtStopAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fanControl1.stop();
        fanControl2.stop();
        fanControl3.stop();
      }    	
    });

    // Set line border for the fan control panels
    Border border = BorderFactory.createLineBorder(Color.black);
    fanControl1.setBorder(border);
    fanControl2.setBorder(border);
    fanControl3.setBorder(border);
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame(
      "Exercise18_12: Controlling a Group of Fans");

    // Create an instance of the applet
    Exercise18_12 applet = new Exercise18_12();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
