import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test3 extends JFrame {
  FlagPanel canvas = new FlagPanel();
  FlagPanel canvas1 = new FlagPanel();
  JButton jbtStop = new JButton("Stop");
  ActionListener listener = new MyListenerClass();
  Timer timer = new Timer(50, listener);
  
  public Test3() {
    timer.start();
    
    this.add(canvas, BorderLayout.EAST);
    this.add(canvas1, BorderLayout.WEST);
    this.add(jbtStop, BorderLayout.SOUTH);
    
    jbtStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        timer.stop();
      }
    });
  }

  /**Main method*/
  public static void main(String[] args) {
    // Create a frame
    Test3 frame = new Test3();
    frame.setTitle("Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
  
  class MyListenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      canvas.repaint();
      canvas1.repaint();
    }
  }
  
  class FlagPanel extends JPanel {
    private ImageIcon imageIcon = new ImageIcon("image/us.gif");
    private Image image = imageIcon.getImage();
    
    private int y = 0;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(image, 20, y, this);
      y = y - 5;
      if (y < 0) y = getHeight();
    }
    
    public Dimension getPreferredSize() {
      return new Dimension(200, 300);
    }
  }
}
