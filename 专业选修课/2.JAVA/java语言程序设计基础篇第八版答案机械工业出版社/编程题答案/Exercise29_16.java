import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Exercise29_16 extends JApplet {
  private JTextField[][] cells = new JTextField[9][9];

  private JButton jbtSolve = new JButton("Solve");

  private JButton jbtClear = new JButton("Clear");

  public Exercise29_16() {
    JPanel[][] panels = new JPanel[3][3];
    JPanel p1 = new JPanel(new GridLayout(3, 3, 0, 0));
    p1.setBorder(new LineBorder(Color.BLACK, 2));
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++) {
        p1.add(panels[i][j] = new JPanel(new GridLayout(3, 3)));
        panels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        panels[i / 3][j / 3].add(cells[i][j] = new JTextField());
        cells[i][j].setHorizontalAlignment(JTextField.CENTER);
      }
    }

    add(p1); // Add p1 to the applet

    JPanel p2 = new JPanel(new FlowLayout());
    p2.add(jbtSolve);
    p2.add(jbtClear);
    add(p2, BorderLayout.SOUTH);

    jbtSolve.addActionListener(new SolveActionListenerClass());
    jbtClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++)
          for (int j = 0; j < 9; j++) {
            cells[i][j].setText(null);
            cells[i][j].setBackground(Color.WHITE);
          }
      }
    });
  }

  int[][] grid = new int[9][9];

  class SolveActionListenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (cells[i][j].getText().trim().length() == 0) {
            grid[i][j] = 0;
          } else {
            grid[i][j] = Integer.parseInt(cells[i][j].getText());
            cells[i][j].setBackground(Color.LIGHT_GRAY);
          }
        }
      }

      Thread thread = new Thread(new RunOnSeparateThread());
      thread.start();
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise29_16");
    JApplet applet = new Exercise29_16();
    frame.add(applet);
    frame.setSize(300, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /** Obtain a list of free cells from the puzzle */
  public static int[][] getFreeCellList(int[][] grid) {
    // Determine the number of free cells
    int numberOfFreeCells = 0;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] == 0)
          numberOfFreeCells++;

    // Store free cell positions into freeCellList
    int[][] freeCellList = new int[numberOfFreeCells][2];
    int count = 0;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] == 0) {
          freeCellList[count][0] = i;
          freeCellList[count++][1] = j;
        }

    return freeCellList;
  }


  /** Search for a solution */
  public boolean search(int[][] grid) {
    int[][] freeCellList = getFreeCellList(grid); // Free cells
    int k = 0; // Start from the first free cell
    boolean found = false; // Solution found?

    while (!found) {
      int i = freeCellList[k][0];
      int j = freeCellList[k][1];
      if (grid[i][j] == 0)
        grid[i][j] = 1; // Start with 1
      
      // Redisplay the grid
      setANewValue(grid, i, j);

      if (isValid(i, j, grid)) {
        if (k + 1 == freeCellList.length) { // No more free cells
          found = true; // A solution is found
        } else { // Move to the next free cell
          k++;
        }
      } else if (grid[i][j] < 9) {
        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      } else { // grid[i][j] is 9, backtrack
        while (grid[i][j] == 9) {
          grid[i][j] = 0; // Reset to free cell
          if (k == 0) {
            return false; // No possible value
          }
          k--; // Backtrack
          i = freeCellList[k][0];
          j = freeCellList[k][1];
        }

        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      }
    }

    return true; // A solution is found
  }

  /** Check whether grid[i][j] is valid in the grid */
  public static boolean isValid(int i, int j, int[][] grid) {
    // Check whether grid[i][j] is valid at the i's row
    for (int column = 0; column < 9; column++)
      if (column != j && grid[i][column] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid at the j's column
    for (int row = 0; row < 9; row++)
      if (row != i && grid[row][j] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid in the 3 by 3 box
    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
      for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
        if (row != i && col != j && grid[row][col] == grid[i][j])
          return false;

    return true; // The current value at grid[i][j] is valid
  }

  /** Check whether the fixed cells are valid in the grid */
  public static boolean isValid(int[][] grid) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] < 0 || grid[i][j] > 9
            || (grid[i][j] != 0 && !isValid(i, j, grid)))
          return false;

    return true; // The fixed cells are valid
  }

  /** Check whether the fixed cells are valid in the grid */
  public void setANewValue(int[][] grid, int i1, int j1) {
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
          cells[i][j].setForeground(Color.BLACK);
      }
    }
    
    cells[i1][j1].setForeground(Color.RED);
    displayGrid(grid);   
    
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  public void displayGrid(int[][] grid) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (grid[i][j] == 0)
          cells[i][j].setText(null);
        else
          cells[i][j].setText(grid[i][j] + "");
      }
    }
  }

  class RunOnSeparateThread implements Runnable {
    public void run() {
      if (!isValid(grid)) {
        JOptionPane.showMessageDialog(null, "Invalid Input");
      } else if (search(grid)) {
        displayGrid(grid);
      } else {
        JOptionPane.showMessageDialog(null, "No solution");
      }
    }
  }
}