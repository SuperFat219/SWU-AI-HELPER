import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TicTacToeClientRMI extends JApplet {
  // marker is used to indicate the token type
  private char marker;

  // myTurn indicates whether the player can move now
  private boolean myTurn = false;

  // Each cell can be empty or marked as 'O' or 'X'
  private Cell[][] cell;

  // ticTacToe is the game server for coordinating 
  // with the players
  private TicTacToeInterface ticTacToe;

  // Border for cells and panel
  private Border lineBorder =
    BorderFactory.createLineBorder(Color.yellow, 1);

  private JLabel jlblStatus = new JLabel("jLabel1");
  private JLabel jlblIdentification = new JLabel();

  boolean isStandalone = false;

  /** Initialize the applet */
  public void init() {
    JPanel jPanel1 = new JPanel();
    jPanel1.setBorder(lineBorder);
    jPanel1.setLayout(new GridLayout(3, 3, 1, 1));

    add(jlblStatus, BorderLayout.SOUTH);
    add(jPanel1, BorderLayout.CENTER);
    add(jlblIdentification, BorderLayout.NORTH);

    // Create cells and place cells in the panel
    cell = new Cell[3][3];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        jPanel1.add(cell[i][j] = new Cell(i, j));

    try {
      initializeRMI();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Initialize RMI */
  protected boolean initializeRMI() throws Exception {
    String host = "";
    if (!isStandalone) host = getCodeBase().getHost();
  
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      ticTacToe = (TicTacToeInterface) 
        registry.lookup("TicTacToeImpl");
      System.out.println
        ("Server object " + ticTacToe + " found");
    } 
    catch (Exception ex) {
      System.out.println(ex);
    }

    // Create callback for use by the 
    // server to control the client
    CallBackImpl callBackControl = new CallBackImpl(this);

    if (
      (marker = 
        ticTacToe.connect((CallBack)callBackControl)) != ' ')
    {
      System.out.println("connected as " + marker + " player.");
      jlblIdentification.setText("You are player " + marker);
      return true;
    }
    else {
      System.out.println("already two players connected as ");
      return false;
    }
  }

  /** Set variable myTurn to true or false */
  public void setMyTurn(boolean myTurn) {
    this.myTurn = myTurn;
  }

  /** Set message on the status label */
  public void setMessage(String message) {
    jlblStatus.setText(message);
  }

  /** Mark the specified cell using the token */
  public void mark(int row, int column, char token) {
    cell[row][column].setToken(token);
  }

  /** Inner class Cell for 
   *  modeling a cell on the TicTacToe board */
  private class Cell extends JPanel {
    // marked indicates whether the cell has been used
    private boolean marked = false;

    // row and column indicate where the cell 
    // appears on the board
    int row, column;

    // The token for the cell
    private char token;

    /** Construct a cell */
    public Cell(final int row, final int column) {
      this.row = row;
      this.column = column;
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (myTurn && !marked) {
            // Mark the cell
            setToken(marker);

            // Notify the server of the move
            try {
              ticTacToe.myMove(row, column, marker);
            }
            catch (RemoteException ex) {
              System.out.println(ex);
            }
          }
        }
      });

      setBorder(lineBorder);
    }

    /** Set token on a cell (mark a cell) */
    public void setToken(char c) {
      token = c;
      marked = true;
      repaint();
    }

    /** Paint the cell to draw a shape for the token */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Draw the border
      g.drawRect(0, 0, getSize().width, getSize().height);

      if (token == 'X') {
        g.drawLine(10, 10, getSize().width - 10,
          getSize().height - 10);
        g.drawLine(getSize().width - 10, 10, 10,
          getSize().height - 10);
      }
      else if (token == 'O') {
        g.drawOval(10, 10, getSize().width - 20,
          getSize().height - 20);
      }
    }
  }

  /** Main method */
  public static void main(String[] args) {
    TicTacToeClientRMI applet = new TicTacToeClientRMI();
    applet.isStandalone = true;
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("TicTacToeClientRMI");
    frame.add(applet, BorderLayout.CENTER);
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}