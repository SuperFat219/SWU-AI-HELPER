// Exercise36_8.java: Traversing trees
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

public class Exercise36_8 extends JApplet {
  public Exercise36_8() {
    // Create a default tree
    JTree tree = new JTree();

    DefaultMutableTreeNode root =
      (DefaultMutableTreeNode)(tree.getModel().getRoot());

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(new JScrollPane(new JTree(root)));

    JTextArea jtaMessage = new JTextArea();
    jtaMessage.setWrapStyleWord(true);
    jtaMessage.setLineWrap(true);
    add(new JSplitPane(JSplitPane.VERTICAL_SPLIT,
      panel, new JScrollPane(jtaMessage)), BorderLayout.CENTER);

    String output = "";
    // Breadth-first traversal
    Enumeration enumeration = root.breadthFirstEnumeration();
    while (enumeration.hasMoreElements())
      output += enumeration.nextElement().toString() + " ";
    jtaMessage.append("\nBreath-first traversal from the root is "
      + output);

    // depth-first traversal
    output = "";
    enumeration = root.depthFirstEnumeration();
    while (enumeration.hasMoreElements())
      output += enumeration.nextElement().toString() + " ";
    jtaMessage.append("\nDepth-first traversal from the root is "
      + output);

    // Preorder traversal
    output = "";
    enumeration = root.preorderEnumeration();
    while (enumeration.hasMoreElements())
      output += enumeration.nextElement().toString() + " ";
    jtaMessage.append("\nPreorder traversal from the root is "
      + output);

    // Preorder traversal
    output = "";
    enumeration = root.postorderEnumeration();
    while (enumeration.hasMoreElements())
      output += enumeration.nextElement().toString() + " ";
    jtaMessage.append("\nPostorder traversal from the root is "
      + output);
  }

  public static void main(String[] args) {
    Exercise36_8 applet = new Exercise36_8();
    JFrame frame = new JFrame();
    frame.setTitle("Exercise36_8");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
