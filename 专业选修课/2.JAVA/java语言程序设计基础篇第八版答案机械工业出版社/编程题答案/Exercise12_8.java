import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise12_8 extends JFrame {
  private JLabel jlblBlack = new JLabel("black");
  private JLabel jlblBlue = new JLabel("blue");
  private JLabel jlblCyan = new JLabel("cyan");
  private JLabel jlblGreen = new JLabel("green");
  private JLabel jlblMagenta = new JLabel("magenta");
  private JLabel jlblOrange = new JLabel("orange");

  public Exercise12_8() {
    setLayout(new GridLayout(2, 3));
    this.add(jlblBlack);
    this.add(jlblBlue);
    this.add(jlblCyan);
    this.add(jlblGreen);
    this.add(jlblMagenta);
    this.add(jlblOrange);

    jlblBlack.setBackground(Color.WHITE);
    jlblBlue.setBackground(Color.WHITE);
    jlblCyan.setBackground(Color.WHITE);
    jlblGreen.setBackground(Color.WHITE);
    jlblMagenta.setBackground(Color.WHITE);
    jlblOrange.setBackground(Color.WHITE);

    jlblBlack.setForeground(Color.BLACK);
    jlblBlue.setForeground(Color.BLUE);
    jlblCyan.setForeground(Color.CYAN);
    jlblGreen.setForeground(Color.GREEN);
    jlblMagenta.setForeground(Color.MAGENTA);
    jlblOrange.setForeground(Color.ORANGE);

    Font font = new Font("TImesRoman", Font.BOLD, 20);
    jlblBlack.setFont(font);
    jlblBlue.setFont(font);
    jlblCyan.setFont(font);
    jlblGreen.setFont(font);
    jlblMagenta.setFont(font);
    jlblOrange.setFont(font);

    Border border = new LineBorder(Color.YELLOW);
    jlblBlack.setBorder(border);
    jlblBlue.setBorder(border);
    jlblCyan.setBorder(border);
    jlblGreen.setBorder(border);
    jlblMagenta.setBorder(border);
    jlblOrange.setBorder(border);

    jlblBlack.setToolTipText("black");
    jlblBlue.setToolTipText("blue");
    jlblCyan.setToolTipText("cyan");
    jlblGreen.setToolTipText("green");
    jlblMagenta.setToolTipText("magenta");
    jlblOrange.setToolTipText("orange");
  }

  public static void main(String[] args) {
    Exercise12_8 frame = new Exercise12_8();
    frame.setTitle("Exercise12_8");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
