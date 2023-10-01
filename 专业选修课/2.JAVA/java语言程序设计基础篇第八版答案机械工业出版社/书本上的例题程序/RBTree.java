import java.util.ArrayList;

public class RBTree<E extends Comparable<E>> extends BinaryTree<E> {
  /** Create a default RB tree */
  public RBTree() {
  }

  /** Create an RB tree from an array of elements */
  public RBTree(E[] elements) {
    super(elements);
  }

  /** Override createNewNode to create an RBTreeNode */
  protected RBTreeNode<E> createNewNode(E e) {
    return new RBTreeNode<E>(e);
  }

  /** Override the insert method to balance the tree if necessary */
  public boolean insert(E e) {
    boolean successful = super.insert(e);
    if (!successful)
      return false; // e is already in the tree
    else {
      ensureRBTree(e); 
    }

    return true; // e is inserted
  }

  /** Ensure that the tree is a red-black tree */
  private void ensureRBTree(E e) {
    // Get the path that leads to element e from the root 
    ArrayList<TreeNode<E>> path = path(e);

    int i = path.size() - 1; // Index to the current node in the path
    
    // u is the last node in the path. u contains element e
    RBTreeNode<E> u = (RBTreeNode<E>)(path.get(i));
        
    // v is the parent of of u, if exists
    RBTreeNode<E> v = (u == root) ? null : 
      (RBTreeNode<E>)(path.get(i - 1));

    u.setRed(); // It is OK to set u red    
          
    if (u == root) // If e is inserted as the root, set root black
      u.setBlack();
    else if (v.isRed()) 
      fixDoubleRed(u, v, path, i); // Fix double red violation at u
  }
  
  /** Fix double red violation at node u */
  private void fixDoubleRed(RBTreeNode<E> u, RBTreeNode<E> v, 
      ArrayList<TreeNode<E>> path, int i) {          
    // w is the grandparent of u
    RBTreeNode<E> w = (RBTreeNode<E>)(path.get(i - 2));
    RBTreeNode<E> parentOfw = (w == root) ? null : 
      (RBTreeNode<E>)path.get(i - 3);

    // Get v's sibling named x
    RBTreeNode<E> x = (w.left == v) ? 
      (RBTreeNode<E>)(w.right) : (RBTreeNode<E>)(w.left);
    
    if (x == null || x.isBlack()) {
      // Case 1: v's sibling x is black
      if (w.left == v && v.left == u) {
        // Case 1.1: u < v < w, Restructure and recolor nodes
        restructureRecolor(u, v, w, w, parentOfw);
    
        w.left = v.right; // v.right is y3 in Figure 11.7
        v.right = w;
      }
      else if (w.left == v && v.right == u) {
        // Case 1.2: v < u < w, Restructure and recolor nodes
        restructureRecolor(v, u, w, w, parentOfw);
        v.right = u.left;
        w.left = u.right;
        u.left = v;
        u.right = w;
      }
      else if (w.right == v && v.right == u) {
        // Case 1.3: w < v < u, Restructure and recolor nodes
        restructureRecolor(w, v, u, w, parentOfw);
        w.right = v.left;
        v.left = w;
      }
      else {
        // Case 1.4: w < u < v, Restructure and recolor nodes
        restructureRecolor(w, u, v, w, parentOfw);
        w.right = u.left;
        v.left = u.right;
        u.left = w;
        u.right = v;
      }
    }
    else { // Case 2: v's sibling x is red 
      // Recolor nodes
      w.setRed();
      u.setRed();
      ((RBTreeNode<E>)(w.left)).setBlack(); 
      ((RBTreeNode<E>)(w.right)).setBlack();
      
      if (w == root) {
        w.setBlack();     
      }
      else if (((RBTreeNode<E>)parentOfw).isRed()) {  
        // Propagate along the path to fix new double red violation
        u = w;
        v = (RBTreeNode<E>)parentOfw;
        fixDoubleRed(u, v, path, i - 2); // i – 2 propagates upward
      }
    }
  }

  /** Connect b with parentOfw and recolor a, b, c for a < b < c */
  private void restructureRecolor(RBTreeNode<E> a, RBTreeNode<E> b, 
      RBTreeNode<E> c, RBTreeNode<E> w, RBTreeNode<E> parentOfw) {
    if (parentOfw == null)
      root = b;
    else if (parentOfw.left == w)
      parentOfw.left = b;
    else
      parentOfw.right = b;

    b.setBlack(); // b becomes the root in the subtree
    a.setRed(); // a becomes the left child of b
    c.setRed(); // c becomes the right child of b
  }      
  
