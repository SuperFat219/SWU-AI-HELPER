import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16_10 extends JFrame {
  private DisplayPanel panel = new DisplayPanel();

  public Exercise16_10() {
    add(panel, BorderLayout.CENTER);
    panel.setFocusable(true);
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new Exercise16_10();
    frame.setTitle("Exercise16_10");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class DisplayPanel extends MessagePanel {
    private String message = "";
    private StringBuffer buffer = new StringBuffer();

    public DisplayPanel() {
      setCentered(true);
      this.addKeyListener(new Listener()); // Add listener
    }
    
    class Listener extends KeyAdapter {

      public void keyTyped(KeyEvent e) {
        buffer.append(e.getKeyChar());
      }

      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          message = buffer.toString().trim();
          buffer.setLength(0);
          setMessage(message);
        }
      }
    }
  }
}
