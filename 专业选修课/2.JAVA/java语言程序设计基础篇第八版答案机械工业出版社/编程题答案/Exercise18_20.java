import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import javax.swing.*;
import java.net.URL;

public class Exercise18_20 extends JApplet {
  private AudioClip audioClip;
  private JButton jbtPlay, jbtLoop, jbtStop;

  public void init() {
    URL url = this.getClass().getResource("ticker.au");
    audioClip = Applet.newAudioClip(url);

    setLayout(new FlowLayout());
    add(jbtPlay = new JButton("Play"));
    add(jbtLoop= new JButton("Loop"));
    add(jbtStop = new JButton("Stop"));

    jbtPlay.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        audioClip.play();
      }    	
    });
    jbtLoop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        audioClip.loop();
      }    	
    });
    jbtStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        audioClip.stop();
      }    	
    });
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise18_20");

    // Create an instance of the applet
    Exercise18_20 applet = new Exercise18_20();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
