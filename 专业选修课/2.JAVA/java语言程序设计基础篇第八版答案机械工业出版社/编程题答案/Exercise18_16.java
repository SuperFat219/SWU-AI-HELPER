import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

//**************************************************
//                  CLASS
//**************************************************
//Class StockTicker;
//Function:Display stock ticker info from HTML params.
//
//
//Class TickerPanel;
//Function:Paints ticker on a panel and plays ticker sound
//    double buffering not needed
//
//Class TickerImagePanel:
//Function:Display StockTicker Logo Bitmaps
//
//**************************************************

public class Exercise18_16 extends JApplet implements MouseListener, MouseMotionListener {
  private TickerPanel tickerPanel;
  private TickerImagePanel tickerImagePanel;
  private String indexString=" ";
  private String valueString=" ";
  private String indexArray[] = new String[7];
  private String valueArray[] = new String[7];
  private int arrowArray[] = new int[7];
  private int i=0;
  private double changeDbl = 0;
  private String changeStr = null;
  private AudioClip tickerAudio;
  private Image redImage = null;
  private Image blackImage = null;

  //**********************************************
  //applet init routine
  //**********************************************
  public void init() {
    setSize(600, 200);
    setBackground(Color.white);
    getHTMLparams();

    //**********************************************
    //get initial ticker logo image
    //**********************************************
    redImage = getImage(getCodeBase(),"redTicker.gif");
    blackImage = getImage(getCodeBase(),"blackTicker.gif");

    //**********************************************
    //create ticker panel and ticker image panel and add to applet
    //**********************************************

    tickerPanel = new TickerPanel(indexArray,valueArray,arrowArray);
    tickerImagePanel = new TickerImagePanel(redImage, blackImage);

    setLayout(new BorderLayout());
    add(tickerPanel, BorderLayout.CENTER);
    add(tickerImagePanel, BorderLayout.NORTH);

    //**********************************************
    //check for mouse events to stop ticker or change tick direction
    //**********************************************
    tickerPanel.addMouseListener(this);
    tickerPanel.addMouseMotionListener(this);

    //**********************************************
    //prepare audio
    //**********************************************
    tickerAudio = getAudioClip(getCodeBase(), "Ticker.au");
    tickerAudio.loop();
  }

  //**********************************************
  //get Information about stocks from HTML
  //**********************************************
  public void getHTMLparams() {
    for (int j=0; j < 7 ; j++) {
      i++;

      //**********************************************
      //build index info string
      //**********************************************
      indexString = getParameter("IndexName" + i) + "  " + getParameter("IndexTime" + i) + " ";
      indexArray[j] = indexString;

      changeDbl = Double.valueOf(getParameter("IndexChng" + i)).doubleValue();

      //get change value, see if negative and log
      if (changeDbl < 0 )
        arrowArray[j] = 1;
      else
        arrowArray[j] = 0;

      //**********************************************
      //don't show negative value...we will draw arrow later
      //**********************************************
      changeStr = Double.toString((Math.abs(changeDbl)));

      //build value string
      valueString = getParameter("IndexPrev" + i) + "  " + changeStr + " ";
      valueArray[j] = valueString;
    }
  }

