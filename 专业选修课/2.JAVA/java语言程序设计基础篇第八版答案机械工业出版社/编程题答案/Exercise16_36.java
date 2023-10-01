import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise16_36 extends JFrame {
  public Exercise16_36() {
    setLayout(new GridLayout(3, 3));
    
    for (int i = 0; i < 9; i++)
      add(new ClickableCell("H"));
  }
  
  /** An inner class for displaying a cell */
  static class ClickableCell extends JLabel {
    public ClickableCell(String s) {
      setBorder(new LineBorder(Color.black, 1)); // Cell border
      setHorizontalAlignment(JLabel.CENTER);
      setFont(new Font("TimesRoman", Font.BOLD, 20));
      setText(s);

      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (getText().equals("H")) {
            setText("T"); // Flip from H to T
          }
          else {
            setText("H"); // Flip from T to H
          }
        }
      });
    }
  }
  
  /** This main method enables the applet to run as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new Exercise16_36();
    frame.setTitle("Exercise16_36");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
