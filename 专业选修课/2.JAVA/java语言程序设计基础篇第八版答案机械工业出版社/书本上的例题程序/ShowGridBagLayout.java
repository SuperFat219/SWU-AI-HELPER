import java.awt.*;
import javax.swing.*;

public class ShowGridBagLayout extends JApplet {
  private JLabel jlbl = new JLabel(
    "Resize the Window and Study GridBagLayout", JLabel.CENTER);
  private JTextArea jta1 = new JTextArea("Text Area 1", 5, 15 );
  private JTextArea jta2 = new JTextArea("Text Area 2", 5, 15 );
  private JTextField jtf = new JTextField("JTextField");
  private JPanel jp = new JPanel();
  private JButton jbt1 = new JButton("Button 1");
  private JButton jbt2 = new JButton("Button 2");

  public ShowGridBagLayout() {
    // Set GridBagLayout in the container
    setLayout(new GridBagLayout());

    // Create an GridBagConstraints object
    GridBagConstraints gbConstraints = new GridBagConstraints();

    gbConstraints.fill = GridBagConstraints.BOTH;
    gbConstraints.anchor = GridBagConstraints.CENTER;

    Container container = getContentPane();

    // Place JLabel to occupy row 0 (the first row)
    addComp(jlbl, container, gbConstraints, 0, 0, 1, 4, 0, 0);

    // Place text area 1 in row 1 and 2, and column 0
    addComp(jta1, container, gbConstraints, 1, 0, 2, 1, 5, 1);

    // Place text area 2 in row 1 and column 3
    addComp(jta2, container, gbConstraints, 1, 3, 1, 1, 5, 1);

    // Place text field in row 2 and column 3
    addComp(jtf, container, gbConstraints, 2, 3, 1, 1, 5, 0);

    // Place JButton 1 in row 3 and column 1
    addComp(jbt1, container, gbConstraints, 3, 1, 1, 1, 5, 0);

    // Place JButton 2 in row 3 and column 2
    addComp(jbt2, container, gbConstraints, 3, 2, 1, 1, 5, 0);

    // Place Panel in row 1 and 2, and column 1 and 2
    jp.setBackground(Color.red);
    jp.setBorder(new javax.swing.border.LineBorder(Color.black));
    gbConstraints.insets = new Insets(10, 10, 10, 10);
    addComp(jp, container, gbConstraints, 1, 1, 2, 2, 10, 1);
  }

  /** Add a component to the container of GridBagLayout */
  private void addComp(Component c, Container container,
                       GridBagConstraints gbConstraints,
                       int row, int column,
                       int numberOfRows,  int numberOfColumns,
                       double weightx, double weighty) {
    // Set parameters
    gbConstraints.gridx = column;
    gbConstraints.gridy = row;
    gbConstraints.gridwidth = numberOfColumns;
    gbConstraints.gridheight = numberOfRows;
    gbConstraints.weightx = weightx;
    gbConstraints.weighty = weighty;

    // Add component to the container with the specified layout
    container.add(c, gbConstraints);
  }

  public static void main(String[] args) {
    ShowGridBagLayout applet = new ShowGridBagLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowGridBagLayout");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
