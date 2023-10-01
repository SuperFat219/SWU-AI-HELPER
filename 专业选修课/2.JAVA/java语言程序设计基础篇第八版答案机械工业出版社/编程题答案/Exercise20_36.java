public class Exercise20_36 {
  public static void main(String[] args) {
    // Read a Sudoku puzzle from the console
    int[][] grid = readAPuzzle();

    if (!isValid(grid)) 
      System.out.println("The input is not valid");      
    else if (search(grid)) // Start search from (0, 0)
      printGrid(grid);
    else
      System.out.println("No solutions");
  }

  /** Check whether the fixed cells are valid in the grid */
  public static boolean isValid(int[][] grid) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] != 0 && !isValid(i, j, grid)) return false;

    return true; // The fixed cells are valid
  }

  /** Search for a solution */
  public static boolean search(int[][] grid) {
    // isFree[i][j] is true if (i, j) needs to be filled
    boolean[][] isFree = findFreeCells(grid);

    return search(0, 0, grid, isFree);
  }
  
  /** Read a Sudoku puzzle from the keyboard */
  private static int[][] readAPuzzle() {
    // Create a Scanner
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.println("Enter a Sudoku puzzle:");
    int[][] grid = new int[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        grid[i][j] = input.nextInt();

    return grid;
  }

  /** Set isFreeCell[i][j] to true if (i, j) is not fixed */
  private static boolean[][] findFreeCells(int[][] grid) {
    boolean[][] isFree = new boolean[9][9];

    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        isFree[i][j] = true;

    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] > 0)
          isFree[i][j] = false;

    return isFree;
  }

  static boolean found = false; // true when one solution is found

  private static boolean search(int row, int column, int[][] grid,
      boolean[][] isFree) {

    if (!isFree[row][column]) {
      // Fill in the next cell
      if (column < 9 - 1)
        search(row, column + 1, grid, isFree);
      else if (row < 9 - 1)
        search(row + 1, 0, grid, isFree);
      else {
        found = true;
      }
    } 
    else {
      for (int i = 1; i < 10 && !found; i++) {
        if (isValid(row, column, i, grid)) {
          grid[row][column] = i;

          // Fill in the next cell
          if (column < 9 - 1)
            search(row, column + 1, grid, isFree);
          else if (row < 9 - 1)
            search(row + 1, 0, grid, isFree);
          else {
            found = true;
          }
        }
      }

      if (!found)
        grid[row][column] = 0;
    }

    return found;
  }
  
  /** Check whether placing v at grid[i][j] is valid */
  private static boolean isValid(int i, int j, int v, int[][] grid) {
    // Check whether v at grid[i][j] is valid at the i's row
    for (int column = 0; column < 9; column++)
      if (column != j && grid[i][column] == v)
        return false;

    // Check whether v at grid[i][j] is valid at the j's column
    for (int row = 0; row < 9; row++)
      if (row != i && grid[row][j] == v)
        return false;

    // Check whether v at grid[i][j] is valid in the 3 by 3 box
    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
      for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
        if (row != i && col != j && grid[row][col] == v)
          return false;

    return true; // The current value at grid[i][j] is valid
  }
  
  /** Check whether grid[i][j] is valid in the grid */
  private static boolean isValid(int i, int j, int[][] grid) {
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
  
  /** Print the values in the grid */
  public static void printGrid(int[][] grid) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++)
        System.out.print(grid[i][j] + " ");
      System.out.println();
    }
  }
}
