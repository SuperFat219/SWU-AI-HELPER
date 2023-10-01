// Define a panel for displaying image and text
// Image is displayed to fit in a fixed size in a panel
import java.awt.*;
import javax.swing.*;

class NewDescriptionPanel extends JPanel {
  // Use ImageViewer to display an image
  // instead of using the label to display an iamge icon
  private ImageViewer imageViewer = new ImageViewer();
  // Label for displaying a title
  private JLabel jlblTitle = new JLabel();
  // Text area for displaying text
  private JTextArea jtaTextDescription = new JTextArea(300, 300);

  // Default constructor
  public NewDescriptionPanel() {
    // Group image label and title label in a panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(imageViewer, BorderLayout.CENTER);
    panel.add(jlblTitle, BorderLayout.SOUTH);
    panel.setPreferredSize(new Dimension(175, 200));

    // Create a scroll pane to hold text area
    //JScrollPane scrollPane = new JScrollPane
      //(jtaTextDescription = new JTextArea());

    // Center the title on the label
    jlblTitle.setHorizontalAlignment(JLabel.CENTER);

    // Set the font for the title and text
    jlblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
    jtaTextDescription.setFont(new Font("Serif", Font.PLAIN, 14));

    // Set lineWrap and wrapStyleWord true for text area
    jtaTextDescription.setLineWrap(true);
    jtaTextDescription.setWrapStyleWord(true);

    // Set preferred size for the image label and scroll pane
    //scrollPane.setPreferredSize(new Dimension(200, 100));

    // Set BorderLayout for the whole panel, add panel and scrollpane
    setLayout(new BorderLayout());
    add(jtaTextDescription, BorderLayout.CENTER);
    add(panel, BorderLayout.WEST);
  }

  // Set the title
  public void setTitle(String title) {
    jlblTitle.setText(title);
  }

  // Set the image icon
  public void setImage(Image image) {
    imageViewer.setImage(image);
  }

  /**Set the text description*/
  public void setTextDescription(String text) {
    jtaTextDescription.setText(text);
  }

  public void setImageIcon(ImageIcon imageIcon) {
    imageViewer.setImage(imageIcon.getImage());
  }
}