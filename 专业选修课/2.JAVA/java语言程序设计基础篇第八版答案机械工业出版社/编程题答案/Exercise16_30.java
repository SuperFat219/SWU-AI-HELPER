import java.awt.Point;

public class Exercise16_30 {
  private int N = 16; // The number of lattice cells in one row
  private Point[][] lattice;
  // (i, j) is the current path point. Initially it is at the center
  private int i = (N + 1) / 2;  
  private int j = (N + 1) / 2; 
  
  private boolean getAPath() {   
    // Refresh lattice
    for (int i = 0; i < lattice.length; i++)
      for (int j = 0; j < lattice.length; j++)
        lattice[i][j] = null;

    // Reset i, j to the center of the lattice
    i = (N + 1) / 2;  
    j = (N + 1) / 2; 

    while (i > 0 && i < N && j > 0 && j < N) {
      if (lattice[i - 1][j] != null && lattice[i + 1][j] != null &&
          lattice[i][j - 1] != null && lattice[i][j + 1] != null) {
        return false; // Dead end
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
    
    return true;
  }
  
  public Exercise16_30() {
    for (N = 10; N <= 80; N++) {
      lattice = new Point[N + 1][N + 1];
      
      // Run simulation 10000 times
      int count = 0;
      for (int i = 0; i < 10000; i++) {
        if (!getAPath()) {
          count++;
        }
      }
      System.out.println("For lattice of size " + N + ", the probability of dead-end paths is " + (count / 10000.0) * 100 + "%");
    }
  }
  
  public static void main(String[] args) {
    new Exercise16_30();
  }
}
