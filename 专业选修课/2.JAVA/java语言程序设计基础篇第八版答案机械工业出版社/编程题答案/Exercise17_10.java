import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise17_10 extends JFrame {
  // Declare a panel for displaying message
  private MessagePanel messagePanel;

  // Declare two buttons to move the message left and right
  private JButton jbtLeft, jbtRight;

  private JTextField jtfNewMessage = new JTextField(8);
  private JComboBox jcboInterval = new JComboBox();
  private JRadioButton jrbRed = new JRadioButton("Red");
  private JRadioButton jrbGreen = new JRadioButton("Green");
  private JRadioButton jrbBlue = new JRadioButton("Blue");
  private JCheckBox jchkCentered = new JCheckBox("Center");
  private JCheckBox jchkBold = new JCheckBox("Bold");
  private JCheckBox jchkItalic = new JCheckBox("Italic");

  // Font name
  private String fontName = "SansSerif";

  // Font style
  private int fontStyle = Font.PLAIN;

  // Font Size
  private int fontSize = 12;

  /** Main method */
  public static void main(String[] args) {
    Exercise17_10 frame = new Exercise17_10();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  /** Default constructor */
  public Exercise17_10() {
    setTitle("Exercise17_10");

    // Create a MessagePanel instance and set colors
    messagePanel = new MessagePanel("Welcome to Java");
    messagePanel.setBackground(Color.white);

    // Create Panel jpButtons to hold two Buttons "<=" and "right =>"
    JPanel jpButtons = new JPanel();
    jpButtons.setLayout(new FlowLayout());
    jpButtons.add(jbtLeft = new JButton());
    jpButtons.add(jbtRight = new JButton());

    // Set button text
    jbtLeft.setText("<=");
    jbtRight.setText("=>");

    // Set keyboard mnemonics
    jbtLeft.setMnemonic('L');
    jbtRight.setMnemonic('R');

    // Set icons
    //jbtLeft.setIcon(new ImageIcon("image/left.gif"));
    //jbtRight.setIcon(new ImageIcon("image/right.gif"));

    // Set toolTipText on the "<=" and "=>" buttons
    jbtLeft.setToolTipText("Move message to left");
    jbtRight.setToolTipText("Move message to right");

    // Place panels in the frame
    setLayout(new BorderLayout());
    add(messagePanel, BorderLayout.CENTER);
    add(jpButtons, BorderLayout.SOUTH);

    // Register listeners with the buttons
    jbtLeft.addActionListener(new Listener());
    jbtRight.addActionListener(new Listener());

    /** 1.Add a text field labeled “New Message.\uFFFD
     *    Upon typing a new message in the text field and pressing the Enter
     *    key, the new message is displayed in the message panel.
     */
    jpButtons.add(new JLabel("Enter a new message"));
    jpButtons.add(jtfNewMessage);

    jtfNewMessage.addActionListener(new Listener());

    /** 2. Add a combo box label “Interval\uFFFD that enables the user to select a
     * new interval for moving the message. The selection values range from
     * 10 to 100 with interval 5. The user can also type a new
     *  interval in the combo box.
     */
    jpButtons.add(new JLabel("Select an interval"));
    jpButtons.add(jcboInterval);
    for (int interval = 5; interval <= 100; interval += 5)
      jcboInterval.addItem(interval + "");

    jcboInterval.addActionListener(new Listener());

    /**
     * 3. Add three radio buttons that enable the user to select the foreground
     * color for the message as Red, Green, and Blue.
     */
    JPanel panel = new JPanel();
    add(panel, BorderLayout.NORTH);

    panel.add(jrbRed);
    panel.add(jrbGreen);
    panel.add(jrbBlue);
    ButtonGroup btg = new ButtonGroup();
    btg.add(jrbRed);
    btg.add(jrbGreen);
    btg.add(jrbBlue);
    jrbRed.addActionListener(new Listener());
    jrbGreen.addActionListener(new Listener());
    jrbBlue.addActionListener(new Listener());

    /**
     * 4. Add three check boxes that enable the user to center the message
     * and display it in italic or bold.
     */
    panel.add(jchkCentered);
    panel.add(jchkBold);
    panel.add(jchkItalic);
    jchkCentered.addActionListener(new Listener());
    jchkBold.addActionListener(new Listener());
    jchkItalic.addActionListener(new Listener());

    /**
     * 5. Add a border titled “Message Panel\uFFFD on the message panel.
     */
    messagePanel.setBorder(new TitledBorder("Message Panel"));
    jpButtons.setBorder(new TitledBorder("South Panel"));
    panel.setBorder(new TitledBorder("North Panel"));

    this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
  }

  class Listener implements ActionListener {
	  /** Handle button events */
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == jbtLeft) {
	      messagePanel.moveLeft();
	      messagePanel.repaint();
	    }
	    else if (e.getSource() == jbtRight) {
	      messagePanel.moveRight();
	      messagePanel.repaint();
	    }
	    else if (e.getSource() == jtfNewMessage) {
	      messagePanel.setMessage(jtfNewMessage.getText());
	      messagePanel.repaint();
	    }
	    else if (e.getSource() == jcboInterval) {
	      messagePanel.setInterval(
	        Integer.parseInt((String)(jcboInterval.getSelectedItem())));
	      messagePanel.repaint();
	    }
	    else if (e.getSource() == jrbRed) {
	      messagePanel.setForeground(Color.red);
	    }
	    else if (e.getSource() == jrbGreen) {
	      messagePanel.setForeground(Color.green);
	    }
	    else if (e.getSource() == jrbBlue) {
	      messagePanel.setForeground(Color.blue);
	    }
	    else if (e.getSource() == jchkCentered) {
	      if (jchkCentered.isSelected())
	        messagePanel.setCentered(true);
	      else
	        messagePanel.setCentered(false);
	
	      messagePanel.repaint();
	    }
	    else if ((e.getSource() == jchkBold) ||
	             (e.getSource() == jchkItalic)) {
	      fontStyle = Font.PLAIN;
	
	      // Determine a font style
	      if (jchkBold.isSelected())
	        fontStyle = fontStyle + Font.BOLD;
	      if (jchkItalic.isSelected())
	        fontStyle = fontStyle + Font.ITALIC;
	
	      // Set font for the message
	      messagePanel.setFont(new Font(fontName, fontStyle, fontSize));
	    }
	  }
  }
}

