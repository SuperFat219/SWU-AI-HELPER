import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise18_6 extends JApplet {
  // Indicate which player has a turn, initially it is the X player
  char whoseTurn = 'X';

  // Create and initialize cells
  private Cell[][] cells =  new Cell[3][3];

  // Create and initialized a status label
  JLabel jlblStatus = new JLabel("X's turn playing");

  // Create a button to start a new game
  private JButton jbtNew = new JButton("New Game");

  // Initialize UI
  public void init() {
    // Panel p to hold cells
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 3, 0, 0));
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        p.add(cells[i][j] = new Cell(this));

    // Set line borders on the cells panel and the status label
    p.setBorder(new LineBorder(Color.red, 1));
    jlblStatus.setBorder(new LineBorder(Color.yellow, 1));

    // Place the panel and the label to the applet
    this.add(jbtNew, BorderLayout.NORTH);
    this.add(p, BorderLayout.CENTER);
    this.add(jlblStatus, BorderLayout.SOUTH);

    jbtNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++)
          for (int j = 0; j < 3; j++) {
            cells[i][j].setToken(' ');
            cells[i][j].repaint();
          }

          jlblStatus.setText("X's turn. Restart the game");
          whoseTurn = 'X';
      }    	
    });
  }

  // This main method enables the applet to run as an application
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise18_6");

    // Create an instance of the applet
    Exercise18_6 applet = new Exercise18_6();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Determine if the cells are all occupied
  public boolean isFull() {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (cells[i][j].getToken() == ' ')
          return false;

    return true;
  }

  // Determine if the player with the specified toekn wins
  public boolean won(char token) {
    for (int i = 0; i < 3; i++)
      if ((cells[i][0].getToken()==token)
          && (cells[i][1].getToken()==token)
          && (cells[i][2].getToken()==token)) {
      // disable actions
      whoseTurn = ' ';

      return true;
    }

    for (int j=0; j<3; j++)
      if ((cells[0][j].getToken()==token)
          && (cells[1][j].getToken()==token)
          && (cells[2][j].getToken()==token)) {
      // disable actions
      whoseTurn = ' ';
      return true;
    }

    if ((cells[0][0].getToken()==token)
        && (cells[1][1].getToken()==token)
        && (cells[2][2].getToken()==token)) {
      // disable actions
      whoseTurn = ' ';
      return true;
    }

    if ((cells[0][2].getToken()==token)
        && (cells[1][1].getToken()==token)
        && (cells[2][0].getToken()==token)) {
      // disable actions
      whoseTurn = ' ';
      return true;
    }

    return false;
  }
}

// Now it is a standalone class
class Cell extends JPanel implements MouseListener {
  // Token used for this cell
  private char token = ' ';

  // Reference to parent
  Exercise18_6 parent;

  public Cell(Exercise18_6 parent) {
    this.parent = parent;

    setBorder(new LineBorder(Color.black, 1)); // Set cell's border
    addMouseListener(this);  // Register listener
  }

  // The getter method for token
  public char getToken() {
    return token;
  }

  // The setter method for token
  public void setToken(char c) {
    token = c;
    repaint();
  }

  // Paint the cell
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (token == 'X') {
      g.drawLine(10, 10, getSize().width - 10, getSize().height - 10);
      g.drawLine(getSize().width - 10, 10, 10, getSize().height - 10);
    }
    else if (token == 'O') {
      g.drawOval(10, 10, getSize().width - 20, getSize().height - 20);
    }
  }

  // Handle mouse click on a cell
  public void mouseClicked(MouseEvent e) {
    if (token == ' ') { // If cell is not occupied
      if (parent.whoseTurn == 'X') { // If it is the X player's turn
        setToken('X');  // Set token in the cell
        parent.whoseTurn = 'O';  // Change the turn
        parent.jlblStatus.setText("O's turn");  // Display status
        if (parent.won('X'))
          parent.jlblStatus.setText("X won! The game is over");
        else if (parent.isFull())
          parent.jlblStatus.setText("Draw! The game is over");
      }
      else if (parent.whoseTurn == 'O') { // If it is the O player's turn
        setToken('O'); // Set token in the cell
        parent.whoseTurn = 'X';  // Change the turn
        parent.jlblStatus.setText("X's turn"); // Display status
        if (parent.won('O'))
          parent.jlblStatus.setText("O won! The game is over");
        else if (parent.isFull())
          parent.jlblStatus.setText("Draw! The game is over");
      }
    }
  }

  public void mousePressed(MouseEvent e) {
    //TODO: implement this java.awt.event.MouseListener method;
  }

  public void mouseReleased(MouseEvent e) {
    //TODO: implement this java.awt.event.MouseListener method;
  }

  public void mouseEntered(MouseEvent e) {
    //TODO: implement this java.awt.event.MouseListener method;
  }

  public void mouseExited(MouseEvent e) {
    //TODO: implement this java.awt.event.MouseListener method;
  }
}
