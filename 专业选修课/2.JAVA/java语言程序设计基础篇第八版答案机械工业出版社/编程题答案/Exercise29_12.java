import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise29_12 extends JApplet implements Runnable {
  boolean isStandalone = false;
  JProgressBar jProgressBar1 = new JProgressBar();
  FlowLayout flowLayout1 = new FlowLayout();

  Thread thread;

  /**Construct the applet*/
  public Exercise29_12() {
  }

  /**Initialize the applet*/
  public void init() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    setSize(new Dimension(400,300));
    setLayout(flowLayout1);
    add(jProgressBar1, null);

    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(500);
      }
      catch (InterruptedException ex) { }

      jProgressBar1.setValue((int)(jProgressBar1.getMaximum()*
        Math.random()));
    }
  }

  /**Main method*/
  public static void main(String[] args) {
    Exercise29_12 applet = new Exercise29_12();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setTitle("Exercise29_12");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // static initializer for setting look & feel
  static {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {}
  }
}

