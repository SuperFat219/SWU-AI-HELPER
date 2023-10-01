import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise35_14 extends JApplet {
  private String[] monthNames = new DateFormatSymbols().getMonths();
  private JSpinner jspMonth = new JSpinner
    (new SpinnerListModel(Arrays.asList(monthNames).subList(0, 12)));
  private JSpinner jspYear =
    new JSpinner(new SpinnerNumberModel(2004, 1, 3000, 1));
  private CalendarPanel calendarPanel = new CalendarPanel();

  public Exercise35_14() {
    JPanel panel = new JPanel(new GridLayout(1, 2));
    panel.add(jspMonth);
    panel.add(jspYear);
    add(panel, BorderLayout.NORTH);
    add(calendarPanel, BorderLayout.CENTER);
    updateMonthYear();

    // Set editor for year
    JSpinner.NumberEditor yearEditor =
      new JSpinner.NumberEditor(jspYear, "####");
    jspYear.setEditor(yearEditor);

    jspMonth.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        updateMonthYear();
      }
    });

    jspYear.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        updateMonthYear();
      }
    });
  }

  /** Update the calendar */
  private void updateMonthYear() {
    // Get current month and year in int
    int month = ((SpinnerListModel)jspMonth.getModel()).
      getList().indexOf(jspMonth.getValue());
    int year = ((Integer)jspYear.getValue()).intValue();

    calendarPanel.setMonth(month);
    calendarPanel.setYear(year);
  }

  public static void main(String[] args) {
    Exercise35_14 applet = new Exercise35_14();
    JFrame frame = new JFrame();
    frame.setTitle("Calendar");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(600, 160);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
