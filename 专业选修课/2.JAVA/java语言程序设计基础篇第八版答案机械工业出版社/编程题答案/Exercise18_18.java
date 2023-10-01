import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise18_18 extends JApplet {
  // Description panel to display photo, name and text
  private NewDescriptionPanel descriptionPanel =
    new NewDescriptionPanel();

  // Number of the slides specified in the HTML page
  private int numOfCountries = 0;

  private ImageIcon[] imageIcon;
  private String[] text;
  private String[] name;
  int current = 0;

  private Timer timer = new Timer(10000, 
  	new ActionListener() {
    // Run a slide show
    public void actionPerformed(ActionEvent e) {
      current = current + 1;
      show(current % numOfCountries);	// Show the current slide
    }
  });

  // Initialize the applet
  public void init() {
    // Get the numOfCountries parameter from the HTML page
    numOfCountries =
      Integer.valueOf(getParameter("numOfCountries")).intValue();
    System.out.println("number of students " + numOfCountries);

    // Create arrays for imageIcon, text and name
    imageIcon = new ImageIcon[numOfCountries];
    text = new String[numOfCountries];
    name = new String[numOfCountries];

    // Initialize text, name and imageIcon
    for (int i = 0; i < numOfCountries; i++) {
      text[i] = getParameter("description" + i);
      name[i] = getParameter("name" + i);
      imageIcon[i] = new ImageIcon(
        getClass().getResource("image/flag" + i + ".gif"));
    }

    // Set applet layout and add text area and panel
    add(descriptionPanel, BorderLayout.CENTER);
    show(current % numOfCountries);

    // Create the thread
    timer.start();
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  private void show(int current) {
    // Show text
    descriptionPanel.setTextDescription(text[current]);

    // Show name
    descriptionPanel.setTitle(name[current]);

    // Show imageIcon
    descriptionPanel.setImageIcon(imageIcon[current]);
  }
}
