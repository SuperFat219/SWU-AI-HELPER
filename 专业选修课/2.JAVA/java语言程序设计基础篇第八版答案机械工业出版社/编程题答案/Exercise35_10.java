import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class Exercise35_10 extends JApplet {
  private JList jlstCityClock = new JList(new Object[][] { {"London",
                                          "Europe/London"}, {"New York", "EST"},
                                          {"Chicago", "CST"}, {"Denver", "MST"},
                                          {"San Francisco", "PST"}
  });
  private WorldClock clock = new WorldClock();
  private JLabel jlblLabel = new JLabel("", JLabel.CENTER);

  public Exercise35_10() {
    jlstCityClock.setCellRenderer(new Exercise33_10CellRenderer());
    JSplitPane jSplitPane = new JSplitPane();
    jSplitPane.setLeftComponent(jlstCityClock);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(jlblLabel, BorderLayout.NORTH);
    panel.add(clock, BorderLayout.CENTER);

    jSplitPane.setRightComponent(panel);
    add(jSplitPane, BorderLayout.CENTER);

    jlstCityClock.setVisibleRowCount(1);
    // Register listener
    jlstCityClock.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        Object[] pair = (Object[])jlstCityClock.getSelectedValue();
        String city = (String)pair[0];
        String timeZone = (String)pair[1];
        jlblLabel.setText(city);
        clock.setTimeZone(TimeZone.getTimeZone(timeZone));
      }
    });
  }

  public static void main(String[] args) {
    Exercise35_10 applet = new Exercise35_10();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise35_10");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class Exercise33_10CellRenderer implements ListCellRenderer {
  private JPanel cellPainter = new JPanel();
  private WorldClock clock = new WorldClock();

  private JLabel jlblCity = new JLabel(" ", JLabel.CENTER);
  Border lineBorder =
    BorderFactory.createLineBorder(Color.black, 1);
  Border emptyBorder =
    BorderFactory.createEmptyBorder(2, 2, 2, 2);

  public Exercise33_10CellRenderer() {
    cellPainter.setLayout(new BorderLayout());
    cellPainter.add(clock, BorderLayout.CENTER);
    cellPainter.add(jlblCity, BorderLayout.NORTH);
    clock.setPreferredSize(new Dimension(140, 100));
    cellPainter.setPreferredSize(new Dimension(140, 120));
    cellPainter.setOpaque(true);
  }

  public Component getListCellRendererComponent
    (JList list, Object value, int index, boolean isSelected,
     boolean cellHasFocus) {
    //TODO: implement this javax.swing.ListCellRenderer method;
    Object[] pair = (Object[])value;
    String city = (String)pair[0];
    String timeZone = (String)pair[1];
    jlblCity.setText(city);
    clock.setTimeZone(TimeZone.getTimeZone(timeZone));

    if (isSelected) {
      cellPainter.setForeground(list.getSelectionForeground());
      cellPainter.setBackground(list.getSelectionBackground());
    }
    else {
      cellPainter.setForeground(list.getForeground());
      cellPainter.setBackground(list.getBackground());
    }

    cellPainter.setBorder(cellHasFocus ? lineBorder : emptyBorder);

    return cellPainter;
  }
}
