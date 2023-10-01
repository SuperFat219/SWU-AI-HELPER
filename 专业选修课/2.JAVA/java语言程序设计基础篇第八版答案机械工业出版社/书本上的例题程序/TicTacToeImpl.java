import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.registry.*;

public class TicTacToeImpl extends UnicastRemoteObject
    implements TicTacToeInterface {
  // Declare two players, used to call players back
  private CallBack player1 = null;
  private CallBack player2 = null;

  // board records players' moves
  private char[][] board = new char[3][3];

  /** Constructs TicTacToeImpl object and exports it 
   * on default port. */
  public TicTacToeImpl() throws RemoteException {
    super();
  }

  /** Constructs TicTacToeImpl object and exports it on specified
   * port.
   * @param port The port for exporting
   */
  public TicTacToeImpl(int port) throws RemoteException {
    super(port);
  }

  /**
   * Connect to the TicTacToe server and return the token.
   * If the returned token is ' ', the client is not connected to
   * the server
   */
  public char connect(CallBack client) throws RemoteException {
    if (player1 == null) {
      // player1 (first player) registered
      player1 = client;
      player1.notify("Wait for a second player to join");
      return 'X';
    }
    else if (player2 == null) {
      // player2 (second player) registered
      player2 = client;
      player2.notify("Wait for the first player to move");
      player2.takeTurn(false);
      player1.notify("It is my turn (X token)");
      player1.takeTurn(true);
      return 'O';
    }
    else {
      // Already two players
      client.notify("Two players are already in the game");
      return ' ';
    }
  }

  /** A client invokes this method to notify the 
   * server of its move*/
  public void myMove(int row, int column, char token)
      throws RemoteException {
    // Set token to the specified cell
    board[row][column] = token;

    // Notify the other player of the move
    if (token == 'X')
      player2.mark(row, column, 'X');
    else
      player1.mark(row, column, 'O');

    // Check if the player with this token wins
    if (isWon(token)) {
      if (token == 'X') {
        player1.notify("I won!");
        player2.notify("I lost!");
        player1.takeTurn(false);
      }
      else {
        player2.notify("I won!");
        player1.notify("I lost!");
        player2.takeTurn(false);
      }
    }
    else if (isFull()) {
      player1.notify("Draw!");
      player2.notify("Draw!");
    }
    else if (token == 'X') {
      player1.notify("Wait for the second player to move");
      player1.takeTurn(false);
      player2.notify("It is my turn, (O token)");
      player2.takeTurn(true);
    }
    else if (token == 'O') {
      player2.notify("Wait for the first player to move");
      player2.takeTurn(false);
      player1.notify("It is my turn, (X token)");
      player1.takeTurn(true);
    }
  }

  /** Check if a player with the specified token wins */
  public boolean isWon(char token) {
    for (int i = 0; i < 3; i++)
      if ((board[i][0] == token) && (board[i][1] == token)
        && (board[i][2] == token))
        return true;

    for (int j = 0; j < 3; j++)
      if ((board[0][j] == token) && (board[1][j] == token)
        && (board[2][j] == token))
        return true;

    if ((board[0][0] == token) && (board[1][1] == token)
      && (board[2][2] == token))
      return true;

    if ((board[0][2] == token) && (board[1][1] == token)
      && (board[2][0] == token))
      return true;

    return false;
  }

  /** Check if the board is full */
  public boolean isFull() {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (board[i][j] == '\u0000')
          return false;

    return true;
  }

  public static void main(String[] args) {
    try {
      TicTacToeInterface obj = new TicTacToeImpl();
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("TicTacToeImpl", obj);
      System.out.println("Server " + obj + " registered");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }    
  }
}
