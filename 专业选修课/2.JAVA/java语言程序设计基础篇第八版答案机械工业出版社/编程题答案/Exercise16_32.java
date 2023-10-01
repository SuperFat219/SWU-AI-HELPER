import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Exercise16_32 extends JFrame {
  private PointsPanel paintPanel = new PointsPanel();

  public Exercise16_32() {
    add(paintPanel);
  }

  class PointsPanel extends JPanel {
    final static int RADIUS = 3;
    ArrayList list = new ArrayList();

    public PointsPanel() {
      this.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (e.isMetaDown()) 
            removePoint(e.getPoint());
          else
            list.add(e.getPoint());
          repaint();
        }
      });
    }

    /** Remove a point in list if it contains the specified point */
    private void removePoint(java.awt.Point point) {
      for (int i = 0; i < list.size(); i++) {
        double dis = distance(((Point)list.get(i)).x, ((Point)list.get(i)).y, point.x, point.y);
        if (dis <= RADIUS) {
          list.remove(i);
          break;
        }
      }
    }

    /** Compute the distance between two points (x1, y1) and (x2, y2) */
    private double distance(double x1, double y1, double x2, double y2) {
      return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /** Paint message */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (int i = 0; i < list.size(); i++) {
        g.drawOval(((Point) list.get(i)).x - RADIUS, ((Point) list.get(i)).y
          - RADIUS, 2 * RADIUS, 2 * RADIUS);
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new Exercise16_32();
    frame.setTitle("Exercise16_32");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}