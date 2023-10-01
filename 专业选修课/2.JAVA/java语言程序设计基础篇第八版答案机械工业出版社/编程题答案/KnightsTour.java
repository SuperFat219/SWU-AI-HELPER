import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class KnightsTour extends JApplet {

  private Square[][] tiles = new Square[8][8];
  private JLabel instructions = new JLabel("Choose a starting tile" +
        " and click the START button to find solution");
  private JButton startButton = new JButton("Start");
  Square[] sq = new Square[64];
  private int startTile = 1;
  int[] path = null;
  
  public void init() {
    // Create and fill the chessboard
    JPanel chessboard = new JPanel(new GridLayout(8,8,1,1));
    for (int i=0; i < 8; i++) {
      for (int j=0; j < 8; j++) {
        // Assigns id (1-64) to each tile
        chessboard.add(tiles[i][j] = new Square(((i*8)+(j+1)), sq));
        tiles[i][j].setBorder(new LineBorder(Color.BLACK, 1));
      }
    }
    add(chessboard, BorderLayout.CENTER);
    add(instructions, BorderLayout.NORTH);
    add(startButton, BorderLayout.SOUTH);
    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        path = findPath(startTile);
        int count = 0;
        System.out.println("Path:");
        for (int z = 0; z < path.length; z++) {
          System.out.print(path[z] + " ");
          count++;
        }
        for (int x = 0; x < 64; x++) {
          if(path[x] == 0) continue;
          tiles[(path[x]-1)/8][(path[x]-1)%8].setText(""+(x+1)+"");
        }
        repaint();
      }
    });
    this.setSize(400, 500);
  }
  int[] findPath(int startSquare) {
    int i  = 0;
    int j = 0;
    int startI = (startSquare-1)/8;
    int startJ = (startSquare-1)%8;
    int path[];
    path = new int[64];
    for(i = 0; i < 8; i++) {
      for (j = 0; j < 8; j++) {
        tiles[i][j].visited = false;
      }
    }
    tiles[startI][startJ].visited = true;
    int nxt[];
    int n = -1;
    int moves = 0;
    int currentSquare = tiles[startI][startJ].getID();
    path[moves] = currentSquare;
    for( i = 0; i < 64; i++) {
      n = tiles[(currentSquare-1)/8][(currentSquare-1)%8].goodExit();
      if( n < 0 ) break;
      tiles[(n-1)/8][(n-1)%8].visited = true;
      moves++;
      //System.out.println("Next move is: " + n);
      currentSquare = n;
      // remember path path
      path[moves] = currentSquare;
    }
    // find out unvisited squares
    int nUnvisited = 0;
    int unvisited = -1;
    for(i = 0; i < 8; i++){
      for (j = 0; j < 8; j++) {
        if(!tiles[i][j].visited){
          unvisited = tiles[i][j].getID();
          nUnvisited++;
        }
      }
    }
    if(nUnvisited == 1) {
          path[63]= unvisited;
          System.out.println("SUCCESS!");
    } else {
      System.out.println("Failed to find solution");
    }
    return path;   
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Knight's Tour Game");
    KnightsTour game = new KnightsTour();
    frame.add(game, BorderLayout.CENTER);
    game.init();
    game.start();
    frame.setSize(400, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  public class Square extends JPanel implements MouseListener {
    int id;
    JLabel label = new JLabel("X");
    boolean visited = false;
    Square[] sq = null;
    
    public Square(int id, Square[] sq) {
      this.id = id;
      this.sq = sq;
      setText("X");
      add(label, BorderLayout.CENTER);
      label.setVisible(true);
      addMouseListener(this);
      this.label.setFont(new Font("Serif", Font.BOLD, 32));
    }
    public void paintComponent(Graphics g) {
      super.paintComponents(g);
    }
    public int getID() {
      return id;
    }
    int getRow(){ 
      return (((id-1)/8)+1);
    }
    int getCol(){ 
      return (((id-1)%8)+1);
    }
    int getId(int r, int c) { 
      return (r-1)*8+c;
    }
    // Returns legal moves FROM current square
    int[] legalMoves(){
       int temp[] = new int[8];
       int index = 0;
       int r = this.getRow();
       int c = this.getCol();
       for(int i=0; i < 8;i++){
        r = this.getRow();
        c = this.getCol();
        switch(i){
         case 0:  r -= 2; c += 1; break;
         case 1:  r -= 1; c += 2; break;
         case 2:  r += 1; c += 2; break;
         case 3:   r += 2; c += 1 ; break;
         case 4:  r += 2 ; c -= 1; break;
         case 5:  r += 1; c -= 2; break;
         case 6:  r -= 1; c -= 2; break;
         case 7:  r -= 2; c -= 1; break;
        }        
        // If ith move is not legal, skip to next iteration
        if(( r < 1) || ( r > 8) || ( c < 1) || (c > 8)) continue;
        // Store legal moves
        temp[index] = getId(r,c);
        index++;
       }
       
       int result[] = new int[index];
       System.arraycopy(temp,0,result,0,index);
       
       return result;  
    }
    // Count escapes for each potential next move
    int escapes(int omit){
      int nxt[];
      nxt = legalMoves();
      int e=0;
      for(int i = 0; i < nxt.length; i++){
        if(tiles[(nxt[i]-1)/8][(nxt[i]-1)%8].visited || (nxt[i]== omit)) continue;
        e++;
      }
      return e; 
    }
    int goodExit(){
      int nxt[];
      nxt = legalMoves();
      int k = 8;
      int idx = -1;
      int e = 0;
      //System.out.println("number of escapes left: " + nxt.length);
      for(int i = 0; i < nxt.length; i++) {
        // If already visited, not a good exit
        if(tiles[(nxt[i]-1)/8][(nxt[i]-1)%8].visited) continue;
        // If not visited, count escapes
        e = tiles[(nxt[i]-1)/8][(nxt[i]-1)%8].escapes(nxt[i]);
        
        //  Compare to other potential next moves.
        if((e > 0) && ( e < k)) { k = e; idx = i;}
      }
      if(idx == -1) return idx;
      else
      return nxt[idx];
    } 
    public void mouseClicked(MouseEvent e) {
      startTile = this.getID();
    }
    public void setText(String turnNumber) {
      label.setText(turnNumber);
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
  }

}
