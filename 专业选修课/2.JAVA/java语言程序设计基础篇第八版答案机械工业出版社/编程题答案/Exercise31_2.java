import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Exercise31_2 extends JApplet implements ActionListener {
  private Locale[] availableLocales = Locale.getAvailableLocales();
  private String[] availableTimeZones = TimeZone.getAvailableIDs();
  private DateFormat dateFormat = DateFormat.getDateInstance();
  private DateFormat timeFormat = DateFormat.getTimeInstance();
  private Date date = new Date();
  private JTextField jtfDate = new JTextField(dateFormat.format(date));
  private JTextField jtfTime = new JTextField(timeFormat.format(date));
  private JComboBox jcboLocale = new JComboBox(availableLocales);
  private JComboBox jcboTimeZone = new JComboBox(availableTimeZones);
  private JComboBox jcboDateStyle = new JComboBox(
    new String[] {"Full", "LONG", "MEDIUM", "SHORT"});
  private JComboBox jcboTimeStyle = new JComboBox(
    new String[] {"Full", "LONG", "MEDIUM", "SHORT"});

  private Locale locale = Locale.US;
  private String timeZone = TimeZone.getDefault().getID();

  private int dateStyle = DateFormat.FULL;
  private int timeStyle = DateFormat.FULL;

  public Exercise31_2() {
    JPanel p1 = new JPanel(new GridLayout(1, 2));
    JPanel p1_1 = new JPanel(new BorderLayout());
    JPanel p1_2 = new JPanel(new BorderLayout());
    p1_1.add(new JLabel("Date:"), BorderLayout.WEST);
    p1_1.add(jtfDate, BorderLayout.CENTER);
    p1_2.add(new JLabel("Time:"), BorderLayout.WEST);
    p1_2.add(jtfTime, BorderLayout.CENTER);
    p1.add(p1_1);
    p1.add(p1_2);

    JPanel p2 = new JPanel();
    p2.add(new JLabel("Locale:"));
    p2.add(jcboLocale);
    p2.add(new JLabel("Time Zone:"));
    p2.add(jcboTimeZone);

    JPanel p3 = new JPanel();
    p3.add(new JLabel("Date Style:"));
    p3.add(jcboDateStyle);
    p3.add(new JLabel("Time Style:"));
    p3.add(jcboTimeStyle);

    this.setLayout(new GridLayout(3, 1));
    this.add(p1);
    this.add(p2);
    this.add(p3);

    jcboLocale.addActionListener(this);
    jcboTimeZone.addActionListener(this);
    jcboDateStyle.addActionListener(this);
    jcboTimeStyle.addActionListener(this);
  }

  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Show Date and Time");

    // Create an instance of the applet
    JApplet applet = new Exercise31_2();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Display the frame
    frame.setSize(450, 150);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jcboLocale) {
      locale = availableLocales[jcboLocale.getSelectedIndex()];
    }
    else if (e.getSource() == jcboTimeZone) {
      timeZone = availableTimeZones[jcboTimeZone.getSelectedIndex()];
    }
    else if (e.getSource() == jcboDateStyle) {
      dateStyle = jcboDateStyle.getSelectedIndex();
    }
    else if (e.getSource() == jcboTimeStyle) {
      timeStyle = jcboTimeStyle.getSelectedIndex();
    }

    dateFormat = DateFormat.getDateInstance(dateStyle, locale);
    timeFormat = DateFormat.getTimeInstance(timeStyle, locale);
    dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
    timeFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

    jtfDate.setText(dateFormat.format(new Date()));
    jtfTime.setText(timeFormat.format(new Date()));
  }
}
