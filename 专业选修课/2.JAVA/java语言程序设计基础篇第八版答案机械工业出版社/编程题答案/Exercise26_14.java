import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class Exercise26_14 extends JApplet {
  private BinaryTree<Integer> tree = new BinaryTree<Integer>();

  public Exercise26_14() {
    tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(30);
    tree.insert(70);
    tree.insert(80);
    tree.insert(110);
    tree.insert(10);
    
    add(new BinaryTreeInsertAnimation(tree));
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise26_14");
    JApplet applet = new Exercise26_14();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class BinaryTreeInsertAnimation extends JPanel {
  // BinaryTree is the same as in the text
  private BinaryTree<Integer> tree = new BinaryTree<Integer>();
  private JButton jbtSearch = new JButton("Search");
  private JButton jbtInsert = new JButton("Insert");
  private JTextField jtfKey = new JTextField(5);
  private PaintTree paintTree = new PaintTree();
  
  public BinaryTreeInsertAnimation(BinaryTree<Integer> tree) {
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
    add(panel, BorderLayout.SOUTH);

    jbtSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (!tree.search(key)) {
          JOptionPane.showMessageDialog(null, key + " is not in the tree");
        }
        else {
          paintTree.paths.clear();
          timer = new Timer(1000, new AnimationListener(null));
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
          paintTree.paths.clear();
          paths = tree.path(key);       
          timer = new Timer(1000, new AnimationListener(key));
          timer.start();
        }
      }
    });
  }
  
  Timer timer;
  
  protected ArrayList<BinaryTree.TreeNode<Integer>> paths = 
    new ArrayList<BinaryTree.TreeNode<Integer>>();
  
  class AnimationListener implements ActionListener {
    Integer key = null;
    
    public AnimationListener(Integer key) {
      this.key = key;
    }
    
    public void actionPerformed(ActionEvent e) {      
      if (!paths.isEmpty())
        paintTree.paths.add(paths.remove(0));
      else {
        timer.stop();
        if (key != null) {
          tree.insert(key);
          paintTree.paths.addAll(tree.path(key)); 
        }
      }
      
      paintTree.repaint();
    }
  }
  
  class PaintTree extends JPanel {  
    protected ArrayList<BinaryTree.TreeNode<Integer>> paths = 
      new ArrayList<BinaryTree.TreeNode<Integer>>();
    protected int radius = 20;
    protected int vGap = 50;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Display root     
      displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
    }
    
    /** (x, y) is the center of the root */
    private void displayTree(Graphics g, BinaryTree.TreeNode root, 
        int x, int y, int hGap) {
      if (root != null) {
        // Display root
        if (paths.contains(root)) {
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
          connectLeftChild(g, x - hGap, y + vGap, x, y);
        
        // Draw left subtree
        displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
      
        // Draw a line to the right node
        if (root.right != null)
          connectRightChild(g, x + hGap, y + vGap, x, y);
        
        // Draw right subtree
        displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);      
      }
    }
    
    /** Connect a parent at (x2, y2) with 
     * its left child at (x1, y1) */
    private void connectLeftChild(Graphics g, int x1, int y1, 
        int x2, int y2) { 
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 + radius * (x2 - x1) / d);
      int y11 = (int)(y1 - radius * vGap / d);
      int x21 = (int)(x2 - radius * (x2 - x1) / d);
      int y21 = (int)(y2 + radius * vGap / d);
      g.drawLine(x11, y11, x21, y21);
    }
        
    /** Connect a parent at (x2, y2) with 
     * its right child at (x1, y1) */
    private void connectRightChild(Graphics g, int x1, int y1, 
        int x2, int y2) {
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 - radius * (x1 - x2) / d);
      int y11 = (int)(y1 - radius * vGap / d);
      int x21 = (int)(x2 + radius * (x1 - x2) / d);
      int y21 = (int)(y2 + radius * vGap / d);
      g.drawLine(x11, y11, x21, y21);
    }
  }
}