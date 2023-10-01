import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Tree24View extends JPanel {
  private Tree24<Integer> tree; // A binary tree to be displayed
  private JTextField jtfKey = new JTextField(5);
  private PaintTree paintTree = new PaintTree(); 
  private JButton jbtSearch = new JButton("Search");
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Delete");
  
  /** Construct a view for a binary tree */
  public Tree24View(Tree24<Integer> tree) {   
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
    panel.add(jbtSearch);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    add(panel, BorderLayout.SOUTH);
    
    // Listener for the Insert button
    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (tree.search(key)) { // key is in the tree already
          JOptionPane.showMessageDialog(null, 
            key + " is already in the tree");
        }
        else {
          tree.insert(key); // Insert a new key
          paintTree.repaint(); // Redisplay the tree
        }
      }
    });
    
    // Listener for the Search button
    jbtSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (!tree.search(key)) {
          JOptionPane.showMessageDialog(null, key + " is not in the tree");
        }
        else {
          JOptionPane.showMessageDialog(null, key + " is in the tree");
        }
      }
    });

    // Listener for the Delete button
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(jtfKey.getText());
        if (!tree.search(key)) { // key is not in the tree
          JOptionPane.showMessageDialog(null, 
            key + " is not in the tree");
        }
        else {
          tree.delete(key); // Delete a key
          paintTree.repaint(); // Redisplay the tree
        }
      }
    });
  }
  
  // Inner class PaintTree for displaying a tree on a panel
  class PaintTree extends JPanel {   
    private int radius = 20; // Tree node radius
    
    private int width = 90;
    private int height = 20;
    
    private int vGap = 50; // Gap between two levels in a tree
        
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (tree.getRoot() != null) {
        // Display tree recursively    
        displayTree(g, tree.getRoot(), getWidth() / 2, 30, 
          getWidth() / 4); 
      }
    }
       
    /** Display a node with one key */
    private void displayOneKeyNode(Graphics g, Tree24.Tree24Node root, 
        int x, int y, int hGap) {
      // Display the root
      g.drawRoundRect(x - width / 6, y - height / 2, 
        width / 3, height, 10, 10);
      g.drawString(root.elements.get(0) + "", x - 6, y + 4);
            
      if (root.child.size() > 0) {
        // Draw a line to the left node
        g.drawLine(x - width / 6, y + height / 2,
          x - hGap / 2, y + vGap - height / 2);
      
        // Draw the left subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(0)), 
          x - hGap / 2, y + vGap, hGap / 3);  
        
        // Draw a line to the right node
        g.drawLine(x + width / 6, y + height / 2,
          x + hGap / 2, y + vGap - height / 2);
      
        // Draw the right subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(1)), 
          x + hGap / 2, y + vGap, hGap / 3);  
      }
    }

    /** Display a node with two keys */
    private void displayTwoKeyNode(Graphics g, Tree24.Tree24Node root, 
        int x, int y, int hGap) {
      // Display the root
      g.drawRoundRect(x - width / 3, y - height / 2, 
        2* width / 3, height, 10, 10);
      g.drawString(root.elements.get(0) + "", x + 10 - width / 3, y + 4);
      g.drawString(root.elements.get(1) + "", x + 10, y + 4);
            
      if (root.child.size() > 0) {
        // Draw a line to the left node
        g.drawLine(x - width / 6, y + height / 2,
          x - 2 * hGap / 3, y + vGap - height / 2);
      
        // Draw the left subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(0)), 
          x - 2 * hGap / 3, y + vGap, hGap / 3);  
        
        // Draw a line to the center child node
        g.drawLine(x, y + height / 2, x, y + vGap - height / 2);
      
        // Draw the center subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(1)), 
          x, y + vGap, hGap / 3);  
        
        // Draw a line to the right node
        g.drawLine(x + width / 6, y + height / 2,
          x + 2 * hGap / 3, y + vGap - height / 2);
      
        // Draw the right subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(2)), 
          x + 2 * hGap / 3, y + vGap, hGap / 3);  
      }
    }
    
    /** Display a node with one key */
    private void displayThreeKeyNode(Graphics g, Tree24.Tree24Node root, 
        int x, int y, int hGap) {
      // Display the root
      g.drawRoundRect(x - width / 2, y - height / 2, 
        width, height, 10, 10);
      g.drawString(root.elements.get(0) + "", x - 6 - width / 3, y + 4);
      g.drawString(root.elements.get(1) + "", x - 6, y + 4);
      g.drawString(root.elements.get(2) + "", x - 6 + width / 3, y + 4);
            
      if (root.child.size() > 0) {
        // Draw a line to the 1st child node
        g.drawLine(x - width / 2, y + height / 2,
          x - hGap, y + vGap - height / 2);
      
        // Draw the 1st child subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(0)), 
          x - hGap, y + vGap, hGap / 3);  
        
        // Draw a line to the 2nd child node
        g.drawLine(x - width / 6, y + height / 2,
          x - hGap / 3, y + vGap - height / 2);
      
        // Draw the second child subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(1)), 
          x - hGap / 3, y + vGap, hGap / 3);  
        
        // Draw a line to the 3rd child node
        g.drawLine(x + width / 6, y + height / 2,
          x + hGap / 3, y + vGap - height / 2);
      
        // Draw the 3rd child subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(2)), 
          x + hGap / 3, y + vGap, hGap / 3);  
        
        // Draw a line to the 4th child node
        g.drawLine(x + width / 2, y + height / 2,
          x + hGap, y + vGap - height / 2);
      
        // Draw the 4th child subtree recursively
        displayTree(g, (Tree24.Tree24Node)(root.child.get(3)), 
          x + hGap, y + vGap, hGap / 3);  
      }
    }
    
    /** Display a subtree rooted at position (x, y) */
    private void displayTree(Graphics g, Tree24.Tree24Node root, 
        int x, int y, int hGap) {     
      if (root.elements.size() == 1) 
        displayOneKeyNode(g, root, x, y, hGap);
      else if (root.elements.size() == 2) 
        displayTwoKeyNode(g, root, x, y, hGap);
      else if (root.elements.size() == 3) 
        displayThreeKeyNode(g, root, x, y, hGap);
    }
  }
}