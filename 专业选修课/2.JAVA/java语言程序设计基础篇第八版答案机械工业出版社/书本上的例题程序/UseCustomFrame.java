import javax.swing.*;

public class UseCustomFrame {  
  public static void main(String[] args) {
    CustomFrame frame1 = new CustomFrame(); // Create a frame
    frame1.setTitle("Window 1"); // Set frame title
    frame1.setLocation(200, 100); // Set frame location
    frame1.setVisible(true); // Display frame

    CustomFrame frame2 = new CustomFrame(); // Create another frame
    frame2.setTitle("Window 2"); // Set frame title
    frame2.setLocation(200, 300); // Set frame location
    frame2.setVisible(true); // Display frame
  }
}

class CustomFrame extends JFrame {
  public CustomFrame() {
    // Create a button with text OK 
    JButton jbtOK = new JButton("OK"); 

    // Create a label with text "Enter your name: "
    JLabel jlblName = new JLabel("Enter your name: "); 

    // Create a text field with text "Type Name Here"
    JTextField jtfName = new JTextField("Type Name Here"); 

    // Create a check box with text bold
    JCheckBox jchkBold = new JCheckBox("Bold"); 

    // Create a radio button with text red
    JRadioButton jrbRed = new JRadioButton("Red"); 

    // Create a combo box with choices red, green, and blue
    JComboBox jcboColor = new JComboBox(new String[]{"Red", 
      "Green", "Blue"}); 

    // Create a panel to group components
    JPanel panel = new JPanel();
    panel.add(jbtOK); // Add the button to the panel
    panel.add(jlblName); // Add the label to the panel
    panel.add(jtfName); // Add the text field to the panel
    panel.add(jchkBold); // Add the check box to the panel
    panel.add(jrbRed); // Add the radio button to the panel
    panel.add(jcboColor); // Add the combo box to the panel
    
    add(panel); // Add the panel to the frame
    setSize(450, 70); // Set frame's size
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
