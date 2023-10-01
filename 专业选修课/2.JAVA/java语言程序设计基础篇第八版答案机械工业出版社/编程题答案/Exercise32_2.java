import java.awt.*;
import javax.swing.*;

import java.util.*;

public class Exercise32_2 extends JApplet implements MemoryListener {
  boolean isStandalone = false;
  private MemoryWatch memoryWatch1 = new MemoryWatch();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JTextArea jTextArea1 = new JTextArea();

  /** Initialize the applet */
  public void init() {
    this.setSize(new Dimension(400,300));
    jTextArea1.setText("jTextArea1");
    memoryWatch1.addMemoryListener(new MemoryListener() {
      public void sufficientMemory(MemoryEvent e) {
      }

      public void insufficientMemory(MemoryEvent e) {
        memoryWatch1_insufficientMemory(e);
      }
    });

    memoryWatch1.addMemoryListener(new MemoryListener() {
      public void sufficientMemory(MemoryEvent e) {
        memoryWatch1_sufficientMemory(e);
      }

      public void insufficientMemory(MemoryEvent e) {
      }
    });
    add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextArea1, null);
  }

  //Main method
  public static void main(String[] args) {
    Exercise32_2 applet = new Exercise32_2();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setTitle("Exercise32_2");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public void sufficientMemory(MemoryEvent e) {
    jTextArea1.setText("Sufficient Memory");
    jTextArea1.append("highLimit " + memoryWatch1.getHighLimit()+"\n");
    jTextArea1.append("free memory " + e.freeMemory() + "\n");
    jTextArea1.append("total memory " + e.totalMemory() + "\n");
  }

  public void insufficientMemory(MemoryEvent e) {
    jTextArea1.setText("Insufficient Memory\n");
    jTextArea1.append("lowLimit " + memoryWatch1.getLowLimit()+"\n");
    jTextArea1.append("free memory " + e.freeMemory() + "\n");
    jTextArea1.append("total memory " + e.totalMemory() + "\n");
  }

  void memoryWatch1_insufficientMemory(MemoryEvent e) {
    insufficientMemory(e);
  }

  void memoryWatch1_sufficientMemory(MemoryEvent e) {
    sufficientMemory(e);
  }
}

interface MemoryListener extends java.util.EventListener {
  // Handler for sufficient memory
  public void sufficientMemory(MemoryEvent e);

  // Handler for insufficient memory
  public void insufficientMemory(MemoryEvent e);
}

class MemoryEvent extends java.util.EventObject {
  private Runtime runtime = Runtime.getRuntime();

  public MemoryEvent(Object o) {
    super(o);
  }

  public long freeMemory() {
    return runtime.freeMemory();
  }

  public long totalMemory() {
    return runtime.totalMemory();
  }
}

class MemoryWatch implements Runnable {
  private int highLimit = 700000;
  private int lowLimit = 200000;

  private Runtime runtime = Runtime.getRuntime();

  private Thread thread;
  private transient Vector memoryListeners;

  public MemoryWatch() {
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {  }

      System.out.println("Total Memory " + runtime.totalMemory());
      System.out.println("Free Memory " + runtime.freeMemory());

      if (runtime.freeMemory() > highLimit) {
        MemoryEvent e = new MemoryEvent(this);
        fireSufficientMemory(e);
      }

      if (runtime.freeMemory() < lowLimit) {
        MemoryEvent e = new MemoryEvent(this);
        fireInsufficientMemory(e);
      }
    }
  }

  public static void main(String[] args) {
    MemoryWatch memoryWatch1 = new MemoryWatch();
  }

  public void setHighLimit(int newHighLimit) {
    highLimit = newHighLimit;
  }

  public int getHighLimit() {
    return highLimit;
  }

  public void setLowLimit(int newLowLimit) {
    lowLimit = newLowLimit;
  }

  public int getLowLimit() {
    return lowLimit;
  }

  public synchronized void removeMemoryListener(MemoryListener l) {
    if(memoryListeners != null && memoryListeners.contains(l)) {
      Vector v = (Vector) memoryListeners.clone();
      v.removeElement(l);
      memoryListeners = v;
    }
  }

  public synchronized void addMemoryListener(MemoryListener l) {
    Vector v = memoryListeners == null ? new Vector(2) : (Vector) memoryListeners.clone();
    if(!v.contains(l)) {
      v.addElement(l);
      memoryListeners = v;
    }
  }

  protected void fireSufficientMemory(MemoryEvent e) {
    if(memoryListeners != null) {
      Vector listeners = memoryListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((MemoryListener) listeners.elementAt(i)).sufficientMemory(e);
      }
    }
  }

  protected void fireInsufficientMemory(MemoryEvent e) {
    if(memoryListeners != null) {
      Vector listeners = memoryListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((MemoryListener) listeners.elementAt(i)).insufficientMemory(e);
      }
    }
  }
}
