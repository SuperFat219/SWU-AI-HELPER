// Exercise20_26.java: The Maze problem
// Comment the block() and unblock() methods to show Figure 10.30 for jbBook
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise20_26 extends JApplet {
  private Cell[][] board = new Cell[8][8];
  private JButton jbtFindPath = new JButton("Find Path");
  private JButton jbtClearPath = new JButton("Clear Path");
  private JPanel jpBoard, jpButton;
  private JLabel jlblStatus = new JLabel();

  public void init() {
    jpBoard = new JPanel();
    jpBoard.setLayout(new GridLayout(8, 8, 2, 2));

    jpButton = new JPanel();
    jpButton.setLayout(new FlowLayout());
    jpButton.add(jbtFindPath);
    jpButton.add(jbtClearPath);

    // Add cells to jpBoard
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = new Cell();
        jpBoard.add(board[row][col]);
      }
    }

    // Add jpBoard, jpButtons and jlblStatus to the applet
    setLayout(new BorderLayout());
    add(jlblStatus, BorderLayout.NORTH);
    add(jpBoard, BorderLayout.CENTER);
    add(jpButton, BorderLayout.SOUTH);

    // Register listeners
    jbtFindPath.addActionListener(new Listener());
    jbtClearPath.addActionListener(new Listener());
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise20_26");

    // Create an instance of MortgageApplet
    Exercise20_26 applet = new Exercise20_26();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class Listener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
	    String arg = e.getActionCommand();
	    if (e.getSource() instanceof JButton) {
	      if ("Find Path".equals(arg)) {
	        findPath();
	      }
	      else if ("Clear Path".equals(arg)) {
	        clearPath();
	      }
	    }
	  }
  }

  public void findPath() {
    if (findPath(0, 0)) {
      jlblStatus.setText("path found");
    }
    else {
      jlblStatus.setText("No path exists");
    }
  }

  public boolean findPath(int row, int col) {
    board[row][col].visit();

    if ((col == 7) && (row == 7)) {
      board[row][col].selectCell();
      return true;
    }

    if ((row > 0) && !board[row - 1][col].marked() &&
        !board[row - 1][col].blocked() && !board[row - 1][col].visited()) {
      block(row, col);

      if (findPath(row - 1, col)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row, col);
    }

    if ((row < 7) && !board[row + 1][col].marked() &&
        !board[row + 1][col].blocked() && !board[row + 1][col].visited()) {
      block(row, col);

      if (findPath(row + 1, col)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row, col);
    }

    if ((col > 0) && !board[row][col - 1].marked() &&
        !board[row][col - 1].blocked() && !board[row][col - 1].visited()) {
      block(row,col);
      if (findPath(row, col - 1)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row,col);
    }

    if ((col < 7) && !board[row][col + 1].marked() &&
        !board[row][col + 1].blocked() && !board[row][col + 1].visited()) {
      block(row,col);
      if (findPath(row, col + 1)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row,col);
    }

    return false;
  }

  // Temporary block the neighbor to prevent neighboring path
  public void block(int row, int col) {
    if (row > 0) {
      board[row - 1][col].block();
    }

    if (row < 7) {
      board[row + 1][col].block();
    }

    if (col > 0) {
      board[row][col - 1].block();
    }

    if (col < 7) {
      board[row][col + 1].block();
    }
  }

  // Remove the temporary block
  public void unblock(int row, int col) {
    if (row > 0) {
      board[row - 1][col].unblock();
    }

    if (row < 7) {
      board[row + 1][col].unblock();
    }

    if (col > 0) {
      board[row][col - 1].unblock();
    }

    if (col < 7) {
      board[row][col + 1].unblock();
    }
  }

  public void clearPath() {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col].deselectCell();
      }
    }
  }

  // Inner class
  class Cell extends JPanel implements MouseListener {
    private boolean marked = false;
    private boolean visited = false;
    private boolean blocked = false;
    private boolean cellSelected = false;

    public Cell() {
      setBackground(Color.white);
      addMouseListener(this);
    }

    public boolean marked() {
      return marked;
    }

    public void visit() {
      visited = true;
    }

    public boolean visited() {
      return visited;
    }

    public boolean blocked() {
      return blocked;
    }

    public void block() {
      blocked = true;
    }

    public void unblock() {
      blocked = false;
    }

    public void selectCell() {
      setBackground(Color.red);

      repaint();
    }

    public void deselectCell() {
      setBackground(Color.white);
      repaint();
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (marked) {
        g.drawLine(0, 0, getSize().width, getSize().height);
        g.drawLine(getSize().width, 0, 0, getSize().height);
      }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
      marked = !marked;
      repaint();
    }
  }
}
