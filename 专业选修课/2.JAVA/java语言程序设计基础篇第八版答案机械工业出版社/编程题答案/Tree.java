public interface Tree<E extends Comparable<E>> {
  /** Returns true if the element is in the tree */
  public boolean search(E o);

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E o);

  /** Delete the specified element from the tree */
  public boolean delete(E o);

  /** Inorder traversal from the root*/
  public void inorder();

  /** Postorder traversal from the root */
  public void postorder();

  /** Preorder traversal from the root */
  public void preorder();

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Return true if the tree is empty */
  public boolean isEmpty();
}

