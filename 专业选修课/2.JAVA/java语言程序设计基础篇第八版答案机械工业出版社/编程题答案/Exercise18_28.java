import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Exercise18_28 extends JApplet {
  private JTextField jtfCenterx1 = new JTextField();
  private JTextField jtfCenterx2 = new JTextField();
  private JTextField jtfCentery1 = new JTextField();
  private JTextField jtfCentery2 = new JTextField();
  private JTextField jtfRadius1 = new JTextField();
  private JTextField jtfRadius2 = new JTextField();
  private JButton jbtRedraw = new JButton("Redraw Circles");
  
  private Circle2D circle1 = new Circle2D(50, 50, 30);
  private Circle2D circle2 = new Circle2D(90, 50, 20);

  private JLabel jlblStatus = new JLabel("Two circles intersect? " +
    (circle1.overlaps(circle2) ? "Yes" : "No"), JLabel.CENTER);

  private PaintPanel paintPanel = new PaintPanel();
  
  public Exercise18_28() {
    JPanel p1 = new JPanel(new GridLayout(3, 2));
    p1.setBorder(new TitledBorder("Enter circle 1 info"));
    p1.add(new JLabel("Center x:"));
    p1.add(jtfCenterx1);
    p1.add(new JLabel("Center y:"));
    p1.add(jtfCentery1);
    p1.add(new JLabel("Radius:"));
    p1.add(jtfRadius1);
    
    JPanel p2 = new JPanel(new GridLayout(3, 2));
    p2.setBorder(new TitledBorder("Enter circle 2 info"));
    p2.add(new JLabel("Center x:"));
    p2.add(jtfCenterx2);
    p2.add(new JLabel("Center y:"));
    p2.add(jtfCentery2);
    p2.add(new JLabel("Radius:"));
    p2.add(jtfRadius2);
    
    JPanel p3 = new JPanel(new GridLayout(1, 2));    
    p3.add(p1);
    p3.add(p2);
    
    JPanel p4 = new JPanel(new BorderLayout());    
    p4.add(p3, BorderLayout.CENTER);    
    p4.add(jbtRedraw, BorderLayout.SOUTH);
    
    add(jlblStatus, BorderLayout.NORTH);
    add(p4, BorderLayout.SOUTH);
    add(paintPanel, BorderLayout.CENTER);

    jbtRedraw.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        circle1.setX(Integer.parseInt(jtfCenterx1.getText()));
        circle1.setY(Integer.parseInt(jtfCentery1.getText()));
        circle1.setRadius(Integer.parseInt(jtfRadius1.getText()));
        circle2.setX(Integer.parseInt(jtfCenterx2.getText()));
        circle2.setY(Integer.parseInt(jtfCentery2.getText()));
        circle2.setRadius(Integer.parseInt(jtfRadius2.getText()));
        jlblStatus.setText("Two circles intersect? " +
            (circle1.overlaps(circle2) ? "Yes" : "No"));

        paintPanel.repaint();
      }
    });
  }

  class PaintPanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      g.drawOval((int)(circle1.getX() - circle1.getRadius()), 
        (int)(circle1.getY() - circle1.getRadius()),
        (int)(2 * circle1.getRadius()),
        (int)(2 * circle1.getRadius()));

      g.drawOval((int)(circle2.getX() - circle2.getRadius()), 
          (int)(circle2.getY() - circle2.getRadius()),
          (int)(2 * circle2.getRadius()),
          (int)(2 * circle2.getRadius()));
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise18_28");
    JApplet applet = new Exercise18_28();
    frame.add(applet);
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
