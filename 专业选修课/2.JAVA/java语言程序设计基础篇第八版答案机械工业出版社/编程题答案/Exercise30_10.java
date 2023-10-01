import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Exercise30_10 extends JApplet implements ActionListener {
  // Get the url for HTML files
  private URL urlFlowLayoutHTML =
    this.getClass().getResource("FlowLayout.html");
  private URL urlGridLayoutHTML =
    this.getClass().getResource("GridLayout.html");
  private URL urlBoxLayoutHTML =
    this.getClass().getResource("BoxLayout.html");

  private JRadioButton jrbFlowLayout =
    new JRadioButton("FlowLayout");
  private JRadioButton jrbGridLayout =
    new JRadioButton("GridLayout", true);
  private JRadioButton jrbBoxLayout =
    new JRadioButton("BoxLayout");

  private JPanel jpComponents = new JPanel();
  private JEditorPane jEditorPane1 = new JEditorPane();

  // Create layout managers
  private FlowLayout flowLayout = new FlowLayout();
  private GridLayout gridLayout = new GridLayout(2, 2, 3, 3);
  private BoxLayout boxLayout =
    new BoxLayout(jpComponents, BoxLayout.X_AXIS);

  public Exercise30_10() {
    // Create a box to hold radio buttons
    Box jpChooseLayout = Box.createVerticalBox();
    jpChooseLayout.add(jrbFlowLayout);
    jpChooseLayout.add(jrbGridLayout);
    jpChooseLayout.add(jrbBoxLayout);

    // Group radio buttons
    ButtonGroup btg = new ButtonGroup();
    btg.add(jrbFlowLayout);
    btg.add(jrbGridLayout);
    btg.add(jrbBoxLayout);

    // Add fours buttons to jpComponents
    jpComponents.add(new JButton("Button 1"));
    jpComponents.add(new JButton("Button 2"));
    jpComponents.add(new JButton("Button 3"));
    jpComponents.add(new JButton("Button 4"));

    // Create two split panes to hold jpChooseLayout, jpComponents,
    // and jEditorPane1
    JSplitPane jSplitPane2 = new JSplitPane(
      JSplitPane.VERTICAL_SPLIT, jpComponents,
      new JScrollPane(jEditorPane1));
    JSplitPane jSplitPane1 = new JSplitPane(
      JSplitPane.HORIZONTAL_SPLIT, jpChooseLayout, jSplitPane2);

    // Set GridLayout as default
    jpComponents.setLayout(gridLayout);
    jpComponents.validate();
    displayHTML(urlGridLayoutHTML);
    jEditorPane1.setEditable(false);

    add(jSplitPane1, BorderLayout.CENTER);

    // Register listeners
    jrbFlowLayout.addActionListener(this);
    jrbGridLayout.addActionListener(this);
    jrbBoxLayout.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jrbFlowLayout) {
      jpComponents.setLayout(flowLayout);
      displayHTML(urlFlowLayoutHTML);
    }
    else if (e.getSource() == jrbGridLayout) {
      jpComponents.setLayout(gridLayout);
      displayHTML(urlGridLayoutHTML);
    }
    else if (e.getSource() == jrbBoxLayout) {
      jpComponents.setLayout(boxLayout);
      displayHTML(urlBoxLayoutHTML);
    }

    jpComponents.validate();
  }

  /** Display an HTML file in EditorPane */
  private void displayHTML(java.net.URL url) {
    try {
      // Render the HTML file
      jEditorPane1.setPage(url);
    }
    catch (java.io.IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise30_10");

    // Create an instance of MortgageApplet
    Exercise30_10 applet = new Exercise30_10();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
