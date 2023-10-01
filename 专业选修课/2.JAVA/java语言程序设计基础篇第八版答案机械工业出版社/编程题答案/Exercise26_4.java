public class Exercise26_4 {
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();
    tree.insert("George");
    tree.insert("Michael");
    tree.insert("Tom");
    tree.insert("Adam");
    tree.insert("Jones");
    tree.insert("Peter");
  //  tree.insert("John");
    tree.insert("Daniel");

    tree.nonRecursivePreorder();
  }

  static class BinaryTree {
    private TreeNode root;
    private int size = 0;

    /** Create a default binary tree */
    public BinaryTree() {
    }

    /** Create a binary tree from an array of objects */
    public BinaryTree(Object[] objects) {
      for (int i = 0; i < objects.length; i++) {
        insert(objects[i]);
      }
    }

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(Object o) {
      if (root == null) {
        root = new TreeNode(o); // Create a new root
      }
      else {
        // Locate the parent node
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
          if (((Comparable)o).compareTo(current.element) < 0) {
            parent = current;
            current = current.left;
          }
          else if (((Comparable)o).compareTo(current.element) > 0) {
            parent = current;
            current = current.right;
          }
          else {
            return false; // Duplicate node not inserted
          }
        }

        // Create the new node and attach it to the parent node
        if (((Comparable)o).compareTo(parent.element) < 0) {
          parent.left = new TreeNode(o);
        }
        else {
          parent.right = new TreeNode(o);
        }
      }

      size++;
      return true; // Element inserted
    }

    /** Inorder traversal from the root*/
    public void nonRecursivePreorder() {
      java.util.ArrayList list = new java.util.ArrayList();
      MyStack stack = new MyStack();

      if (root == null) return;

      stack.push(root);

      while (!stack.isEmpty()) {
        TreeNode node = (TreeNode)(stack.peek());
        stack.pop(); // Remove the node from the stack
        list.add(node);
        
        if (node.right != null && !list.contains(node.right)) {
          stack.push(node.right); // Add the right node to the stack
        }
        
        if (node.left != null && !list.contains(node.left)) {
          stack.push(node.left); // Add the left node to the stack
        }
      }

      for (int i = 0; i < list.size(); i++)
        System.out.print(((TreeNode)(list.get(i))).element + " ");
    }

    /** Postorder traversal from the root */
    public void postorder() {
      postorder(root);
    }

    /** Postorder traversal from a subtree */
    private void postorder(TreeNode root) {
      if (root == null) {
        return;
      }
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.element + " ");
    }

    /** Preorder traversal from the root */
    public void preorder() {
      preorder(root);
    }

    /** Preorder traversal from a subtree */
    private void preorder(TreeNode root) {
      if (root == null) {
        return;
      }
      System.out.print(root.element + " ");
      preorder(root.left);
      preorder(root.right);
    }

    /** Inner class tree node */
    private static class TreeNode {
      Object element;
      TreeNode left;
      TreeNode right;

      public TreeNode(Object o) {
        element = o;
      }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return size;
    }

    /* Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    boolean delete(Object element) {
      if (root == null) {
        return false; // Element is not in the tree
      }

      // Locate the node to be deleted and also locate its parent node
      TreeNode parent = null;
      TreeNode current = root;
      while (current != null) {
        if (((Comparable)element).compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (((Comparable)element).compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else {
          break; // Element is in the tree pointed by current
        }
      }

      if (current == null) {
        return false; // Element is not in the tree
      }

      // Case 1: current has no left children
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          root = current.right;
        }
        else {
          if (((Comparable)element).compareTo(parent.element) < 0) {
            parent.left = current.right;
          }
          else {
            parent.right = current.right;
          }
        }
      }
      else if (current.left.right == null) {
        // Case 2: current has a left child, but
        //   the left child does not have a right child

        // Connect parent with current.left
        if (parent == null) { // current is the root
          root = current.left;
        }
        else {
          if (((Comparable)element).compareTo(parent.element) < 0) {
            parent.left = current.left;
          }
          else {
            parent.right = current.left;
          }
        }

        // Let current.left.right point to the right child of current
        current.left.right = current.right;
      }
      else {
        // Case 3: The left child of the current node has a right child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
        TreeNode parentOfRightMost = current.left;
        TreeNode rightMost = current.left;

        while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
        }

        // Replace the element in current by the element in rightMost
        current.element = rightMost.element;

        // Make parentOfRightMost the parent of the
        // left child of rightMost;
        parentOfRightMost.right = rightMost.left;
      }

      size--;
      return true; // Element inserted
    }

    /** Obtain an inorder iterator */
    public java.util.Iterator inorderIterator() {
      return new InorderIterator();
    }

// Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
      // Store the elements in a list
      private java.util.ArrayList list = new java.util.ArrayList();
      private int current = 0; // Point to the current element in list

      public InorderIterator() {
        inorder(); // Traverse binary tree and store elements in list
      }

      /** Inorder traversal from the root*/
      private void inorder() {
        inorder(root);
      }

      /** Inorder traversal from a subtree */
      private void inorder(TreeNode root) {
        if (root == null) {
          return;
        }
        inorder(root.left);
        list.add(root.element);
        inorder(root.right);
      }

      /** Next element for traversing? */
      public boolean hasNext() {
        if (current < list.size()) {
          return true;
        }

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
  }
}
