import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise16_28 extends JFrame {
  final static int N = 16; // The number of lattice cells in one row
  private Point[][] lattice = new Point[N + 1][N + 1];
  
  // (i, j) is the current path point. Initially it is at the center
  private int i = (N + 1) / 2;  
  private int j = (N + 1) / 2; 
  
  private JButton jbtStart = new JButton("Start");
  
  public Exercise16_28() {
    add(new Lattice());
    JPanel panel = new JPanel();
    panel.add(jbtStart);
    panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    add(panel, BorderLayout.SOUTH);
    
    jbtStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Compute a random walk       
        getAPath();
        
        repaint();
      }
    });
  }
  
  private void getAPath() {
    
    // Refresh lattice
    for (int i = 0; i < lattice.length; i++)
      for (int j = 0; j < lattice.length; j++)
        lattice[i][j] = null;
    
    i = (N + 1) / 2;
    j = (N + 1) / 2; 
    
    while (i > 0 && i < N && j > 0 && j < N) {
      if (lattice[i - 1][j] != null && lattice[i + 1][j] != null &&
          lattice[i][j - 1] != null && lattice[i][j + 1] != null) {
        break; // Dead end
      }
      
      double r = Math.random();
      if (r < .25 && lattice[i][j + 1] == null) {
        lattice[i][j] = new Point(i, j + 1); // Right
        j++;
      }
      else if (r < .50 && lattice[i + 1][j] == null) {
        lattice[i][j] = new Point(i + 1, j); // Down
        i++;
      }
      else if (r < .75 && lattice[i][j - 1] == null) {
        lattice[i][j] = new Point(i, j - 1); // Left
        j--;
      }
      else if (r < 1.00 && lattice[i - 1][j] == null) {
        lattice[i][j] = new Point(i - 1, j); // Up
        i--;
      }      
    }
  }
  
  class Lattice extends JPanel {
    protected void paintComponent(Graphics g) {     
      int vGap = getHeight() / N;
      int hGap = getWidth() / N;
      
      // Draw lattice lines
      g.setColor(Color.LIGHT_GRAY);
      for (int i = 0; i < N; i++) {
        g.drawLine(0, i * vGap, getWidth(), i * vGap);
        g.drawLine(i * hGap, 0, i * hGap, getHeight());
      }
      
      // Draw a path
      g.setColor(Color.BLACK);
      // Start from the center point in the lattice (i, j)
      int i = (N + 1) / 2;
      int j = (N + 1) / 2; 
      while (lattice[i][j] != null) {
        Point p = lattice[i][j];
        g.drawLine(i * hGap, j * vGap, p.x * hGap, p.y * vGap);
        i = p.x;
        j = p.y;
      }
    }
  }
  
  public static void main(String[] args) {
    JFrame frame = new Exercise16_28();
    frame.setTitle("Exercise16_28");
    frame.setSize(400, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
