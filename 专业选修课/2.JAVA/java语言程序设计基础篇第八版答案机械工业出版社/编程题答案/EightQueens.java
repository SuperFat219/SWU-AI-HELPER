import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Eight Queens places eight queens on a chessboard without having any two queens share
 * a row, column, or diagonal.  The initial queen placement is selected by the user.
 * 
 * To use, simply run, then select the square in which you wish to place the first queen.
 * @author James Blondin
 *
 */
public class EightQueens extends JApplet {

	// main panel containing chessboard grid
	private static EightQueensPanel eightQueensPanel = new EightQueensPanel();
	
	// representation of chessboard data
	static int[][] boardArray = new int[8][8];
	
	// current position
	static Position curPos;
	
	// total number of recursions attempted
	static long totalMoves;
	
	// the queen image, used for drawing
	static Image queenImage = new ImageIcon("image/queen.jpg").getImage();
	
	// the list of position in of queens
	static LinkedList<Position> posList = new LinkedList<Position>();
	
		
	public EightQueens()
	{
		add(eightQueensPanel);
	
	}
	
	/**
	 * Clear the board
	 */
	private static void clearBoard()
	{
		for ( int i = 0; i < boardArray.length; i++ )
		{
			for ( int j = 0; j < boardArray[i].length; j++)
			{
				boardArray[i][j] = 0;
			}
		}
		posList.clear();
	}
	
	/** 
	 * Starting point for recursion.  Sets the initial placement based on user choice,
	 * and calls recursive method.  Also handles completion of the algorithm.
	 */
	private static void solve()
	{
		System.out.println("Starting From: " + curPos.xPos + " " + curPos.yPos);
		totalMoves=0;
		int moveNum=1;
		boardArray[curPos.xPos][curPos.yPos] = moveNum;		
		
		// kick off recursion with current position and initial move number
		// This should always return true for normal 8x8 chessboard
		if ( tryMove(curPos, moveNum) )
		{
			// update the image, and print the board to console as well
			System.out.println("Solution found:");
			eightQueensPanel.repaint();
			printBoard();
		}
		else
		{
			System.out.println("No solution found.");
			printBoard();
		}
		
		System.out.println("Total Moves: " + totalMoves + "\n");
	}
	
	/**
	 * Main recursive method.  Performs following steps:
	 * 1) Adds new position to list of positions.
	 * 2) Marks off all rows, columns, and diagonals from position as invalid
	 *    for further use.
	 * 3) Loop through board to find next available spot for queen placement. 
	 *    Place queen and recurse.
	 * 4) If unsuccessful, remove attempted position from list of positions 
	 *    (backtrack)
	 *    
	 * @param pos
	 * @param moveNum
	 * @return
	 */	
	private static boolean tryMove(Position pos, int moveNum) {
		totalMoves++;
		
		posList.add(pos);
		
		// mark rows, columns, diagonals as used
		for ( int i = 0; i < boardArray.length; i++ )
		{
			// fill row
			if ( boardArray[i][pos.yPos] == 0 )
				boardArray[i][pos.yPos] = moveNum;
			for ( int j = 0; j < boardArray[i].length; j++ )
			{
				// fill column
				if ( boardArray[pos.xPos][j] == 0 )
					boardArray[pos.xPos][j] = moveNum;
				// fill both diagonals
				if ( j-i == pos.yPos-pos.xPos && boardArray[i][j] == 0 )
					boardArray[i][j] = moveNum;
				if ( j+i == pos.yPos+pos.xPos && boardArray[i][j] == 0 )
					boardArray[i][j] = moveNum;
			}
		}
		
		// loop through board
		for ( int p = 0; p < boardArray.length; p++ )
		{
			for ( int q = 0; q < boardArray[p].length; q++ )
			{
				// check if board unoccupied
				if ( boardArray[p][q] == 0 )
				{
					// mark unoccupied position
					boardArray[p][q] = moveNum + 1;
					// check if newly mark position completes the board
					if ( moveNum + 1 < 8 )
					{
						// since there's more moves to be made, recurse with
						// newly selected position
						if ( tryMove(new Position(p,q),moveNum + 1))
						{
							return true;
						}
						
						// since newly attempted spot failed, remove any
						// evidence of attempt to prepare board for next
						// attempted spot
						for ( int s = 0; s < boardArray.length; s++)
						{
							for ( int t = 0; t < boardArray[s].length; t++ )
							{
								if ( boardArray[s][t] == moveNum+1 )
									boardArray[s][t] = 0;
							}
						}
					}
					else
					{
						// board complete.  Add new spot to list.
						posList.add(new Position(p,q));
						totalMoves++;
						return true;
					}
				}
			}
		}
		
		// failed, remove this attempt.
		posList.remove(pos);
		
		return false;
	}
	
