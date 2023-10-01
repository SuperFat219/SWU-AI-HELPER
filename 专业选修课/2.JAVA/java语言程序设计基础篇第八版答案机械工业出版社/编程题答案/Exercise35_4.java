import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;

public class Exercise35_4 extends JApplet {
  // Create a JSpinner
  private JSpinner spinner =
    new JSpinner(new SpinnerPowerNumberModel());

  // Create a JLabel
  private JLabel label = new JLabel("", JLabel.CENTER);

  public Exercise35_4() {
    // Add spinner and label to the UI
    add(spinner, BorderLayout.NORTH);
    add(label, BorderLayout.CENTER);

    // Register and create a listener
    spinner.addChangeListener(new ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent e) {
        label.setText("Previous value: " + spinner.getPreviousValue()
          + " Current value: " + spinner.getValue()
          + " Next value: " + spinner.getNextValue());
      }
    });
  }

  public static void main(String[] args) {
    javax.swing.JFrame frame = new javax.swing.JFrame(
        "Exercise35_4");

    Exercise35_4 applet = new Exercise35_4();

    // Add the applet instance to the frame
    frame.add(applet, java.awt.BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Invoke init and start
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class SpinnerPowerNumberModel extends AbstractSpinnerModel {
  private int value = 2;

  public Object getPreviousValue() {
    if (value / 2 == 0)
      return null;

    return new Integer(value / 2);
  }

  public Object getNextValue() {
    return new Integer(value * 2);
  }

  public Object getValue() {
    return new Integer(value);
  }

  public void setValue(Object value) {
    if ((value == null) || !(value instanceof Number)) {
        throw new IllegalArgumentException("illegal value");
    }
    if (!new Integer(this.value).equals(value)) {
        this.value = ((Number)value).intValue();
        fireStateChanged();
    }
  }
}
