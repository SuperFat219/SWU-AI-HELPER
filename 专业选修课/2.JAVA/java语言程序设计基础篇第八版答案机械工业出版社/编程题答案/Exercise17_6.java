import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise17_6 extends JFrame {
  private JTextField jtfMile = new JTextField(10);
  private JTextField jtfKilometer = new JTextField(10);

  // Main method
  public static void main(String[] args) {
    Exercise17_6 frame = new Exercise17_6();
    frame.pack();
    frame.setTitle("Exercise17_6");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public Exercise17_6() {
    // Panel p1 to hold labels
    JPanel p1 = new JPanel(new GridLayout(2, 1));
    p1.add(new JLabel("Mile"));
    p1.add(new JLabel("Kilometer"));

    // Panel p2 to hold text fileds
    JPanel p2 = new JPanel(new GridLayout(2, 1));
    p2.add(jtfMile);
    p2.add(jtfKilometer);

    // Panel p to hold the label panel and text field panel
    JPanel p = new JPanel(new BorderLayout(5, 5));
    p.setBorder(new EmptyBorder(5, 5, 5, 5));
    p.add(p1, BorderLayout.WEST);
    p.add(p2, BorderLayout.CENTER);

    jtfMile.setHorizontalAlignment(JTextField.RIGHT);
    jtfKilometer.setHorizontalAlignment(JTextField.RIGHT);

    // Add p to the frame
    add(p);

    // Register listener
    jtfMile.addActionListener(new Listener());
    jtfKilometer.addActionListener(new Listener());
  }

  class Listener implements ActionListener {
    // Handle ActionEvent
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == jtfMile) {
        double mile = new Double(jtfMile.getText().trim()).doubleValue();
        double kilometer = mile / 0.6241;
        jtfKilometer.setText(new Double(kilometer).toString());
      }
      else {
        double kilometer = new Double(jtfKilometer.getText().trim()).doubleValue();
        double mile = 0.6241 * kilometer;
        jtfMile.setText(new Double(mile).toString());
      }
    }
  }
}