	/**
	 * Print character representation of board to console
	 */
	private static void printBoard() {
		for (int i = 0; i < boardArray.length; i++ )
		{
			for (int j = 0; j < boardArray[i].length; j++ )
			{
				System.out.print(boardArray[j][i] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Eight Queens");
		EightQueens knightsTour = new EightQueens();
		
		frame.add(knightsTour,BorderLayout.CENTER);
		
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Panel containing main chessboard grid.  This class handles drawing.  
	 *
	 */
	static class EightQueensPanel extends JPanel
	{
		/**
		 * Default constructor.  Add click even listener to wait for user to click a spot on the grid,
		 * and kicks off algorithm from that spot.
		 */
		public EightQueensPanel() {
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)
				{
					clearBoard();
					curPos = new Position((int)(e.getX()*8/getWidth()),(int)(e.getY()*8/getHeight()));
					repaint();
					solve();
				}
			});
		}
		
		/**
		 * Overridden paintComponent method.  Draws the grid and all queens ( if any )
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			drawGrid(g,getWidth(),getHeight());
			drawAllQueens(g,boardArray);
		}
		
		/**
		 * Loops through posList, which contains position of all queens.  Draws each one.
		 * @param g
		 * @param boardArray
		 */
		private void drawAllQueens(Graphics g, int[][] boardArray) {
			Iterator<Position> posIter = posList.iterator();
			
			while ( posIter.hasNext() )
			{
				Position pos = posIter.next();
				
				
				g.drawImage(queenImage, pos.xPos*getWidth()/8+1, 
						pos.yPos*getHeight()/8+1, 
						getWidth()/8-1, getHeight()/8-1, this);
				
			}
		}

		/**
		 * Draws chessboard grid.
		 * 
		 * @param g
		 * @param width
		 * @param height
		 */
		private static void drawGrid(Graphics g, int width, int height)
		{
			// draw vertical lines
			g.drawLine(width/8, 0, width/8, height);
			g.drawLine((2*width)/8, 0, (2*width)/8, height);
			g.drawLine((3*width)/8, 0, (3*width)/8, height);
			g.drawLine((4*width)/8, 0, (4*width)/8, height);
			g.drawLine((5*width)/8, 0, (5*width)/8, height);
			g.drawLine((6*width)/8, 0, (6*width)/8, height);
			g.drawLine((7*width)/8, 0, (7*width)/8, height);
			
			// draw horizontal lines
			g.drawLine(0, height/8, width, height/8);
			g.drawLine(0, (2*height)/8, width, (2*height)/8);
			g.drawLine(0, (3*height)/8, width, (3*height)/8);
			g.drawLine(0, (4*height)/8, width, (4*height)/8);
			g.drawLine(0, (5*height)/8, width, (5*height)/8);
			g.drawLine(0, (6*height)/8, width, (6*height)/8);
			g.drawLine(0, (7*height)/8, width, (7*height)/8);
		}
	}
	
	/**
	 * Class that maintains x-coordinates and y-coordinates on chessboard.
	 *
	 */
	private static class Position
	{
		public int xPos = 0;
		public int yPos = 0;

		/**
		 * Default constructor.  x-coordinate and y-coordinate set to 0.
		 */
		public Position()
		{
		}
		
		/**
		 * Constructor specifying x and y coordinates.
		 * 
		 * @param x
		 * @param y
		 */
		public Position(int x, int y)
		{
			xPos = x;
			yPos = y;
		}

		/**
		 * Returns string with x and y position.
		 */
		public String toString()
		{
			return "XPos: " + (xPos+1) + "\tYPos: " + (yPos+1);
		}
	}

}
