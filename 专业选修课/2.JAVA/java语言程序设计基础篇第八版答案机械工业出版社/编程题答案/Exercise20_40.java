import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise20_40 extends JApplet {
  private JTextField jtfDepth = new JTextField(5);
  private TreePanel treePanel = new TreePanel();
  
  public Exercise20_40() {
    // Panel to hold label, text field, and a button
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter the depth: "));
    panel.add(jtfDepth);
    jtfDepth.setHorizontalAlignment(SwingConstants.RIGHT);

    // Add a H-tree panel to the applet
    add(treePanel);
    add(panel, BorderLayout.SOUTH);


  }
  
  class TreePanel extends JPanel {


    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (int x = -2; x < 2; x += 0.005)
    	for (int y = -2; y < 2; y += 0.005) {
    	  for (int count = 0; count < 50; count++) {
    		Complex temp = new Complex(x, y).add(new Complex(x, y));
    		if (temp.abs() > 2) {
    			
    		}
    	  }
    	}
    }
    
    

  }
  
  /** Main method */
  public static void main(String[] args) {
    JApplet applet = new Exercise20_40();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise20_40");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}
