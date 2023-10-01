import java.util.Comparator;

public class Exercise26_16 {
  public static void main(String[] args) {
    BinaryTree<GeometricObject> tree =
      new BinaryTree<GeometricObject>(new GeometricObjectComparator());

    tree.insert(new Circle(3));
    tree.insert(new Circle(5));
    tree.insert(new Circle(1));

    tree.inorder();
  }

  static class BinaryTree<E> {
    private TreeNode<E> root;
    private int size = 0;

    private Comparator<? super E> comparator;

    /** Create a default heap */
    public BinaryTree(Comparator<? super E> comparator) {
      this.comparator = comparator;
	  }

    /** Create a binary tree from an array of objects */
    public BinaryTree(E[] objects) {
      for (int i = 0; i < objects.length; i++) {
        insert(objects[i]);
      }
    }

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E o) {
      if (root == null) {
        root = new TreeNode<E>(o); // Create a new root
      }
      else {
        // Locate the parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
          if (comparator.compare(o, current.element) < 0) {
            parent = current;
            current = current.left;
          }
          else if (comparator.compare(o, current.element) > 0) {
            parent = current;
            current = current.right;
          }
          else {
            return false; // Duplicate node not inserted
          }
        }

        // Create the new node and attach it to the parent node
        if (comparator.compare(o, parent.element) < 0) {
          parent.left = new TreeNode<E>(o);
        }
        else {
          parent.right = new TreeNode<E>(o);
        }
      }

      size++;
      return true; // Element inserted
    }

    /** Inorder traversal from the root*/
    public void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode root) {
      if (root == null) {
        return;
      }
      inorder(root.left);
      System.out.print(root.element + " ");
      inorder(root.right);
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
    private static class TreeNode<E> {
      E element;
      TreeNode<E> left;
      TreeNode<E> right;

      public TreeNode(E o) {
        element = o;
      }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return size;
    }
  }

}