  /** Delete an element from the RBTree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else
        break; // Element is in the tree pointed by current
    }

    if (current == null)
      return false; // Element is not in the tree

    java.util.ArrayList<TreeNode<E>> path;

    // current node is an internal node 
    if (current.left != null && current.right != null) {
      // Locate the rightmost node in the left subtree of current
      TreeNode<E> rightMost = current.left;
      while (rightMost.right != null) {
        rightMost = rightMost.right; // Keep going to the right
      }

      path = path(rightMost.element); // Get path before replacement

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;
    }
    else
      path = path(e); // Get path to current node

    // Delete the last node in the path and propagate if needed
    deleteLastNodeInPath(path);
    
    size--; // After one element deleted
    return true; // Element deleted
  }

  /** Delete the last node from the path. */
  public void deleteLastNodeInPath(ArrayList<TreeNode<E>> path) {
    int i = path.size() - 1; // Index to the node in the path
    // u is the last node in the path
    RBTreeNode<E> u = (RBTreeNode<E>)(path.get(i));
    RBTreeNode<E> parentOfu = (u == root) ? null :
      (RBTreeNode<E>)(path.get(i - 1));
    RBTreeNode<E> grandparentOfu = (parentOfu == null ||
      parentOfu == root) ? null :
      (RBTreeNode<E>)(path.get(i - 2));
    RBTreeNode<E> childOfu = (u.left == null) ?
      (RBTreeNode<E>)(u.right) : (RBTreeNode<E>)(u.left);

    // Delete node u. Connect childOfu with parentOfu
    connectNewParent(parentOfu, u, childOfu);
    
    // Recolor the nodes and fix double black if needed
    if (childOfu == root || u.isRed())
      return; // Done if childOfu is root or if u is red 
    else if (childOfu != null && childOfu.isRed()) 
      childOfu.setBlack(); // Set it black, done
    else // u is black, childOfu is null or black
      // Fix double black on parentOfu
      fixDoubleBlack(grandparentOfu, parentOfu, childOfu, path, i);
  }

  /** Fix the double black problem at node parent */
  private void fixDoubleBlack(
      RBTreeNode<E> grandparent, RBTreeNode<E> parent, 
      RBTreeNode<E> db, ArrayList<TreeNode<E>> path, int i) {
    // Obtain y, y1, and y2
    RBTreeNode<E> y = (parent.right == db) ? 
      (RBTreeNode<E>)(parent.left) : (RBTreeNode<E>)(parent.right);
    RBTreeNode<E> y1 = (RBTreeNode<E>)(y.left);
    RBTreeNode<E> y2 = (RBTreeNode<E>)(y.right);

    if (y.isBlack() && y1 != null && y1.isRed()) {
      if (parent.right == db) {
        // Case 1.1: y is a left black sibling and y1 is red
        connectNewParent(grandparent, parent, y);
        recolor(parent, y, y1); // Adjust colors

        // Adjust child links
        parent.left = y.right;
        y.right = parent;
      }
      else {
        // Case 1.3: y is a right black sibling and y1 is red        
        connectNewParent(grandparent, parent, y1);
        recolor(parent, y1, y); // Adjust colors

        // Adjust child links
        parent.right = y1.left;
        y.left = y1.right;
        y1.left = parent;
        y1.right = y;
      }
    }
    else if (y.isBlack() && y2 != null && y2.isRed()) {
      if (parent.right == db) {
        // Case 1.2: y is a left black sibling and y2 is red
        connectNewParent(grandparent, parent, y2);
        recolor(parent, y2, y); // Adjust colors

        // Adjust child links
        y.right = y2.left;
        parent.left = y2.right;
        y2.left = y;
        y2.right = parent;
      }
      else {
        // Case 1.4: y is a right black sibling and y2 is red        
        connectNewParent(grandparent, parent, y);
        recolor(parent, y, y2); // Adjust colors

        // Adjust child links
        y.left = parent;
        parent.right = y1;
      }
    }
    else if (y.isBlack()) { 
      // Case 2: y is black and y's children are black or null
      y.setRed(); // Change y to red
      if (parent.isRed())
        parent.setBlack(); // Done
      else if (parent != root) {
        // Propagate double black to the parent node
        // Fix new appearance of double black recursively
        db = parent;
        parent = grandparent;
        grandparent = 
          (i >= 3) ? (RBTreeNode<E>)(path.get(i - 3)) : null;
        fixDoubleBlack(grandparent, parent, db, path, i - 1);
      }
    }
    else { // y.isRed()
      if (parent.right == db) {       
        // Case 3.1: y is a left red child of parent
        parent.left = y2;
        y.right = parent;
      }
      else {
        // Case 3.2: y is a right red child of parent
        parent.right = y.left;
        y.left = parent;
      } 
      
      parent.setRed(); // Color parent red
      y.setBlack(); // Color y black
      connectNewParent(grandparent, parent, y); // y is new parent
      fixDoubleBlack(y, parent, db, path, i - 1); 
    }
  }

  /** Recolor parent, newParent, and c. Case 1 removal */
  private void recolor(RBTreeNode<E> parent, 
      RBTreeNode<E> newParent, RBTreeNode<E> c) {
    // Retain the parent's color for newParent
    if (parent.isRed())
      newParent.setRed(); 
    else
      newParent.setBlack();
    
    // c and parent become the children of newParent, set them black
    parent.setBlack();  
    c.setBlack();
  }

  /** Connect newParent with grandParent */   
  private void connectNewParent(RBTreeNode<E> grandparent,
      RBTreeNode<E> parent, RBTreeNode<E> newParent) {
    if (parent == root) {
      root = newParent;
      if (root != null)
        newParent.setBlack();
    }
    else if (grandparent.left == parent)
      grandparent.left = newParent;
    else
      grandparent.right = newParent;
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element +
      (((RBTreeNode<E>)root).isRed() ? " (red) " : " (black) "));
    preorder(root.left);
    preorder(root.right);
  }

  /** RBTreeNode is TreeNode plus color indicator */
  protected static class RBTreeNode<E extends Comparable<E>> extends
      BinaryTree.TreeNode<E> {
    private boolean red = true; // Indicate node color

    public RBTreeNode(E e) {
      super(e);
    }

    public boolean isRed() {
      return red;
    }

    public boolean isBlack() {
      return!red;
    }

    public void setBlack() {
      red = false;
    }

    public void setRed() {
      red = true;
    }

    int blackHeight;
  }
}