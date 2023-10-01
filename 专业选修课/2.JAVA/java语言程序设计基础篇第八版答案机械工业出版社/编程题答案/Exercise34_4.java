import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise34_4 extends JApplet {
  // Create image icons
  private ImageIcon caImageIcon =
    new ImageIcon(getClass().getResource("/image/caIcon.gif"));
  private ImageIcon usImageIcon =
    new ImageIcon(getClass().getResource("/image/usIcon.gif"));

  private JLabel jlblImage = new JLabel(caImageIcon, JLabel.CENTER);
  private FlowLayout flowLayout = new FlowLayout();

  public Exercise34_4() {
    // Create actions
    Action caAction = new MyAction("CA", caImageIcon);
    Action usAction = new MyAction("US", usImageIcon);

    // Create menus
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jmenuFlag = new JMenu("Flags");
    setJMenuBar(jMenuBar1);
    jMenuBar1.add(jmenuFlag);

    // Add actions to the menu
    jmenuFlag.add(caAction);
    jmenuFlag.add(usAction);

    // Add actions to the toolbar
    JToolBar jToolBar1 = new JToolBar(JToolBar.VERTICAL);
    jToolBar1.setBorder(BorderFactory.createLineBorder(Color.red));
    jToolBar1.add(caAction);
    jToolBar1.add(usAction);

    // Add tool bar to the east and panel to the center
    add(jToolBar1, BorderLayout.EAST);
    add(jlblImage, BorderLayout.CENTER);
  }

  private class MyAction extends AbstractAction {
    String name;

    MyAction(String name, Icon icon) {
      super(name, icon);
      putValue(Action.SHORT_DESCRIPTION, " Select the " + name +
        " flag to display");
      this.name = name;
    }

    public void actionPerformed(ActionEvent e) {
      if (name.equals("CA"))
        jlblImage.setIcon(caImageIcon);
      else if (name.equals("US"))
        jlblImage.setIcon(usImageIcon);
    }
  }

  public static void main(String[] args) {
    Exercise34_4 applet = new Exercise34_4();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise34_4");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
