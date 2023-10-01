import java.applet.Applet;
import java.awt.*;
import javax.swing.*;

public class Exercise18_2 extends JApplet {
  private String message; // Message to display
  private int x = 20; // Default x coordinate
  private int y = 20; // Default y coordinate
  private String color;
  private String fontName;
  private int fontSize = 20;

  // Initialize the applet
  public void init() {
    // Get parameter values from the HTML file
    message = getParameter("MESSAGE");
    x = Integer.parseInt(getParameter("X"));
    y = Integer.parseInt(getParameter("Y"));
    color = getParameter("COLOR");
    fontName = getParameter("FONTNAME");
    fontSize = Integer.parseInt(getParameter("FONTSIZE"));

    // Create a message panel
    MessagePanel messagePanel = new MessagePanel(message);
    messagePanel.setXCoordinate(x);
    messagePanel.setYCoordinate(y);

    if (color.equals("red")) {
      messagePanel.setForeground(Color.red);
    }
    else if (color.equals("yellow"))
      messagePanel.setForeground(Color.yellow);
    else if (color.equals("green"))
      messagePanel.setForeground(Color.green);

    messagePanel.setFont(new Font(fontName, Font.BOLD, fontSize));

    // Add the message panel to the applet
    add(messagePanel);
  }
}
