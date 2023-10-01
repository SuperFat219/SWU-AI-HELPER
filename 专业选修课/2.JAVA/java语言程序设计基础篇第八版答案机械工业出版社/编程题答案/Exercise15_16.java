import java.awt.*;
import javax.swing.*;

public class Exercise15_16 extends JFrame {
  public static void main(String[] args) {
    JFrame frame = new Exercise15_16();
    frame.setSize(300, 300);
    frame.setTitle("Exercise15_16");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public Exercise15_16() {
    add(new MyPanel());
  }

  class MyPanel extends JPanel {
    MyPanel() {
      this.setFont(new Font("TimesRoman", Font.BOLD, 20));
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      FontMetrics fm = g.getFontMetrics();

      g.drawString("Java is fun", 40, 40);
      this.setToolTipText("Ascent: " + fm.getAscent() + " " +
        "Descent: " + fm.getDescent() + " " +
        "Leading: " + fm.getLeading() + " " +
        "string width: " + fm.stringWidth("Java is fun"));
    }
  }
}

