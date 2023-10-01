// Exercise31_6.java: Convert currency
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.*;
import java.util.*;

public class Exercise31_6 extends JApplet implements ActionListener {
  // Text fields for US dollars, Canadian dollars, German marks
  // and British pounds
  JTextField jtfUSDollars = new JTextField();
  JTextField jtfCanadianDollars = new JTextField();
  JTextField jtfGermanMarks = new JTextField();
  JTextField jtfBritishPounds = new JTextField();

  // Text fields for excahnge rates
  JTextField jtfCanadianDollarsRate = new JTextField();
  JTextField jtfGermanMarksRate = new JTextField();
  JTextField jtfBritishPoundsRate = new JTextField();

  // Button to convert currencies
  JButton jbtConvert = new JButton("Convert");

  // Number formater
  NumberFormat nfCanada = NumberFormat.getCurrencyInstance(Locale.CANADA);
  NumberFormat nfGerman = NumberFormat.getCurrencyInstance(Locale.GERMAN);
  NumberFormat nfUK = NumberFormat.getCurrencyInstance(Locale.UK);


  public void init() {
    // Panel p1 to hold the text field and button for US dollars
    JPanel p1 = new JPanel();
    p1.setLayout(new BorderLayout());
    p1.add(new JLabel("US Dollars"), BorderLayout.WEST);
    p1.add(jtfUSDollars, BorderLayout.CENTER);
    p1.add(jbtConvert, BorderLayout.EAST);
    p1.setBorder(new TitledBorder("Enter Dollar Amount"));

    // Panel p2 to hold the text field and button for US dollars
    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(4, 3));
    p2.add(new JLabel());
    p2.add(new JLabel("Exchange Rate"));
    p2.add(new JLabel("Converted Amount"));
    p2.add(new JLabel("Canadian Dollars"));
    p2.add(jtfCanadianDollarsRate);
    p2.add(jtfCanadianDollars);
    p2.add(new JLabel("German Marks"));
    p2.add(jtfGermanMarksRate);
    p2.add(jtfGermanMarks);
    p2.add(new JLabel("British Pounds"));
    p2.add(jtfBritishPoundsRate);
    p2.add(jtfBritishPounds);
    p2.setBorder(new TitledBorder("Display Exchange"));

    // Set the text fields properties
    jtfCanadianDollars.setEditable(false);
    jtfGermanMarks.setEditable(false);
    jtfBritishPounds.setEditable(false);
    jtfUSDollars.setHorizontalAlignment(JTextField.RIGHT);
    jtfCanadianDollars.setHorizontalAlignment(JTextField.RIGHT);
    jtfGermanMarks.setHorizontalAlignment(JTextField.RIGHT);
    jtfBritishPounds.setHorizontalAlignment(JTextField.RIGHT);
    jtfCanadianDollarsRate.setHorizontalAlignment(JTextField.RIGHT);
    jtfGermanMarksRate.setHorizontalAlignment(JTextField.RIGHT);
    jtfBritishPoundsRate.setHorizontalAlignment(JTextField.RIGHT);

    // Add panels to the frame
    add(p1, BorderLayout.NORTH);
    add(p2, BorderLayout.CENTER);

    // Register listener
    jbtConvert.addActionListener(this);
  }

  // Handle ActionEvent
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jbtConvert) {
      double USDollars = new Double(jtfUSDollars.getText().trim()).doubleValue();
      double rateCanada = new Double(jtfCanadianDollarsRate.getText().trim()).doubleValue();
      double rateGermany = new Double(jtfGermanMarksRate.getText().trim()).doubleValue();
      double rateBritan = new Double(jtfBritishPoundsRate.getText().trim()).doubleValue();

      jtfCanadianDollars.setText(nfCanada.format(USDollars*rateCanada));
      jtfGermanMarks.setText(nfGerman.format(USDollars*rateGermany));
      jtfBritishPounds.setText(nfUK.format(USDollars*rateBritan));
    }
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exchange Currencies");

    // Create an instance of the applet
    Exercise31_6 applet = new Exercise31_6();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