  //**********************************************
  //Mouse Listening Events
  //**********************************************
  public void mouseClicked(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  //**********************************************
  // restart ticker on mouse release
  //**********************************************
  public void mouseReleased(MouseEvent e) {
    tickerPanel.tickerResume();
    tickerAudio.loop();
  }

  //**********************************************
  //stop ticker on mouse press
  //**********************************************
  public void mousePressed(MouseEvent e) {
    tickerAudio.stop();
    tickerPanel.tickerSuspend();
  }

  public void mouseDragged(MouseEvent e) {
  }

  public void mouseMoved(MouseEvent e) {
  }

  //***********************************************
  //                Inner  CLASS
  //***********************************************
  //Image Canvas class...displays StockTicker Logo
  //***********************************************
  class TickerImagePanel extends JPanel
    implements Runnable {
    private Image blackImage = null;
    private Image redImage = null;
    private Image currentImage = null;
    private Thread ticker = null;
    private boolean suspended = false;

    //**********************************************
    //Constructor
    //**********************************************
    public TickerImagePanel(Image rImage, Image bImage) {
//      setSize(600,100);
      setBackground(Color.white);
      setVisible(true);
      redImage = rImage;
      blackImage = bImage;
      currentImage = redImage;

      // Start thread
      ticker = new Thread(this);
      ticker.start();
    }


    //**********************************************
    //repaints panel
    //**********************************************
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (currentImage != null)
        g.drawImage(currentImage, 0, 0, this);
    }

    //**********************************************
    //thread run routine
    //**********************************************
    public void run() {
      while (true) {
        try {
          ticker.sleep(2000);
          synchronized (this) {
            while (suspended)
              wait();
          }
        }
        catch (InterruptedException ex) {
        }

        if (currentImage == redImage)
          currentImage = blackImage;
        else
          currentImage = redImage;

        repaint();
      }
    }

    public synchronized void resume() {
      if (suspended) {
        suspended = false;
        notify();
      }
    }

    public synchronized void suspend() {
      suspended = true;
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 80);
    }
  }

  //***********************************************
  //                 Inner CLASS
  //***********************************************
  //Canvas class...displays StockTicker
  //***********************************************
  // Since we are using Swing, no manual handling of double buffering
  // is needed
  class TickerPanel extends JPanel implements Runnable {
    private boolean isActive = true;
    private String indexString;
    private String valueString;
    private String[] indexArray = new String[7];
    private String[] valueArray = new String[7];
    private int[] arrowArray = new int[7];

    private boolean suspended = false;
    private Thread ticker = null;
    private int xPos = 600;
    private int xPos2 = 600;
    private int xStart = 600;
    private int yInPos=25;
    private int yValPos=38;

    private boolean first = true;
    private Thread control = null;

    //**********************************************
    //Constructor
    //**********************************************
    public TickerPanel(String[] inArray,String[] vlArray,int[] arArray) {
      setSize(600,50);
      setBackground(Color.white);
      setForeground(Color.green);
      setVisible(true);

      //point to same location
      indexArray = inArray;
      valueArray = vlArray;
      arrowArray = arArray;

      //find start position
      xPos = getSize().width;
      xStart = getSize().width;
      xPos2 = getSize().width;
    }

    //**********************************************
    //draws stock ticker
    //**********************************************
    public void draw(Graphics g) {
      int wVal = 0;
      int w = 0;

      Font myFont = new Font("Helvetica", Font.BOLD, 14);
      g.setFont(myFont);
      FontMetrics fm=g.getFontMetrics(myFont);

      for (int j=0; j < 7 ; j++)
        if ( (indexArray[j] != null) && (valueArray[j] != null) ) {
          //**********************************************
          //Set color based on values read in html
          //**********************************************
          if (arrowArray[j] == 1)
            g.setColor(Color.red);
          else
            g.setColor(Color.green);

          //**********************************************
          //draw strings
          //**********************************************
          g.drawString(indexArray[j],xPos,yInPos);
          g.drawString(valueArray[j],xPos,yValPos);

          //**********************************************
          //calculate largest width string and position accordingly
          //**********************************************
          w=fm.stringWidth(indexArray[j]);
          wVal=fm.stringWidth(valueArray[j]);

          if (wVal > w)
            w = fm.stringWidth(valueArray[j]);

          //**********************************************
          //draw arrows
          //**********************************************
          if (arrowArray[j] == 1) {
            //put in arrow coordinates for up
            int arrowXPos[] = {xPos+wVal+3,xPos+wVal+6,xPos+wVal+4,xPos+wVal+4,xPos+wVal+2,xPos+wVal+2,xPos+wVal};
            int arrowYPos[] = {yValPos,yValPos-5,yValPos-5,yValPos-10,yValPos-10,yValPos-5,yValPos-5};

            //draw and fill
            g.drawPolygon(arrowXPos,arrowYPos,arrowXPos.length);
            g.fillPolygon(arrowXPos,arrowYPos,arrowXPos.length);
          }
          else {
            //put in arrow coordinates for down
            int arrowXPos[] = {xPos+wVal+3,xPos+wVal+6,xPos+wVal+4,xPos+wVal+4,xPos+wVal+2,xPos+wVal+2,xPos+wVal};
            int arrowYPos[] = {yValPos-10,yValPos-5,yValPos-5,yValPos,yValPos,yValPos-5,yValPos-5};

            //draw and fill
            g.drawPolygon(arrowXPos,arrowYPos,arrowXPos.length);
            g.fillPolygon(arrowXPos,arrowYPos,arrowXPos.length);
          }

          //**********************************************
          //advance to next index String
          //**********************************************
          xPos = w + 10 + xPos;
        }

        //restart scrolling at right when ticker
        //moves out of the view
        if (xPos > 0 )
          xPos = xStart;
        else {
          xPos = getSize().width;
          xStart = xPos;
        }

        //increment left
        xStart -= 2;
    }

    //**********************************************
    //repaints
    //**********************************************
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (first) {
        control = new Thread(this);
        control.start();
        first = false;
      }

      draw(g);
    }

    //**********************************************
    //thread run routine
    //**********************************************
    public void run() {
      while (true) {
        repaint();
        try {
          ticker.sleep(20);
          synchronized (this) {
            while (suspended)
              wait();
          }
        }
        catch (InterruptedException e) {}
      }
    }

    public synchronized void resume() {
      if (suspended) {
        suspended = false;
        notify();
      }
    }

    public synchronized void suspend() {
      suspended = true;
    }

    //**********************************************
    //thread resume
    //**********************************************
    public void tickerResume() {
      if (control != null)
        resume();
    }

    //**********************************************
    //thread suspend
    //**********************************************
    public void tickerSuspend() {
      if (control != null)
        suspend();
    }

    //**********************************************
    //thread start
    //**********************************************
    public void tickerStart() {
      resume();
    }
  }//Class TickerPanel
}
