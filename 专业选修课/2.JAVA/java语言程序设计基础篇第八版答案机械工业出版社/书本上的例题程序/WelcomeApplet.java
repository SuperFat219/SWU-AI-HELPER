// WelcomeApplet.java: Applet for displaying a message
import javax.swing.*;

public class WelcomeApplet extends JApplet {
  /** Initialize the applet */
  public void init() {
    add(new JLabel("Welcome to Java", JLabel.CENTER));
  }
}
