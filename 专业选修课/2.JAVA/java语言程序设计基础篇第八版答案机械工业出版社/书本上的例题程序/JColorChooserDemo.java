import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Color;

public class JColorChooserDemo extends javax.swing.JApplet {
  public void init() {
    Color color = JColorChooser.showDialog(this, "Choose a color",
      Color.YELLOW);
  }

  public static void main(String[] args) {
    JColorChooserDemo applet = new JColorChooserDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestColorDialog");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
