import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;

class TheTour extends JApplet {
  	static private int[][] analysis = { 
  		{ 4, 5, 3, 6, 6, 6, 5, 6 },
  		{ 2, 5, 7, 7, 2, 3, 6, 5 }, 
  		{ 4, 5, 1, 1, 1, 2, 1, 5 },
  		{ 4, 8, 3, 4, 7, 7, 5, 8 }, 
  		{ 4, 5, 2, 2, 6, 6, 4, 8 },
  		{ 1, 5, 1, 8, 7, 7, 1, 7 }, 
  		{ 3, 3, 3, 6, 3, 3, 1, 6 },
  		{ 2, 8, 2, 2, 2, 7, 1, 8 } };

	  public TheTour() {

	    add(new InternalWorks());

	  }

	 

	  public static void main(String[] args) {

		TheTour applet = new TheTour();
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
	    frame.setTitle( "Knight's Tour");
	    frame.getContentPane().add(applet, BorderLayout.CENTER);
	    frame.setSize(400, 310);
	    frame. setVisible(true);
	  }


	  
	  

	  private class InternalWorks extends JPanel {

	  	private KnightTour tour = new KnightTour();

	  	private JButton jbtSuspend = new JButton("Suspend");
	  	private JButton jbtResume = new JButton("Resume");
	  	private JButton jbtReset = new JButton("Reset");
	  	private JTextField XBox  = new  JTextField();
	  	private JTextField YBox  = new  JTextField();
	  	protected Timer timer = new Timer(500, new TimerListener());

	  	private boolean active = false;

	  	private int knightX;

	  	private int knightY;

	  	private byte[][] path = new byte[8][8];

	  	private int keeper = 0;



	  	private class TimerListener implements ActionListener {
	  		// Updates the scroll bars
	  		public void actionPerformed(ActionEvent e) {
	  			updateByOne();
	  		}
	  	}

	  	private void updateByOne() {
	  		// Looks at the analysis array and determines where to go next, also
	  		// draws arrow
	  		if (active) {
	  			int oldX = knightX;
	  			int oldY = knightY;
	  			switch (analysis[knightY][knightX]) {
	  			case 1:
	  				knightX++;
	  				knightY -= 2;
	  				break;
	  			case 2:
	  				knightX += 2;
	  				knightY--;
	  				break;
	  			case 3:
	  				knightX += 2;
	  				knightY++;
	  				break;
	  			case 4:
	  				knightX++;
	  				knightY += 2;
	  				break;
	  			case 5:
	  				knightX--;
	  				knightY += 2;
	  				break;
	  			case 6:
	  				knightX -= 2;
	  				knightY++;
	  				break;
	  			case 7:
	  				knightX -= 2;
	  				knightY--;
	  				break;
	  			case 8:
	  				knightX--;
	  				knightY -= 2;
	  				break;
	  			}
	  			path[oldY][oldX] = 1;
	  			if ((knightX > 7) || (knightX < 0) || (knightY > 7)
	  					|| (knightY < 0)) {
	  				System.out
	  						.println("Error: " + oldX + "  " + oldY + "\n"
	  								+ knightX + " " + knightY + "\n"
	  								+ analysis[oldY][oldX]);
	  				System.exit(0);
	  			}
	  			path[knightY][knightX] = 2;

	  			redrawMatrix();
	  			keeper++;
	  			if (keeper == 64) {
	  				timer.setDelay(1000);
	  				path = new byte[8][8];
	  			}
	  			tour.updateMatrix(path);
	  		}
	  	}

	  	private void redrawMatrix() {
	  		System.out.println("\n\n\n\n\n\n\n\n");// clears the console
	  		for (int i = 0; i <= 7; i++) {
	  			for (int j = 0; j <= 7; j++)
	  				System.out.print("" + path[i][j]);
	  			System.out.println("");// moves to next line
	  		}
	  	}

	  	public InternalWorks() {
	  		// Group buttons in a panel
	  		JPanel panel = new JPanel();
	  		XBox.setText("1");
	  		YBox.setText("1");
	  		panel.add(jbtSuspend);
	  		panel.add(jbtResume);
	  		panel.add(jbtReset);
	  		panel.add(XBox, BorderLayout.WEST );
	  		panel.add(YBox,  BorderLayout.NORTH );
	  		timer.start();
	  		tour.setBorder(new javax.swing.border.LineBorder(Color.red));
	  		setLayout(new BorderLayout());
	  		add(tour, BorderLayout.CENTER);
	  		add(panel, BorderLayout.SOUTH);
	  		// Register listeners
	  		jbtSuspend.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				active = false;
	  			}
	  		});
	  		jbtResume.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				active = true;
	  			}
	  		});
	  		jbtReset.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  			    try {
	  			       knightX = Integer.parseInt(XBox.getText());
	  			       knightY = Integer.parseInt(YBox.getText());
	  			       path = new byte[8][8];
	  			       if (knightX >= 8 || knightX < 0)
	  			    	   knightX = 4;
	  			       if (knightY >= 8 || knightY < 0)
	  			    	   knightY = 4;
	  			       path[knightY][knightX] = 2;
	  			       keeper = 0;
	  			    } catch (NumberFormatException nfe) {
	  			    	System.out.println("NumberFormatException: " + nfe.getMessage());
	  			    }
	  				
	  			}
	  		});
	  	}

	  }
	  
	  




	  private class KnightTour extends JPanel {
	    private int delay = 10;
	   

	    // Create a timer with delay 10 ms
	    protected Timer timer = new Timer(delay, new TimerListener());

	    private byte[][] spaces = new byte[8][8];

	    
	    public void updateMatrix(byte[][] input){
	  	  spaces = input;
	    }
	    public KnightTour() {
	      timer.start();
	    }
	   


	    private class TimerListener implements ActionListener {
	      /** Handle the action event */
	      public void actionPerformed(ActionEvent e) {
	        repaint();
	        
	      }
	    }

	    protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      int width = getSize().width;
	      
	      int height = getSize().height;
	      g.clearRect(0,0,width,height);
	      for (int i=0; i < 8; i++){
	      	g.setColor(Color.gray);
	      	g.drawLine(0,i*(height / 8),width, i*(height / 8));
	      	g.drawLine(i*(width / 8),0,i*(width / 8),height);
	          for (int j=0; j<8; j++){
	          	if (spaces[i][j] ==1){
	          		g.setColor(Color.red);
	          		g.drawString("X", (1+2*j)*(width/16), (1+2*i)*(height/16));
	          	} else if (spaces[i][j] == 2){
	          		g.setColor(Color.black);
	          		g.drawString("k", (1+2*j)*(width/16), (1+2*i)*(height/16));
	          	}
	          }
	      }

	    }
	    

	    public void suspend() {
	      timer.stop(); // Suspend timer
	    }

	    public void resume() {
	      timer.start(); // Resume timer
	    }

	    public void setDelay(int delay) {
	      this.delay = delay;
	      timer.setDelay(delay);
	    }
	  }
	  
	  

}
