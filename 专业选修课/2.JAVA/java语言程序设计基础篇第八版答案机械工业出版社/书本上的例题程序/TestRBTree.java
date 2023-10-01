public class TestRBTree {
  public static void main(String[] args) {
    // Create an RB tree
    RBTree<Integer> tree = 
      new RBTree<Integer>(new Integer[]{34, 3, 50});
    printTree(tree);
    
    tree.insert(20);
    printTree(tree);

    tree.insert(15);
    printTree(tree);

    tree.insert(16);
    printTree(tree);

    tree.insert(25);
    printTree(tree);

    tree.insert(27);
    printTree(tree);

    tree.delete(50);
    printTree(tree);

    tree.delete(20);
    printTree(tree);

    tree.delete(15);
    printTree(tree);

    tree.delete(3);
    printTree(tree);

    tree.delete(25);
    printTree(tree);

    tree.delete(16);
    printTree(tree);

    tree.delete(34);
    printTree(tree);

    tree.delete(27);
    printTree(tree);
  }

  public static void printTree(BinaryTree tree) {
    // Traverse tree
    System.out.print("\nInorder (sorted): ");
    tree.inorder();
    System.out.print("\nPostorder: ");
    tree.postorder();
    System.out.print("\nPreorder: ");
    tree.preorder();
    System.out.print("\nThe number of nodes is " + tree.getSize());
    System.out.println();
  }
}
