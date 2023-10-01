import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Exercise32_6 {
  public static void main(String[] args) {
    DisplayMovingMessage applet = new DisplayMovingMessage();
    JFrame frame = new JFrame();
    frame.setTitle("DisplayMovingMessage");
    frame.add(applet, BorderLayout.CENTER);
    frame.setSize(200, 80);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class DisplayMovingMessage extends JApplet {
  public DisplayMovingMessage() {
    MovingMessage messagePanel = new MovingMessage();
    add(messagePanel);
    Tick tick = new Tick(100, 1000, 100, 10);
    tick.addTickListener(messagePanel);
  }

  class MovingMessage extends JPanel implements TickListener {
    private int xCoordinate = 20;
    private int yCoordinate = 20;
    private String message = "I am moving";

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (xCoordinate > getWidth())
        xCoordinate = -20;
      else
        xCoordinate += 5;
      g.drawString(message, xCoordinate, yCoordinate);
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void handleTick(TickEvent e) {
      setMessage("tickCount: " + e.getTickCount() +
                 " tickInterval: " + e.getTickInterval());
      repaint();
    }
  }
}

class Tick implements java.io.Serializable, ActionListener {
  /** Tick properties */
  private int tickCount = 0;
  private int tickInterval = 100;
  private int maxInterval = 5000;
  private int minInterval = 1;
  private int step = 0;

  /** Tick event */
  private TickEvent tickEvent = new TickEvent(this);

  /** Store Tick listeners in a list */
  private ArrayList tickListenerList;

  // Control tick using a timer
  private Timer timer = new Timer(tickInterval, this);

  /** Construct a tick with the default properties */
  public Tick() {
    this(100, 5000, 1, 0);
  }

  /** Construct a tick with the specified properties */
  public Tick(int tickInterval, int maxInterval,
      int minInterval, int step) {
    this.tickInterval = tickInterval;
    this.maxInterval = maxInterval;
    this.minInterval = minInterval;
    this.step = step;

    // Start the thread
    timer.start();
  }

  /** Return tickCount */
  public long getTickCount() {
    return tickInterval;
  }

  /** Set a tickCount */
  public void setTickCount(int tickCount) {
    this.tickCount = tickCount;
  }

  /** Return tickInterval */
  public long getTickInterval() {
    return tickInterval;
  }

  /** Set tickInterval */
  public void setTickInterval(int tickInterval) {
    this.tickInterval = tickInterval;
    timer.setDelay(tickInterval);
  }

  /** Return maxInterval */
  public long getMaxInterval() {
    return maxInterval;
  }

  /** Set maxInterval */
  public void setMaxInterval(int maxInterval) {
    this.maxInterval = maxInterval;
  }

  /** Return minInterval */
  public long getMinInterval() {
    return minInterval;
  }

  /** Set minInterval */
  public void setMinInterval(int minInterval) {
    this.minInterval = minInterval;
  }

  /** Return step */
  public long getStep() {
    return step;
  }

  /** Set step */
  public void setStep(int step) {
    this.step = step;
  }

  /** Handle timer */
  public void actionPerformed(ActionEvent e) {
    if (tickInterval > maxInterval ||
        tickInterval < minInterval) {
      return;
    }

    // Adjust Tick count and interval
    tickEvent.setTickCount(tickCount++);
    tickInterval += step;
    tickEvent.setTickInterval(tickInterval);

    // Process event
    processEvent(tickEvent);
  }

  /** Suspend the tick */
  public synchronized void suspend() {
    timer.stop();
  }

  /** Resume the tick */
  public synchronized void resume() {
    timer.start();
  }

  /** Register a tick event listener */
  public synchronized void addTickListener(TickListener l) {
    if (tickListenerList == null)
      tickListenerList = new ArrayList(2);

    if (!tickListenerList.contains(l))
      tickListenerList.add(l);
  }

  /** Remove a tick event listener */
  public synchronized void removeTickListener(TickListener l) {
    if (tickListenerList != null && tickListenerList.contains(l)) {
      tickListenerList.remove(l);
    }
  }

  /** Fire TickEvent */
  private void processEvent(TickEvent e) {
    ArrayList list;

    synchronized (this) {
      list = (ArrayList)tickListenerList.clone();
    }

    for (int i = 0; i < list.size(); i++) {
      TickListener listener = (TickListener)list.get(i);
      listener.handleTick(e);
    }
  }
}

class TickEvent extends java.util.EventObject {
  /** tickCount counts the number of ticks for the event */
  private long tickCount;

  /** tickInterval is the interval between ticks in milliseconds */
  private long tickInterval;

  /** Construct a TickEvent */
  public TickEvent(Object source) {
    super(source);
  }

  /** Return tickCount */
  public long getTickCount() {
    return tickCount;
  }

  /** Set a new tickCount */
  public void setTickCount(long tickCount) {
    this.tickCount = tickCount;
  }

  /** Return tickInterval */
  public long getTickInterval() {
    return tickInterval;
  }

  /** Set a new tickInterval */
  public void setTickInterval(long milliseconds) {
    tickInterval = milliseconds;
  }
}

interface TickListener extends java.util.EventListener {
  /** Handle a TickEvent, to be implemented by a listener */
  public void handleTick(TickEvent e);
}

