import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class BSTAnimation extends JApplet {
  private BinaryTree<Integer> tree = new BinaryTree<Integer>();

  public BSTAnimation() {
    tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(30);
    tree.insert(70);
    tree.insert(80);
    tree.insert(110);
    tree.insert(10); 
    
    add(new BinaryTreeDeleteAnimation(tree));
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("BSTAnimation");
    JApplet applet = new BSTAnimation();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class BinaryTreeDeleteAnimation extends JApplet {
  // BinaryTree is the same as in the text
  private BinaryTree<Integer> tree;
  private JButton jbtSearch = new JButton("Search");
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Delete");
  private JTextField jtfKey = new JTextField(5);
  private PaintTree paintTree = new PaintTree();
  
  public BinaryTreeDeleteAnimation(BinaryTree<Integer> tree) {
    this.tree = tree;
    setUI();
  }
  
  private void setUI() {
    setLayout(new BorderLayout());
    
    add(paintTree, BorderLayout.CENTER);   
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter a key: "));
    panel.add(jtfKey);
    panel.add(jbtSearch);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    add(panel, BorderLayout.SOUTH);

    jbtSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (!tree.search(key)) {
          JOptionPane.showMessageDialog(null, key + " is not in the tree");
        }
        else {
          paintTree.setOfHighlightesNodes.clear();
          timer = new Timer(1000, new AnimationListener(null, 0));
          paths = tree.path(key);       
          timer.start();
        }
      }
    });
    
    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (tree.search(key)) {
          JOptionPane.showMessageDialog(null, key + " is already in the tree");
        }
        else {
          paintTree.setOfHighlightesNodes.clear();
          paths = tree.path(key);       
          timer = new Timer(1000, new AnimationListener(key, 1));
          timer.start();
        }
      }
    });
    
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (!tree.search(key)) {
          JOptionPane.showMessageDialog(null, key + " is not in the tree");
        }
        else {
          paintTree.setOfHighlightesNodes.clear();
          paths = tree.path(key);       
          timer = new Timer(1000, new AnimationListener(key, 2));
          timer.start();
        }
      }
    });
  }
  
  Timer timer;
  
  protected ArrayList<BinaryTree.TreeNode<Integer>> paths;
  
  class AnimationListener implements ActionListener {
    int mode = 0; // 0 for search, 1 for insert, and 2 for delete
    Integer key = null;
    
    public AnimationListener(Integer key, int mode) {
      this.key = key;
      this.mode = mode;
    }
    
    public void actionPerformed(ActionEvent e) {      
      if (!paths.isEmpty())
        paintTree.setOfHighlightesNodes.add(paths.remove(0));
      else {
        timer.stop();
        if (mode == 1) {
          tree.insert(key);
          paintTree.setOfHighlightesNodes.addAll(tree.path(key)); 
        }
        else if (mode == 2) {
          tree.delete(key);
        }
      }
      
      paintTree.repaint();
    }
  }
  
  class PaintTree extends JPanel {  
    protected Set<BinaryTree.TreeNode<Integer>> setOfHighlightesNodes = 
      new HashSet<BinaryTree.TreeNode<Integer>>();
    protected int radius = 20;
    protected int virticalGap = 50;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Display root     
      displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
    }
    
    /** (x, y) is the center of the root */
    private void displayTree(Graphics g, BinaryTree.TreeNode root, 
        int x, int y, int gap) {
      if (root != null) {
        // Display root
        if (setOfHighlightesNodes.contains(root)) {
          g.setColor(Color.GREEN);
          g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
          g.setColor(Color.BLACK);
        }
        else {
          g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        }

        g.drawString(root.element + "", x - 6, y + 4);
        
        // Draw a line to the left node
        if (root.left != null)
          connectLeftChild(g, x - gap, y + virticalGap, x, y);
        
        // Draw left subtree
        displayTree(g, root.left, x - gap, y + virticalGap, gap / 2);
      
        // Draw a line to the right node
        if (root.right != null)
          connectRightChild(g, x + gap, y + virticalGap, x, y);
        
        // Draw right subtree
        displayTree(g, root.right, x + gap, y + virticalGap, gap / 2);      
      }
    }
    
    private void connectLeftChild(Graphics g, 
        int x1, int y1, int x2, int y2) { 
      // (x1, y1) is for left child and (x2, y2) is for the parent
      double r = Math.sqrt(virticalGap * virticalGap + 
          (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 + radius * (x2 - x1) / r);
      int y11 = (int)(y1 - radius * virticalGap / r);
      int x21 = (int)(x2 - radius * (x2 - x1) / r);
      int y21 = (int)(y2 + radius * virticalGap / r);
      g.drawLine(x11, y11, x21, y21);
    }
    
    private void connectRightChild(Graphics g, 
        int x1, int y1, int x2, int y2) {
      // (x1, y1) is for left child and (x2, y2) is for the parent
      double r = Math.sqrt(virticalGap * virticalGap + 
          (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 - radius * (x1 - x2) / r);
      int y11 = (int)(y1 - radius * virticalGap / r);
      int x21 = (int)(x2 + radius * (x1 - x2) / r);
      int y21 = (int)(y2 + radius * virticalGap / r);
      g.drawLine(x11, y11, x21, y21);
    }
  }
}
