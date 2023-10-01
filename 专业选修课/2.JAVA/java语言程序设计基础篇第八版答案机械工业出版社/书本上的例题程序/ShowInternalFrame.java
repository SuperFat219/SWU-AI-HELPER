import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowInternalFrame extends JApplet {
  // Create image icons
  private ImageIcon USIcon =
    new ImageIcon(getClass().getResource("/image/usIcon.gif"));
  private ImageIcon CanadaIcon =
    new ImageIcon(getClass().getResource("/image/caIcon.gif"));

  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenuItem jmiUS = new JMenuItem("US");
  private JMenuItem jmiCanada = new JMenuItem("Canada");
  private JLabel jlblImage = new JLabel(USIcon, JLabel.CENTER);

  // Create JDesktopPane to hold the internal frame
  private JDesktopPane desktop = new JDesktopPane();
  private JInternalFrame internalFrame =
    new JInternalFrame("US", true, true, true, true);

  public ShowInternalFrame() {
    desktop.add(internalFrame);

    this.setSize(new Dimension(400, 300));
    this.getContentPane().add(desktop, BorderLayout.CENTER);

    jlblImage.setIcon(USIcon);
    internalFrame.setFrameIcon(USIcon);

    internalFrame.add(jlblImage);
    internalFrame.setLocation(20, 20);
    internalFrame.setSize(100, 100);
    internalFrame.setVisible(true);

    JMenu jMenu1 = new JMenu("Flags");
    jMenuBar1.add(jMenu1);
    jMenu1.add(jmiUS);
    jMenu1.add(jmiCanada);

    this.setJMenuBar(jMenuBar1);

    jmiUS.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblImage.setIcon(USIcon);
        internalFrame.setFrameIcon(USIcon);
        internalFrame.setTitle("US");
      }
    });

    jmiCanada.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblImage.setIcon(CanadaIcon);
        internalFrame.setFrameIcon(CanadaIcon);
        internalFrame.setTitle("Canada");
      }
    });
  }

  public static void main(String[] args) {
    ShowInternalFrame applet = new ShowInternalFrame();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowInternalFrame");
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
