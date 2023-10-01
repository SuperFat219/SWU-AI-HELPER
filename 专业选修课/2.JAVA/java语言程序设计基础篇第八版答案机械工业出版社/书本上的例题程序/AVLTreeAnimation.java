import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class AVLTreeAnimation extends JApplet {
  private AVLTree<Integer> tree = new AVLTree<Integer>();
  private AVLTree<Integer> treeCopy;
  
  public AVLTreeAnimation() {
 /*   tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(30);
    tree.insert(70);
    tree.insert(80);
    tree.insert(110);
    tree.insert(10); 
    */
    setUI();
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("AVLTreeAnimation");
    JApplet applet = new AVLTreeAnimation();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  private JButton jbtSearch = new JButton("Search");
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Delete");
  private JTextField jtfKey = new JTextField(5);
  private PaintTree paintTree = new PaintTree();
  
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
          paintTree.setOfHighlightedNodes.clear();
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
          treeCopy = (AVLTree<Integer>)(tree.clone());          
          paintTree.setOfHighlightedNodes.clear();
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
          treeCopy = (AVLTree<Integer>)(tree.clone());          
          paintTree.setOfHighlightedNodes.clear();
          paths = tree.path(key);       
          timer = new Timer(1000, new AnimationListener(key, 2));
          timer.start();
        }
      }
    });
  }
  
  Timer timer;
  
  AnimationRotationAfterDeletion deleteAnimation = new AnimationRotationAfterDeletion(null);
  Timer timerForDelete = new Timer(500, deleteAnimation);  
  
  protected ArrayList<AVLTree.TreeNode<Integer>> paths = 
    new ArrayList<AVLTree.TreeNode<Integer>>();
  
  class AnimationListener implements ActionListener {
    int mode = 0; // 0 for search, 1 for insert, and 2 for delete
    Integer key = null;
    
    public AnimationListener(Integer key, int mode) {
      this.key = key;
      this.mode = mode;
    }
    
    public void actionPerformed(ActionEvent e) {      
      if (!paths.isEmpty())
        paintTree.setOfHighlightedNodes.add(paths.remove(0));
      else {
        timer.stop();
        if (mode == 1) {
          tree.justInsert(key);
          timer = new Timer(500, new AnimationRotationAfterInsertion(key));
          timer.start();
        }
        else if (mode == 2) {
          deleteAnimation.setKey(key);
          timerForDelete.start();          
        }
      }
      
      paintTree.repaint();
    }
  }
  
  class AnimationRotationAfterInsertion implements ActionListener {
    int mode = 0; // 0 for search, 1 for insert, and 2 for delete
    Integer key = null;
    int count = 0;
    HashSet<AVLTree.TreeNode<Integer>> set1 = new HashSet<AVLTree.TreeNode<Integer>>();
    HashSet<AVLTree.TreeNode<Integer>> set2 = new HashSet<AVLTree.TreeNode<Integer>>();
    
    public void setKey(Integer key) {
      this.key = key;
    }
    
    public AnimationRotationAfterInsertion(Integer key) {
      this.key = key;     
      set2 = tree.findImbalanceSubtree(key);
      
      if (set2.size() == 0)
        count = 16;
    }
    
    public void actionPerformed(ActionEvent e) {      
      if (count++ <= 15) {
        if (count % 2 == 0) 
          paintTree.setOfHighlightedNodes = set2;
        else
          paintTree.setOfHighlightedNodes = set1;
      }
      else {
        timer.stop();
        tree = treeCopy;
        tree.insert(key);        
      }
      
      paintTree.repaint();
    }
  }

  class AnimationRotationAfterDeletion implements ActionListener {
    int mode = 0; // 0 for search, 1 for insert, and 2 for delete
    Integer key = null;
    int count = 0;
    HashSet<AVLTree.TreeNode<Integer>> set1 = new HashSet<AVLTree.TreeNode<Integer>>();
    HashSet<AVLTree.TreeNode<Integer>> set2 = new HashSet<AVLTree.TreeNode<Integer>>();

    public void setKey(Integer key) {
      this.key = key;

      Integer startingElement = tree.findStartingNodeForDeletion(key);
      tree.justDelete(key);
      set2 = tree.findImbalanceSubtreeForDeletion(startingElement);
      
      if (set2.size() == 0) 
        count = 16;
      else
        count = 0;
    }
    
    public AnimationRotationAfterDeletion(Integer key) {
      this.key = key;     
    }
    
    public void actionPerformed(ActionEvent e) {      
      if (count++ <= 15) {
        if (count % 2 == 0) 
          paintTree.setOfHighlightedNodes = set2;
        else
          paintTree.setOfHighlightedNodes = set1;
      }
      else {
        timerForDelete.stop();
        tree = treeCopy;
        tree.delete(key);        
      }
      
      paintTree.repaint();
    }
  }
  
  class PaintTree extends JPanel {      
    protected HashSet<AVLTree.TreeNode<Integer>> setOfHighlightedNodes = 
      new HashSet<AVLTree.TreeNode<Integer>>();
    protected int radius = 20;
    protected int virticalGap = 50;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Display root     
      displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
    }
    
    /** (x, y) is the center of the root */
    private void displayTree(Graphics g, AVLTree.TreeNode root, 
        int x, int y, int gap) {
      if (root != null) {
        // Display root
        if (setOfHighlightedNodes.contains(root)) {
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
  
  static class AVLTree<E extends Comparable<E>> extends BinaryTree<E>
      implements Cloneable {
    /** Create a default AVL tree */
    public AVLTree() {
    }

    /** Create an AVL tree from an array of objects */
    public AVLTree(E[] objects) {
      super(objects);
    }

    /** Override createNewNode to create an AVLTreeNode */
    protected AVLTreeNode<E> createNewNode(E o) {
      return new AVLTreeNode<E>(o);
    }

    /** insert for animation only */
    public boolean justInsert(E o) {
      return super.insert(o);
    }

    public boolean justDelete(E element) {
      return super.delete(element); 
    }

    /** Override the insert method to balance the tree if necessary */
    public boolean insert(E o) {
      boolean successful = super.insert(o);

      if (!successful)
        return false; // o is already in the tree
      else {
        balancePath(o); // Balance from o to the root if necessary
      }

      return true; // o is inserted
    }

    /* For animation */
    public java.util.HashSet<TreeNode<E>> findImbalanceSubtree(E o) {
      java.util.HashSet<TreeNode<E>> set = new java.util.HashSet<TreeNode<E>>();
      if (findImbalanceNode(o) == null) 
        return set;
      else {
        AVLTreeNode<E> root = findImbalanceNode(o);
        addDecendantsToSet(root, set);
        return set;
      }
    }
    
    /* For animation */
    public java.util.HashSet<TreeNode<E>> findImbalanceSubtreeForDeletion(E o) {
      java.util.HashSet<TreeNode<E>> set = new java.util.HashSet<TreeNode<E>>();
      if (findImbalanceNode(o) == null) 
        return set;
      else {
        AVLTreeNode<E> root = findImbalanceNode(o);
        addDecendantsToSet(root, set);
        return set;
      }
    }
    
    /* For animation */
    private void addDecendantsToSet(TreeNode<E> root, java.util.HashSet<TreeNode<E>> set) {
      if (root != null) {
        set.add(root);
        addDecendantsToSet((AVLTreeNode<E>)root.left, set);
        addDecendantsToSet((AVLTreeNode<E>)root.right, set);
      }
    }
    
    /* For animation */
    private AVLTreeNode<E> findImbalanceNode(E o) {
      if (o == null) return null;
      
      java.util.ArrayList<TreeNode<E>> path = path(o);
      for (int i = path.size() - 1; i >= 0; i--) {
        AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
        updateHeight(A);
        AVLTreeNode<E> parentOfA = (A == root) ? null :
          (AVLTreeNode<E>)(path.get(i - 1));

        switch (balanceFactor(A)) {
          case -2:
            return A;
          case +2:
            return A;
        }
      }
      
      return null;
    }
    
    /* For animation */
    private E findStartingNodeForDeletion(E element) {
      if (root == null)
        return null; // Element is not in the tree

      // Locate the node to be deleted and also locate its parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (element.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (element.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          break; // Element is in the tree pointed by current
      }

      if (current == null)
        return null; // Element is not in the tree

      // Case 1: current has no left children (See Figure 23.6)
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          return null;
        }
        else {
          return parent.element;
        }
      }
      else {
        // Case 2: The current node has a left child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
        }
        
        // Balance the tree if necessary
        return parentOfRightMost.element;
      }
    }
    
    /** Update the height of a specified node */
    private void updateHeight(AVLTreeNode<E> node) {
      if (node.left == null && node.right == null) // node is a leaf
        node.height = 0;
      else if (node.left == null) // node has no left subtree
        node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
      else if (node.right == null) // node has no right subtree
        node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
      else
        node.height = 1 +
          Math.max(((AVLTreeNode<E>)(node.right)).height,
          ((AVLTreeNode<E>)(node.left)).height);
    }

    /** Balance the nodes in the path from the specified
     * node to the root if necessary
     */
    private void balancePath(E o) {
      java.util.ArrayList<TreeNode<E>> path = path(o);
      for (int i = path.size() - 1; i >= 0; i--) {
        AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
        updateHeight(A); 
        AVLTreeNode<E> parentOfA = (A == root) ? null :
          (AVLTreeNode<E>)(path.get(i - 1));

        switch (balanceFactor(A)) {
          case -2:
            if (balanceFactor((AVLTreeNode<E>)A.left) <= 0) {
              balanceLL(A, parentOfA); // Perform LL rotation
            }
            else {
              balanceLR(A, parentOfA); // Perform LR rotation
            }
            break;
          case +2:
            if (balanceFactor((AVLTreeNode<E>)A.right) >= 0) {
              balanceRR(A, parentOfA); // Perform RR rotation
            }
            else {
              balanceRL(A, parentOfA); // Perform RL rotation
            }
        }
      }
    }

    /** Return the balance factor of the node */
    private int balanceFactor(AVLTreeNode<E> node) {
      if (node.right == null) // node has no right subtree
        return -node.height;
      else if (node.left == null) // node has no left subtree
        return +node.height;
      else
        return ((AVLTreeNode<E>)node.right).height -
          ((AVLTreeNode<E>)node.left).height;
    }

    /** Balance LL (see Figure 9.1) */
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
      TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

      if (A == root) {
        root = B;
      }
      else {
        if (parentOfA.left == A) {
          parentOfA.left = B;
        }
        else {
          parentOfA.right = B;
        }
      }

      A.left = B.right; // Make T2 the left subtree of A
      B.right = A; // Make A the left child of B
      updateHeight((AVLTreeNode<E>)A);
      updateHeight((AVLTreeNode<E>)B);
    }

    /** Balance LR (see Figure 9.1(c)) */
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
      TreeNode<E> B = A.left; // A is left-heavy
      TreeNode<E> C = B.right; // B is right-heavy

      if (A == root) {
        root = C;
      }
      else {
        if (parentOfA.left == A) {
          parentOfA.left = C;
        }
        else {
          parentOfA.right = C;
        }
      }

      A.left = C.right; // Make T3 the left subtree of A
      B.right = C.left; // Make T2 the right subtree of B
      C.left = B;
      C.right = A;

      // Adjust heights
      updateHeight((AVLTreeNode<E>)A);
      updateHeight((AVLTreeNode<E>)B);
      updateHeight((AVLTreeNode<E>)C);
    }

    /** Balance RR (see Figure 9.1(b)) */
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
      TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

      if (A == root) {
        root = B;
      }
      else {
        if (parentOfA.left == A) {
          parentOfA.left = B;
        }
        else {
          parentOfA.right = B;
        }
      }

      A.right = B.left; // Make T2 the right subtree of A
      B.left = A;
      updateHeight((AVLTreeNode<E>)A);
      updateHeight((AVLTreeNode<E>)B);
    }

    /** Balance RL (see Figure 9.1(d)) */
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
      TreeNode<E> B = A.right; // A is right-heavy
      TreeNode<E> C = B.left; // B is left-heavy

      if (A == root) {
        root = C;
      }
      else {
        if (parentOfA.left == A) {
          parentOfA.left = C;
        }
        else {
          parentOfA.right = C;
        }
      }

      A.right = C.left; // Make T2 the right subtree of A
      B.left = C.right; // Make T3 the left subtree of B
      C.left = A;
      C.right = B;

      // Adjust heights
      updateHeight((AVLTreeNode<E>)A);
      updateHeight((AVLTreeNode<E>)B);
      updateHeight((AVLTreeNode<E>)C);
    }

    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E element) {
      if (root == null)
        return false; // Element is not in the tree

      // Locate the node to be deleted and also locate its parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (element.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (element.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          break; // Element is in the tree pointed by current
      }

      if (current == null)
        return false; // Element is not in the tree

      // Case 1: current has no left children (See Figure 23.6)
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          root = current.right;
        }
        else {
          if (element.compareTo(parent.element) < 0)
            parent.left = current.right;
          else
            parent.right = current.right;

          // Balance the tree if necessary
          balancePath(parent.element);          
        }
      }
      else {
        // Case 2: The current node has a left child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
        }

        // Replace the element in current by the element in rightMost
        current.element = rightMost.element;

        // Eliminate rightmost node
        if (parentOfRightMost.right == rightMost)
          parentOfRightMost.right = rightMost.left;
        else
          // Special case: parentOfRightMost is current
          parentOfRightMost.left = rightMost.left; 
        
        // Balance the tree if necessary
        balancePath(parentOfRightMost.element);
      }

      size--;
      return true; // Element inserted
    }

    /** AVLTreeNode is TreeNode plus height */
    protected static class AVLTreeNode<E extends Comparable<E>>
        extends BinaryTree.TreeNode<E> {
      int height = 0; // New data field

      public AVLTreeNode(E o) {
        super(o);
      }
    }
    
    public Object clone() {
      AVLTree<E> tree = new AVLTree<E>();

      LinkedList<AVLTreeNode<E>> queue = new LinkedList<AVLTreeNode<E>>();
      
      if (root == null) return tree;
        
      queue.add((AVLTreeNode<E>)root);
      
      while (queue.size() > 0) {
        AVLTreeNode<E> node = queue.remove(0);
        tree.insert(node.element);
    
        if (node.left != null) 
          queue.add((AVLTreeNode<E>)(node.left));
        
        if (node.right != null) 
          queue.add((AVLTreeNode<E>)(node.right));
        
      }
      
      return tree;
    }
  }
}