import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Exercise35_8 extends JApplet {
  private final static int NUMBER_OF_NATIONS = 7;
  private String[] nations = new String[]
    {"Denmark", "Germany", "China", "India", "Norway", "UK", "US"};
  private ImageIcon[] icons = new ImageIcon[NUMBER_OF_NATIONS];
  private ImageIcon[] bigIcons = new ImageIcon[NUMBER_OF_NATIONS];

  // Create a list model
  private DefaultListModel listModel = new DefaultListModel();

  // Create a list using the list model
  private JList jlstNations = new JList(listModel);

  // Create a list cell renderer
  private ListCellRenderer renderer = new MyListCellRenderer();

  // Create a split pane
  private JSplitPane jSplitPane1 = new JSplitPane();

  // Create a label for displaying iamge
  private JLabel jlblImage = new JLabel("", JLabel.CENTER);

  /** Construct ListCellRenderer */
  public Exercise35_8() {
    // Load small and large image icons
    for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
      icons[i] = new ImageIcon(getClass().getResource(
        "image/flagIcon" + i + ".gif"));
      listModel.addElement(new Object[]{icons[i], nations[i]});

      bigIcons[i] = new ImageIcon(getClass().getResource(
        "image/flag" + i + ".gif"));
    }

    // Set list cell renderer
    jlstNations.setCellRenderer(renderer);
    jlstNations.setPreferredSize(new Dimension(200, 200));
    jSplitPane1.setLeftComponent(new JScrollPane(jlstNations));
    jSplitPane1.setRightComponent(jlblImage);
    jlstNations.setSelectedIndex(0);
    jlblImage.setIcon(bigIcons[0]);
    add(jSplitPane1, BorderLayout.CENTER);

    // Register listener
    jlstNations.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        Object[] pair = (Object[])(jlstNations.getSelectedValue());
        jlblImage.setIcon((ImageIcon)pair[0]);
      }
    });
  }

  public static void main(String[] args) {
    Exercise35_8 applet = new Exercise35_8();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise35_8");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
