import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Exercise29_6 extends JApplet {
  public Exercise29_6() {
    add(new BallControlUsingThread());
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JApplet applet = new Exercise29_6();
    frame.add(applet);
    frame.setTitle("Exercise29_6");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setSize(400, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class BallControlUsingThread extends JPanel implements ActionListener,
    AdjustmentListener {
    private Ball ball = new Ball();
    private JButton jbtSuspend = new JButton("Suspend");
    private JButton jbtResume = new JButton("Resume");
    private JButton jbtAdd = new JButton("+1");
    private JButton jbtSubtract = new JButton("-1");
    private JScrollBar jsbDelay = new JScrollBar();

    public BallControlUsingThread() {
      // Group buttons in a panel
      JPanel panel = new JPanel();
      panel.add(jbtSuspend);
      panel.add(jbtResume);
      panel.add(jbtAdd);
      panel.add(jbtSubtract);

      // Add ball and buttons to the panel
      ball.setBorder(new javax.swing.border.LineBorder(Color.red));
      jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
      ball.setDelay(jsbDelay.getMaximum());
      setLayout(new BorderLayout());
      add(jsbDelay, BorderLayout.NORTH);
      add(ball, BorderLayout.CENTER);
      add(panel, BorderLayout.SOUTH);

      // Register listeners
      jbtSuspend.addActionListener(this);
      jbtResume.addActionListener(this);
      jbtAdd.addActionListener(this);
      jbtSubtract.addActionListener(this);
      jsbDelay.addAdjustmentListener(this);
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == jbtSuspend) {
        ball.suspend();
      }
      else if (e.getSource() == jbtResume) {
        ball.resume();
      }
      else if (e.getSource() == jbtAdd) {
        ball.add();
      }
      else if (e.getSource() == jbtSubtract) {
        ball.subtract();
      }

    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
      ball.setDelay(jsbDelay.getMaximum() - e.getValue());
    }

    class Ball extends JPanel implements Runnable {
      private int speed = 10;

      // Create a timer with delay 1000 ms
      protected Thread timer = new Thread(this);

      private ArrayList list = new ArrayList();

      private int x = 0;
      private int y = 0; // Current ball position

      private int radius = 5; // Ball radius
      private int dx = 2; // Increment on ball's x-coordinate
      private int dy = 2; // Increment on ball's y-coordinate

      public Ball() {
        timer.start();
      }

      public void add() {
        list.add(new BallState());
      }

      public void subtract() {
        if (list.size() > 0) {
          list.remove(list.size() - 1); // Remove the last one
        }
      }

      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);

        for (int i = 0; i < list.size(); i++) {
          BallState ball = (BallState)list.get(i);

          // Check boundaries
          if (ball.x < radius) {
            ball.dx = Math.abs(ball.dx);
          }
          if (ball.x > getWidth() - radius) {
            ball.dx = -Math.abs(ball.dx);
          }
          if (ball.y < radius) {
            ball.dy = Math.abs(ball.dy);
          }
          if (ball.y > getHeight() - radius) {
            ball.dy = -Math.abs(ball.dy);
          }

          // Adjust ball position
          ball.x += ball.dx;
          ball.y += ball.dy;
          g.fillOval(ball.x - radius, ball.y - radius, radius * 2, radius * 2);
        }
      }

      boolean isSuspended = false;
      Lock lock = new ReentrantLock();
      Condition suspended = lock.newCondition();

      public void run() {
        try {
          while (true) {
            repaint();
            Thread.sleep(speed);
            while (isSuspended) {
              lock.lock();
              suspended.await();
              lock.unlock();
            }
          }
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }

      public void resume() {
        lock.lock();
        isSuspended = false;
        suspended.signalAll();
        lock.unlock();
      }

      public void suspend() {
        lock.lock();
        isSuspended = true;
        lock.unlock();
      }

      public void setDelay(int ms) {
        speed = ms;
      }

      class BallState {
        int x = 0;
        int y = 0; // Current ball position
        int dx = 2; // Increment on ball's x-coordinate
        int dy = 2; // Increment on ball's y-coordinate

        public BallState() {
        }
      }
    }
  }
}

