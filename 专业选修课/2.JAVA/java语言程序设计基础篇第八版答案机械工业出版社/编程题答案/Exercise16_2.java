import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise16_2 extends JFrame
    implements ComponentListener {
  public Exercise16_2() {
    // Set the window title
    setTitle("Exercise16_2");

    // Register the frame as a listener for component events
    this.addComponentListener(this);
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise16_2 frame = new Exercise16_2();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(100, 80);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public void componentMoved(ComponentEvent e) {
    System.out.println("Component moved");
  }

  public void componentHidden(ComponentEvent e) {
    System.out.println("Component hidden");
  }

  public void componentResized(ComponentEvent e) {
    System.out.println("Component resized");
  }

  public void componentShown(ComponentEvent e) {
    System.out.println("Component shown");
  }
}
