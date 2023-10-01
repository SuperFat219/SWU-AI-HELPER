import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Exercise18_30 extends JApplet {
  private JTextField jtfTriangle1P1x = new JTextField();
  private JTextField jtfTriangle1P1y = new JTextField();
  private JTextField jtfTriangle1P2x = new JTextField();
  private JTextField jtfTriangle1P2y = new JTextField();
  private JTextField jtfTriangle1P3x = new JTextField();
  private JTextField jtfTriangle1P3y = new JTextField();
  private JTextField jtfTriangle2P1x = new JTextField();
  private JTextField jtfTriangle2P1y = new JTextField();
  private JTextField jtfTriangle2P2x = new JTextField();
  private JTextField jtfTriangle2P2y = new JTextField();
  private JTextField jtfTriangle2P3y = new JTextField();
  private JTextField jtfTriangle2P3x = new JTextField();
  private JButton jbtRedraw = new JButton("Redraw Rectangles");
  
  private Triangle2D triangle1 = new Triangle2D(50, 50, 70, 50, 45, 60);
  private Triangle2D triangle2 = new Triangle2D(90, 50, 50, 70, 34, 40);

  private JLabel jlblStatus = new JLabel("Two triangles intersect? " +
    (triangle1.overlaps(triangle2) ? "Yes" : "No"), JLabel.CENTER);

  private PaintPanel paintPanel = new PaintPanel();
  
  public Exercise18_30() {
    JPanel p1 = new JPanel(new GridLayout(6, 2));
    p1.setBorder(new TitledBorder("Enter triangle 1 info"));
    p1.add(new JLabel("p1.x:"));
    p1.add(jtfTriangle1P1x);
    p1.add(new JLabel("p1.y:"));
    p1.add(jtfTriangle1P1y);
    p1.add(new JLabel("p2.x:"));
    p1.add(jtfTriangle1P2x);
    p1.add(new JLabel("p2.y:"));
    p1.add(jtfTriangle1P2y);
    p1.add(new JLabel("p3.x:"));
    p1.add(jtfTriangle1P3x);
    p1.add(new JLabel("p3.y:"));
    p1.add(jtfTriangle1P3y);
    
    JPanel p2 = new JPanel(new GridLayout(6, 2));
    p2.setBorder(new TitledBorder("Enter triangle 2 info"));
    p2.add(new JLabel("p1.x:"));
    p2.add(jtfTriangle2P1x);
    p2.add(new JLabel("p1.y:"));
    p2.add(jtfTriangle2P1y);
    p2.add(new JLabel("p2.x"));
    p2.add(jtfTriangle2P2x);
    p2.add(new JLabel("p2.y"));
    p2.add(jtfTriangle2P2y);
    p2.add(new JLabel("p3.x"));
    p2.add(jtfTriangle2P3x);
    p2.add(new JLabel("p3.y"));
    p2.add(jtfTriangle2P3y);    
    JPanel p3 = new JPanel(new GridLayout(1, 2));    
    p3.add(p1);
    p3.add(p2);
    
    JPanel p4 = new JPanel(new BorderLayout());    
    p4.add(p3, BorderLayout.CENTER);    
    p4.add(jbtRedraw, BorderLayout.SOUTH);
    
    add(jlblStatus, BorderLayout.NORTH);
    add(p4, BorderLayout.SOUTH);
    add(paintPanel, BorderLayout.CENTER);

    fillTextFieldWithInitialValues();
    
    jbtRedraw.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        triangle1.setP1(
          new MyPoint(Integer.parseInt(jtfTriangle1P1x.getText()),
          Integer.parseInt(jtfTriangle1P1y.getText())));
        triangle1.setP2(
          new MyPoint(Integer.parseInt(jtfTriangle1P2x.getText()),
          Integer.parseInt(jtfTriangle1P2y.getText())));
        triangle1.setP3(
            new MyPoint(Integer.parseInt(jtfTriangle1P3x.getText()),
            Integer.parseInt(jtfTriangle1P3y.getText())));
   
        triangle2.setP1(
          new MyPoint(Integer.parseInt(jtfTriangle2P1x.getText()),
          Integer.parseInt(jtfTriangle2P1y.getText())));
        triangle2.setP2(
          new MyPoint(Integer.parseInt(jtfTriangle2P2x.getText()),
          Integer.parseInt(jtfTriangle2P2y.getText())));
        triangle2.setP3(
            new MyPoint(Integer.parseInt(jtfTriangle2P3x.getText()),
            Integer.parseInt(jtfTriangle2P3y.getText())));
        
        jlblStatus.setText("Two triangles intersect? " +
            (triangle1.overlaps(triangle2) ? "Yes" : "No"));

        paintPanel.repaint();
      }
    });
  }

  private void fillTextFieldWithInitialValues() {
    jtfTriangle1P1x.setText((int)triangle1.getP1().getX() + "");
    jtfTriangle1P1y.setText((int)triangle1.getP1().getY() + "");
    jtfTriangle1P2x.setText((int)triangle1.getP2().getX() + "");
    jtfTriangle1P2y.setText((int)triangle1.getP2().getY() + "");
    jtfTriangle1P3x.setText((int)triangle1.getP3().getX() + "");
    jtfTriangle1P3y.setText((int)triangle1.getP3().getY() + "");
    
    jtfTriangle2P1x.setText((int)triangle2.getP1().getX() + "");
    jtfTriangle2P1y.setText((int)triangle2.getP1().getY() + "");
    jtfTriangle2P2x.setText((int)triangle2.getP2().getX() + "");
    jtfTriangle2P2y.setText((int)triangle2.getP2().getY() + "");
    jtfTriangle2P3x.setText((int)triangle2.getP3().getX() + "");
    jtfTriangle2P3y.setText((int)triangle2.getP3().getY() + "");
  }
  
  class PaintPanel extends JPanel {
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      displayTriangle(g, triangle1);
      displayTriangle(g, triangle2);
    }
    
    private void displayTriangle(Graphics g, Triangle2D t) {
      g.drawLine(
        (int)(t.getP1().getX()), 
        (int)(t.getP1().getY()),
        (int)(t.getP2().getX()), 
        (int)(t.getP2().getY()));

      g.drawLine(
          (int)(t.getP1().getX()), 
          (int)(t.getP1().getY()),
          (int)(t.getP3().getX()), 
          (int)(t.getP3().getY()));

      g.drawLine(
          (int)(t.getP2().getX()), 
          (int)(t.getP2().getY()),
          (int)(t.getP3().getX()), 
          (int)(t.getP3().getY()));
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise18_30");
    JApplet applet = new Exercise18_30();
    frame.add(applet);
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
