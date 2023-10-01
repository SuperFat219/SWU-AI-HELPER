import javax.swing.JApplet;
import javax.swing.JFrame;

public class Tree24Animation extends JApplet {
  public Tree24Animation() {
    Tree24<Integer> tree = new Tree24<Integer>();
    
    add(new Tree24View(tree));
    
/*    tree.insert(16); 
    tree.insert(17); 
    tree.insert(18); 
    tree.insert(19); 
    tree.insert(20); 
    tree.insert(21); 
    tree.insert(22); 
    tree.insert(15);
    tree.insert(27);
    tree.insert(38);
    tree.insert(4);
    tree.insert(5);
    tree.insert(6);
    tree.insert(7);
    tree.insert(8);
    tree.insert(9);
    tree.insert(10);
    tree.insert(11);
    tree.insert(12);
    tree.insert(13);
    tree.insert(14); 
    tree.insert(15); */

  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Tree24Animation");
    JApplet applet = new Tree24Animation();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}