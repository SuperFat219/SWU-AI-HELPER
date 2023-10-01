import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise24_10 extends JApplet {
  public Exercise24_10() {
    add(new HeapView());
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise25_20");
    JApplet applet = new Exercise24_10();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class HeapView extends JPanel {
  private Heap<Integer> heap = new Heap<Integer>();
  private JTextField jtfKey = new JTextField(5);
  private PaintHeap paintTree = new PaintHeap(); 
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Remove the root");
  
  /** Construct a view for a binary tree */
  public HeapView() {   
    setLayout(new BorderLayout()); 
    add(paintTree, BorderLayout.CENTER);    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter a key: "));
    panel.add(jtfKey);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    add(panel, BorderLayout.SOUTH);
    
    // Listener for the Insert button
    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        heap.add(key); // Insert a new key
        paintTree.repaint(); // Redisplay the tree
      }
    });
    
    // Listener for the Delete button
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        heap.remove(); // Delete t
        paintTree.repaint(); // Redisplay the tree
      }
    });
  }
  
  class Heap<E extends Comparable> {
    java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new object into the heap */
    public void add(E newObject) {
      list.add(newObject); // Append to the heap
      int currentIndex = list.size() - 1; // The index of the last node

      while (currentIndex > 0) {
        int parentIndex = (currentIndex - 1) / 2;
        // Swap if the current object is greater than its parent
        if (list.get(currentIndex).compareTo(
            list.get(parentIndex)) > 0) {
          E temp = list.get(currentIndex);
          list.set(currentIndex, list.get(parentIndex));
          list.set(parentIndex, temp);
        }
        else
          break; // the tree is a heap now

        currentIndex = parentIndex;
      }
    }

    /** Remove the root from the heap */
    public E remove() {
      if (list.size() == 0) return null;
      
      E removedObject = list.get(0);
      list.set(0, list.get(list.size() - 1));
      list.remove(list.size() - 1);

      int currentIndex = 0;
      while (currentIndex < list.size()) {
        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;
        
        // Find the maximum between two children
        if (leftChildIndex >= list.size()) break; // The tree is a heap
        int maxIndex = leftChildIndex;
        if (rightChildIndex < list.size()) {
          if (list.get(maxIndex).compareTo(
              list.get(rightChildIndex)) < 0) {
            maxIndex = rightChildIndex;
          }
        }      
        
        // Swap if the current node is less than the maximum 
        if (list.get(currentIndex).compareTo(
            list.get(maxIndex)) < 0) {
          E temp = list.get(maxIndex);
          list.set(maxIndex, list.get(currentIndex));
          list.set(currentIndex, temp);
          currentIndex = maxIndex;
        }   
        else 
          break; // The tree is a heap
      }

      return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return list.size();
    }
  }

  // Inner class PaintHeap for displaying a tree on a panel
  class PaintHeap extends JPanel {   
    private int radius = 20; // Tree node radius
    private int vGap = 50; // Gap between two levels in a tree
        
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Display the nodes in heap recursively    
      displayTree(g, 0, getWidth() / 2, 30, getWidth() / 4); 
    }
        
    /** Display a subtree root at position (x, y) */
    private void displayTree(Graphics g, int index, 
        int x, int y, int hGap) {
      if (index < heap.getSize()) {
        // Display root
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.drawString(heap.list.get(index) + "", x - 6, y + 4);
            
        // Draw a line to the left node
        int indexOfLeft = 2 * index + 1;
        if (indexOfLeft < heap.getSize())
          connectLeftChild(g, x - hGap, y + vGap, x, y);
            
        // Draw the left subtree
        displayTree(g, indexOfLeft, x - hGap, y + vGap, hGap / 2);
          
        // Draw a line to the right node
        int indexOfRight = 2 * index + 2;        
        if (indexOfRight < heap.getSize())
          connectRightChild(g, x + hGap, y + vGap, x, y);
            
        // Draw the right subtree
        displayTree(g, indexOfRight, x + hGap, y + vGap, hGap / 2);      
      }
    }
        
    /** Connect a parent at (x2, y2) with 
     * its left child at (x1, y1) */
    private void connectLeftChild(Graphics g, 
        int x1, int y1, int x2, int y2) { 
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 + radius * (x2 - x1) / d);
      int y11 = (int)(y1 - radius * vGap / d);
      int x21 = (int)(x2 - radius * (x2 - x1) / d);
      int y21 = (int)(y2 + radius * vGap / d);
      g.drawLine(x11, y11, x21, y21);
    }
        
    /** Connect a parent at (x2, y2) with 
     * its right child at (x1, y1) */
    private void connectRightChild(Graphics g, 
        int x1, int y1, int x2, int y2) {
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      int x11 = (int)(x1 - radius * (x1 - x2) / d);
      int y11 = (int)(y1 - radius * vGap / d);
      int x21 = (int)(x2 + radius * (x1 - x2) / d);
      int y21 = (int)(y2 + radius * vGap / d);
      g.drawLine(x11, y11, x21, y21);
    }
  }
}
