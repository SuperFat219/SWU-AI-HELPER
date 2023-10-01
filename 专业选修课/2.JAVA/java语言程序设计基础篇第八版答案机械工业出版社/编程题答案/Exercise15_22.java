import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.FontMetrics;

public class Exercise15_22 extends JFrame {
  public Exercise15_22() {
    add(new StopSignPanel());
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise15_22 frame = new Exercise15_22();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setTitle("Exercise15_22");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 250);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

// Draw a polygon in the panel
class StopSignPanel extends JPanel {
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int xCenter = getWidth() / 2;
    int yCenter = getHeight() / 2;
    int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);

    // Create a Polygon object
    Polygon polygon = new Polygon();

    // Add points to the polygon
    for (int i = 0; i < 8; i++) {
      polygon.addPoint((int)(xCenter + radius *
        Math.cos(i * 2 * Math.PI / 8 + 2 * Math.PI / 16)), (int)(yCenter - radius *
        Math.sin(i * 2 * Math.PI / 8 + 2 * Math.PI / 16)));
    }

    // Draw the polygon
    g.setColor(Color.RED);
    g.fillPolygon(polygon);

    g.setFont(new Font("Arial Black", Font.BOLD, 30));

    // Get font metrics for the current font
    FontMetrics fm = g.getFontMetrics();

    // Find the center location to display
    int stringWidth = fm.stringWidth("STOP");
    int stringAscent = fm.getAscent();

    // Get the position of the leftmost character in the baseline
    int xCoordinate = getWidth() / 2 - stringWidth / 2;
    int yCoordinate = getHeight() / 2 + stringAscent / 2;

    g.setColor(Color.WHITE);
    g.drawString("STOP", xCoordinate, yCoordinate);
  }
}
