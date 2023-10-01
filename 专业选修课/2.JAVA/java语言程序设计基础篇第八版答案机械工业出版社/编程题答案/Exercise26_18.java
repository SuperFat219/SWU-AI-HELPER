import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise26_18 extends JApplet {
  public Exercise26_18() {
    add(new BinaryTreeView(new BinaryTree<Integer>()));
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise26_18");
    JApplet applet = new Exercise26_18();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  class BinaryTreeView extends JPanel {
    private BinaryTree<Integer> tree; // A binary tree to be displayed
    private JTextField jtfKey = new JTextField(5);
    private PaintTree paintTree = new PaintTree();
    private JButton jbtInsert = new JButton("Insert");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtShowInorder = new JButton("Show Inorder");
    private JButton jbtShowPreorder = new JButton("Show Preorder");
    private JButton jbtShowPostorder = new JButton("Show Postorder");

    /** Construct a view for a binary tree */
    public BinaryTreeView(BinaryTree<Integer> tree) {
      this.tree = tree; // Set a binary tree to be displayed
      setUI();
    }

    /** Initialize UI for binary tree */
    private void setUI() {
      this.setLayout(new BorderLayout());
      add(paintTree, BorderLayout.CENTER);
      JPanel panel = new JPanel();
      panel.add(new JLabel("Enter a key: "));
      panel.add(jtfKey);
      panel.add(jbtInsert);
      panel.add(jbtDelete);
      panel.add(jbtShowInorder);
      panel.add(jbtShowPreorder);
      panel.add(jbtShowPostorder);
      
      add(panel, BorderLayout.SOUTH);

      // Listener for the Insert button
      jbtInsert.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int key = Integer.parseInt(jtfKey.getText());
          if (tree.search(key)) { // key is in the tree already
            JOptionPane
                .showMessageDialog(null, key + " is already in the tree");
          } else {
            tree.insert(key); // Insert a new key
            paintTree.repaint(); // Redisplay the tree
          }
        }
      });

      // Listener for the Delete button
      jbtDelete.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int key = Integer.parseInt(jtfKey.getText());
          if (!tree.search(key)) { // key is not in the tree
            JOptionPane.showMessageDialog(null, key + " is not in the tree");
          } else {
            tree.delete(key); // Delete a key
            paintTree.repaint(); // Redisplay the tree
          }
        }
      });
      
      // Listener for the the Show Inorder button
      jbtShowInorder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          java.util.List<Integer> list = tree.inorderList();
          String elements = "";
          for (Integer element: list) {
            elements += element + " ";
          }
          JOptionPane.showMessageDialog(null, "Inorder is " + elements);
        }
      });

      // Listener for the Show Preorder button
      jbtShowPreorder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          java.util.List<Integer> list = tree.preorderList();
          String elements = "";
          for (Integer element: list) {
            elements += element + " ";
          }
          JOptionPane.showMessageDialog(null, "Preorder is " + elements);
        }
      });
      
      // Listener for the Show Postorder button
      jbtShowPostorder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          java.util.List<Integer> list = tree.postorderList();
          String elements = "";
          for (Integer element: list) {
            elements += element + " ";
          }
          JOptionPane.showMessageDialog(null, "Postorder is " + elements);
        }
      });
    }

    // Inner class PaintTree for displaying a tree on a panel
    class PaintTree extends JPanel {
      private int radius = 20; // Tree node radius

      private int vGap = 50; // Gap between two levels in a tree

      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tree.getRoot() != null) {
          // Display tree recursively
          displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
      }

      /** Display a subtree rooted at position (x, y) */
      private void displayTree(Graphics g, BinaryTree.TreeNode root, int x,
          int y, int hGap) {
        // Display the root
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.drawString(root.element + "", x - 6, y + 4);

        if (root.left != null) {
          // Draw a line to the left node
          connectLeftChild(g, x - hGap, y + vGap, x, y);
          // Draw the left subtree recursively
          displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
          // Draw a line to the right node
          connectRightChild(g, x + hGap, y + vGap, x, y);
          // Draw the right subtree recursively
          displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);
        }
      }

      /**
       * Connect a parent at (x2, y2) with its left child at (x1, y1)
       */
      private void connectLeftChild(Graphics g, int x1, int y1, int x2, int y2) {
        double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
        int x11 = (int) (x1 + radius * (x2 - x1) / d);
        int y11 = (int) (y1 - radius * vGap / d);
        int x21 = (int) (x2 - radius * (x2 - x1) / d);
        int y21 = (int) (y2 + radius * vGap / d);
        g.drawLine(x11, y11, x21, y21);
      }

      /**
       * Connect a parent at (x2, y2) with its right child at (x1, y1)
       */
      private void connectRightChild(Graphics g, int x1, int y1, int x2, int y2) {
        double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
        int x11 = (int) (x1 - radius * (x1 - x2) / d);
        int y11 = (int) (y1 - radius * vGap / d);
        int x21 = (int) (x2 + radius * (x1 - x2) / d);
        int y21 = (int) (y2 + radius * vGap / d);
        g.drawLine(x11, y11, x21, y21);
      }
    }
  }

  class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> {
    protected TreeNode<E> root;

    protected int size = 0;

    /** Create a default binary tree */
    public BinaryTree() {
    }

    /** Create a binary tree from an array of objects */
    public BinaryTree(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        insert(objects[i]);
    }

    /** Returns true if the element is in the tree */
    public boolean search(E e) {
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else
          // element matches current.element
          return true; // Element is found
      }

      return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully
     */
    public boolean insert(E e) {
      if (root == null)
        root = createNewNode(e); // Create a new root
      else {
        // Locate the parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null)
          if (e.compareTo(current.element) < 0) {
            parent = current;
            current = current.left;
          } else if (e.compareTo(current.element) > 0) {
            parent = current;
            current = current.right;
          } else
            return false; // Duplicate node not inserted

        // Create the new node and attach it to the parent node
        if (e.compareTo(parent.element) < 0)
          parent.left = createNewNode(e);
        else
          parent.right = createNewNode(e);
      }

      size++;
      return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
      return new TreeNode<E>(e);
    }

    /** Inorder traversal from the root */
    public void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
      if (root == null)
        return;
      inorder(root.left);
      System.out.print(root.element + " ");
      inorder(root.right);
    }

    /** Inorder traversal from the root */
    public java.util.List<E> inorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      inorderList(root, list);
      return list;
    }

    /** Inorder traversal from a subtree */
    protected void inorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      inorderList(root.left, list);
      list.add(root.element);
      inorderList(root.right, list);
    }
    
    /** Postorder traversal from the root */
    public void postorder() {
      postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
      if (root == null)
        return;
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.element + " ");
    }

    /** Postorder traversal from the root */
    public java.util.List<E> postorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      postorderList(root, list);
      return list;
    }

    /** Postorder traversal from a subtree */
    protected void postorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      postorderList(root.left, list);
      postorderList(root.right, list);
      list.add(root.element);
    }
    
    /** Preorder traversal from the root */
    public void preorder() {
      preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
      if (root == null)
        return;
      System.out.print(root.element + " ");
      preorder(root.left);
      preorder(root.right);
    }

    /** Preorder traversal from the root */
    public java.util.List<E> preorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      preorderList(root, list);
      return list;     
    }

    /** Preorder traversal from a subtree */
    protected void preorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      list.add(root.element);    
      preorderList(root.left, list);
      preorderList(root.right, list);
  
    }
    
    /** Inner class tree node */
    public class TreeNode<E extends Comparable<E>> {
      E element;
      TreeNode<E> left;
      TreeNode<E> right;

      public TreeNode(E e) {
        element = e;
      }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return size;
    }

    /** Returns the root of the tree */
    public TreeNode getRoot() {
      return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
      java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        list.add(current); // Add the node to the list
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else
          break;
      }

      return list; // Return an array of nodes
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
      // Locate the node to be deleted and also locate its parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else
          break; // Element is in the tree pointed by current
      }

      if (current == null)
        return false; // Element is not in the tree

      // Case 1: current has no left children
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          root = current.right;
        } else {
          if (e.compareTo(parent.element) < 0)
            parent.left = current.right;
          else
            parent.right = current.right;
        }
      } else {
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
          // Special case: parentOfRightMost == current
          parentOfRightMost.left = rightMost.left;
      }

      size--;
      return true; // Element inserted
    }

    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator iterator() {
      return inorderIterator();
    }

    /** Obtain an inorder iterator */
    public java.util.Iterator inorderIterator() {
      return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
      // Store the elements in a list
      private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

      private int current = 0; // Point to the current element in list

      public InorderIterator() {
        inorder(); // Traverse binary tree and store elements in list
      }

      /** Inorder traversal from the root */
      private void inorder() {
        inorder(root);
      }

      /** Inorder traversal from a subtree */
      private void inorder(TreeNode<E> root) {
        if (root == null)
          return;
        inorder(root.left);
        list.add(root.element);
        inorder(root.right);
      }

      /** Next element for traversing? */
      public boolean hasNext() {
        if (current < list.size())
          return true;

        return false;
      }

      /** Get the current element and move cursor to the next */
      public Object next() {
        return list.get(current++);
      }

      /** Remove the current element and refresh the list */
      public void remove() {
        delete(list.get(current)); // Delete the current element
        list.clear(); // Clear the list
        inorder(); // Rebuild the list
      }
    }

    /** Remove all elements from the tree */
    public void clear() {
      root = null;
      size = 0;
    }
  }

  interface Tree<E extends Comparable<E>> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully
     */
    public boolean insert(E e);

    /**
     * Delete the specified element from the tree Return true if the element is
     * deleted successfully
     */
    public boolean delete(E e);

    /** Inorder traversal from the root */
    public void inorder();

    /** Postorder traversal from the root */
    public void postorder();

    /** Preorder traversal from the root */
    public void preorder();

    /** Get the number of nodes in the tree */
    public int getSize();

    /** Return true if the tree is empty */
    public boolean isEmpty();

    /** Return an iterator to traverse elements in the tree */
    public java.util.Iterator iterator();
  }

  abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {
    /** Inorder traversal from the root */
    public void inorder() {
    }

    /** Postorder traversal from the root */
    public void postorder() {
    }

    /** Preorder traversal from the root */
    public void preorder() {
    }

    /** Return true if the tree is empty */
    public boolean isEmpty() {
      return getSize() == 0;
    }

    /** Return an iterator to traverse elements in the tree */
    public java.util.Iterator iterator() {
      return null;
    }
  }
}