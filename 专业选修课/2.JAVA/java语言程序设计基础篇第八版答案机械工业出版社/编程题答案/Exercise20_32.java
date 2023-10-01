import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Exercise20_32 extends JApplet {
  private static final int SIZE = 8;

  private int startX = 0;

  private int startY = 0;

  private ArrayList moveHistory = null;

  /**
   * @param args
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("Knight's Tour");
    Exercise20_32 kp = new Exercise20_32();
    frame.add(kp, BorderLayout.CENTER);
    kp.init();
    kp.start();

    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }

  public Exercise20_32() {
    this.setLayout(new BorderLayout());
    Chessboard board = new Chessboard();
    add(board, BorderLayout.CENTER);
    JPanel jpButtonPanel = new JPanel();
    JButton jbSolve = new JButton("Solve");
    jbSolve.addActionListener(new Listener());
    jbSolve.setPreferredSize(new Dimension(75, 25));
    jpButtonPanel.add(jbSolve);
    add(jpButtonPanel, BorderLayout.SOUTH);
  }

  // This method does the bulk of the work.
  // I'm not *thrilled* with this solution as
  // it has more redundant code than I'd prefer
  // but it gets the job done and done efficiently.
  // Uses Warnsdorf's heuristic discovered in 1823
  // that says the best move is the one with the
  // fewest next moves. I found it necessary to
  // back up in only one case (3,0) and choose to
  // try the second best move which worked well.
  private boolean solvePuzzle(boolean[][] moves, int numMoves, int x, int y) {
    int nextX = 0;
    int nextY = 0;
    int bestMoveX = 0;
    int bestMoveY = 0;
    int bestMoveX2 = 0;
    int bestMoveY2 = 0;
    int minMoveCount = 8; // Knight has max of 8 moves
    int moveCount = 0;

    for (int i = 2; i >= -2; i += -4) {
      for (int j = 1; j >= -1; j += -2) {
        nextX = x + i;
        nextY = y + j;
        if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
            && !moves[nextX][nextY]) {
          moveCount = lookAheadCount(moves, nextX, nextY);
          if (moveCount <= minMoveCount) {
            minMoveCount = moveCount;
            bestMoveX2 = bestMoveX;
            bestMoveY2 = bestMoveY;
            bestMoveX = nextX;
            bestMoveY = nextY;
          }
        }

        nextX = x + j;
        nextY = y + i;
        if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
            && !moves[nextX][nextY]) {
          moveCount = lookAheadCount(moves, nextX, nextY);
          if (moveCount <= minMoveCount) {
            minMoveCount = moveCount;
            bestMoveX2 = bestMoveX;
            bestMoveY2 = bestMoveY;
            bestMoveX = nextX;
            bestMoveY = nextY;
          }
        }
      }
    }
    moves[bestMoveX][bestMoveY] = true;
    addMoveHistory(bestMoveX, bestMoveY);
    numMoves++;
    if (numMoves == (SIZE * SIZE))
      return true;
    if (moveCount > 0 && solvePuzzle(moves, numMoves, bestMoveX, bestMoveY)) {
      return true;
    }
    moves[bestMoveX][bestMoveY] = false;
    moves[bestMoveX2][bestMoveY2] = true;
    removeLastMoveHistory();
    addMoveHistory(bestMoveX2, bestMoveY2);
    if (moveCount > 1 && solvePuzzle(moves, numMoves, bestMoveX2, bestMoveY2)) {
      return true;
    }
    moves[bestMoveX2][bestMoveY2] = false;
    removeLastMoveHistory();
    numMoves--;
    return false;
  }

  private int lookAheadCount(boolean[][] moves, int x, int y) {
    int maxCount = 0;
    int nextX = 0;
    int nextY = 0;
    for (int i = -2; i <= 2; i += 4) {
      for (int j = -1; j <= 1; j += 2) {
        nextX = x + i;
        nextY = y + j;
        if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
            && !moves[nextX][nextY]) {
          maxCount++;
        }

        nextX = x + j;
        nextY = y + i;
        if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
            && !moves[nextX][nextY]) {
          maxCount++;
        }
      }
    }
    return maxCount;
  }

  class Listener implements ActionListener {
	  public void actionPerformed(ActionEvent ae) {
	    boolean[][] moves = new boolean[SIZE][SIZE];
	    moves[startX][startY] = true;
	    resetMoveHistory();
	    addMoveHistory(startX, startY);
	    solvePuzzle(moves, 1, startX, startY);
	    repaint();
	  }
  }

  public void resetMoveHistory() {
    moveHistory = new ArrayList(63);
  }

  public void addMoveHistory(int x, int y) {
    moveHistory.add(new Point(x, y));
  }

  public void removeLastMoveHistory() {
    moveHistory.remove(moveHistory.size() - 1);
  }

  private class Chessboard extends JPanel {
    Image knightImage = new ImageIcon("image/knight.jpg").getImage();

    Chessboard() {
      this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      this.addMouseListener(new ChessMouseAdapter());
    }

    protected void paintComponent(Graphics g) {
      // clear previous drawing
      g.clearRect(0, 0, getWidth(), getHeight());
      // Add the Knight image
      g.drawImage(knightImage, startX * getWidth() / SIZE, startY * getHeight()
          / SIZE, getWidth() / SIZE, getHeight() / SIZE, this);
      // Paint the lines
      for (int i = 1; i < SIZE; i++) {
        g.drawLine(0, i * getHeight() / SIZE, getWidth(), i * getHeight()
            / SIZE);
        g
            .drawLine(i * getWidth() / SIZE, 0, i * getWidth() / SIZE,
                getHeight());
      }

      // Draw the moves
      if (moveHistory != null) {
        Point p1 = null;
        Point p2 = null;
        for (int i = 1; i < moveHistory.size(); i++) {
          p1 = (Point) moveHistory.get(i - 1);
          p2 = (Point) moveHistory.get(i);
          g
              .drawLine((int) (p1.getX() * (getWidth() / SIZE))
                  + (getWidth() / SIZE / 2),
                  (int) (p1.getY() * (getHeight() / SIZE))
                      + (getHeight() / SIZE / 2),
                  (int) (p2.getX() * (getWidth() / SIZE))
                      + (getWidth() / SIZE / 2),
                  (int) (p2.getY() * (getHeight() / SIZE))
                      + (getHeight() / SIZE / 2));
        }
      }
    }

    public class ChessMouseAdapter extends MouseAdapter {
      public void mouseClicked(MouseEvent me) {
        startX = me.getX() / (getWidth() / SIZE);
        startY = me.getY() / (getHeight() / SIZE);
        resetMoveHistory();
        repaint();
      }
    }
  }
}
