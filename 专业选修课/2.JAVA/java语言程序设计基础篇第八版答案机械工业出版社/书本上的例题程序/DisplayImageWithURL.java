import javax.swing.*;

public class DisplayImageWithURL extends JApplet {
  public DisplayImageWithURL() {
    java.net.URL url = this.getClass().getResource("image/us.gif");
    add(new JLabel(new ImageIcon(url)));
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("DisplayImageWithURL");

    // Create an instance of the applet
    DisplayImageWithURL applet = new DisplayImageWithURL();
    applet.init();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the frame
    frame.setSize(200, 150);
    frame.setVisible(true);
  }
}
