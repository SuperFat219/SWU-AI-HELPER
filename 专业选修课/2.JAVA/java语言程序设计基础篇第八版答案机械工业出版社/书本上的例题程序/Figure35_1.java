// Figure35_1.java: Figures 24.1
import java.awt.*;
import javax.swing.*;

public class Figure35_1 extends JApplet {

  public static void main(String[] args) {
    JToolBar toolBar = new JToolBar();
    toolBar.setLayout(new GridLayout());
    toolBar.add(new JButton("ABC"));
    toolBar.add(new JButton("DEF"));

    JFrame frame = new JFrame("My Menus");

//    frame.getContentPane().add(toolBar, BorderLayout.SOUTH);

    frame.setSize(300, 200);
    frame.setVisible(true);
    JMenuBar jmb = new JMenuBar();
    frame.setJMenuBar(jmb);  // Attach a menu bar to a frame

    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");
    jmb.add(fileMenu);
    jmb.add(helpMenu);

    JMenuItem jmiNew, jmiOpen;
fileMenu.add(jmiNew = new JMenuItem("New"));
fileMenu.add(jmiOpen = new JMenuItem("Open"));
jmiNew.setIcon(new ImageIcon("image/new.gif"));
jmiOpen.setIcon(new ImageIcon("image/open.gif"));
helpMenu.setMnemonic('H');
fileMenu.setMnemonic('F');
jmiNew.setMnemonic('N');
jmiOpen.setMnemonic('O');
//    fileMenu.add(new JMenuItem("New"));
//fileMenu.add(new JMenuItem("Open"));
fileMenu.addSeparator();
fileMenu.add(new JMenuItem("Print"));
fileMenu.addSeparator();
fileMenu.add(new JMenuItem("Exit"));

    JMenu softwareHelpSubMenu = new JMenu("Software");
JMenu hardwareHelpSubMenu = new JMenu("Hardware");
helpMenu.add(softwareHelpSubMenu);
helpMenu.add(hardwareHelpSubMenu);
softwareHelpSubMenu.add(new JMenuItem("Unix"));
softwareHelpSubMenu.add(new JMenuItem("NT"));
softwareHelpSubMenu.add(new JMenuItem("Win95"));

helpMenu.add(new JCheckBoxMenuItem("Check it"));

    JMenu colorHelpSubMenu = new JMenu("Color");
    helpMenu.add(colorHelpSubMenu);

    JRadioButtonMenuItem jrbmiBlue, jrbmiYellow, jrbmiRed;
    colorHelpSubMenu.add(jrbmiBlue =
      new JRadioButtonMenuItem("Blue"));
    colorHelpSubMenu.add(jrbmiYellow =
      new JRadioButtonMenuItem("Yellow"));
    colorHelpSubMenu.add(jrbmiRed =
      new JRadioButtonMenuItem("Red"));

    ButtonGroup btg = new ButtonGroup();
//    btg.add(jrbmiBlue);
//    btg.add(jrbmiYellow);
//    btg.add(jrbmiRed);




  }
}
