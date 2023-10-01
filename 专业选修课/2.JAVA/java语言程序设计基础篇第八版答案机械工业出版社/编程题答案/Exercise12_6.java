import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Exercise12_6 extends JFrame {
  public Exercise12_6() {
    JLabel jlbl1 = new JLabel(new ImageIcon("image/uk.gif"));
    JLabel jlbl2 = new JLabel(new ImageIcon("image/fr.gif"));
    JLabel jlbl3 = new JLabel(new ImageIcon("image/norway.gif"));
    JLabel jlbl4 = new JLabel(new ImageIcon("image/my.jpg"));

    jlbl1.setBorder(new LineBorder(Color.BLUE, 1));
    jlbl2.setBorder(new LineBorder(Color.BLUE, 1));
    jlbl3.setBorder(new LineBorder(Color.BLUE, 1));
    jlbl4.setBorder(new LineBorder(Color.BLUE, 1));

    setLayout(new GridLayout(2, 2));
    add(jlbl1);
    add(jlbl2);
    add(jlbl3);
    add(jlbl4);
  }

  public static void main(String[] args) {
    Exercise12_6 frame = new Exercise12_6();
    frame.setTitle("Exercise12_6");
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
