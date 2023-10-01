import javax.swing.*;
import java.awt.*;

public class Exercise15_26 extends JFrame {
  public Exercise15_26() {
    MessagePanel m1 = new MessagePanel("Java");
    MessagePanel m2 = new MessagePanel("HTML");
    MessagePanel m3 = new MessagePanel("Tomcat");
    MessagePanel m4 = new MessagePanel("PHP");

    m1.setCentered(true);
    m2.setCentered(true);
    m3.setCentered(true);
    m4.setCentered(true);

    m1.setBackground(Color.white);
    m2.setBackground(Color.cyan);
    m3.setBackground(Color.white);
    m4.setBackground(Color.green);

    Font font = new Font("TimezRoman", Font.ITALIC, 14);

    m1.setFont(font);
    m2.setFont(font);
    m3.setFont(font);
    m4.setFont(font);

    JPanel p = new JPanel(new GridLayout(3, 1));
    p.add(m2);
    p.add(m3);
    p.add(m4);

    add(m1, BorderLayout.CENTER);
    add(p, BorderLayout.EAST);
  }

  public static void main(String[] args) {
    Exercise15_26 frame = new Exercise15_26();
    frame.setSize(400, 400);
    frame.setTitle("Exercise15_26");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
