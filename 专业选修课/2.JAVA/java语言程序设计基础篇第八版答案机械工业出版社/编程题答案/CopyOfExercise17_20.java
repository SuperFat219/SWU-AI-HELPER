import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class CopyOfExercise17_20 extends JFrame implements ItemListener {
  // Declare an array of Strings for flag titles
  private String[] flagTitle = {"Canada", "China", "Denmark",
    "France", "Germany", "India", "Norway", "United Kingdom",
    "United States of America"};

  // Declare an ImageIcon array for the national flags of 9 countries
  private ImageIcon[] flagImage = new ImageIcon[9];

  // Declare an array of strings for flag descriptions
  private String[] flagDescription = new String[9];

  // Declare and create a description panel
  private DescriptionPanel descriptionPanel = new DescriptionPanel();

  // The combo list for selecting countries
  private JComboBox jcbo;

  // Base directory for the files
  String baseDirectory;

  // Main Method
  public static void main(String[] args) {
    CopyOfExercise17_20 frame = new CopyOfExercise17_20();
    frame.pack();
    frame.setTitle("Exercise16_20");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Default Constructor
  public CopyOfExercise17_20() {
    // Load images info flagImage array
    flagImage[0] = new ImageIcon("image/ca.gif");
    flagImage[1] = new ImageIcon("image/china.gif");
    flagImage[2] = new ImageIcon("image/denmark.gif");
    flagImage[3] = new ImageIcon("image/fr.gif");
    flagImage[4] = new ImageIcon("image/germany.gif");
    flagImage[5] = new ImageIcon("image/india.gif");
    flagImage[6] = new ImageIcon("image/norway.gif");
    flagImage[7] = new ImageIcon("image/uk.gif");
    flagImage[8] = new ImageIcon("image/us.gif");

    // Set text description
    for (int i=0; i<9; i++) {
      flagDescription[i] = getDescription(i);
    }

    // Create items into the combo box
    jcbo = new JComboBox(flagTitle);

    // Set the first country (Canada) for display
    setDisplay(0);

    // Add combo box and description panel to the list
    add(new JScrollPane(jcbo), BorderLayout.NORTH);
    add(descriptionPanel, BorderLayout.CENTER);

    // Register listener
    jcbo.addItemListener(this);
  }

  // Handle item selection
  public void itemStateChanged(ItemEvent e) {
    setDisplay(jcbo.getSelectedIndex());
  }

  // Set display information on the description panel
  public void setDisplay(int index) {
    descriptionPanel.setTitle(flagTitle[index]);
    descriptionPanel.setImageIcon(flagImage[index]);
    descriptionPanel.setDescription(flagDescription[index]);
  }

  private String getDescription(int i) {
    StringBuilder result = new StringBuilder();

    try {
      Scanner input = new Scanner(new File(
        "text/description" + i + ".txt"));

      while (input.hasNext()) {
        result.append(input.nextLine() + '\n');
      }
    }
    catch (IOException ex) {
      System.out.println(ex);
    }

    return result.toString();
  }
}
