import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.*;

import java.io.*;

public class Exercise29_14 extends JFrame {
  private JProgressBar jpb = new JProgressBar();
  private JButton jbtCopy = new JButton("Copy");
  private JTextField jtfFrom = new JTextField();
  private JTextField jtfTo = new JTextField();

  public Exercise29_14() {
    // Start progress meter bar
    jpb.setValue(0);
    jpb.setMaximum(100);
    
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout(new BorderLayout());
    jPanel2.setBorder(new TitledBorder("From"));
    jPanel2.add(jtfFrom, BorderLayout.CENTER);

    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout(new BorderLayout());
    jPanel3.setBorder(new TitledBorder("To"));
    jPanel3.add(jtfTo, BorderLayout.CENTER);

    JPanel jPanel1 = new JPanel();
    jPanel1.setLayout(new GridLayout(2, 1));
    jPanel1.add(jPanel2);
    jPanel1.add(jPanel3);

    JPanel jPanel4 = new JPanel();
    jPanel4.add(jbtCopy);

    this.add(jpb, BorderLayout.NORTH);
    this.add(jPanel1, BorderLayout.CENTER);
    this.add(jPanel4, BorderLayout.SOUTH);

    jpb.setStringPainted(true); // Paint the percent in a string

    jbtCopy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {      
        Exercise27_14Task task = new Exercise27_14Task();
                
        task.addPropertyChangeListener(new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent e) {
            if ("progress".equals(e.getPropertyName())) {
              jpb.setValue((Integer)e.getNewValue());
            }
          }
        });
        
        task.execute(); // Execute SwingWorker
      }
    });
  }

  public static void main(String[] args) {
    Exercise29_14 frame = new Exercise29_14();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise29_14");
    frame.setSize(400, 180);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  // Copy file and update progress bar in a separate thread
  class Exercise27_14Task extends SwingWorker<Integer, Object> {
    private int currentValue;

    /** Code run on a background thread */
    protected Integer doInBackground() {
      copy();
      return 0;
    }
    
    public void copy() {
      BufferedInputStream in = null;
      BufferedOutputStream out = null;
      try {
        // Create file input stream
        File inFile = new File(jtfFrom.getText().trim());
        in = new BufferedInputStream(new FileInputStream(inFile));

        // Create file output stream
        File outFile = new File(jtfTo.getText());
        out = new BufferedOutputStream(new FileOutputStream(outFile));

        // Get total bytes in the file
        long totalBytes = in.available();

        int r;
        long bytesRead = 0;
        // You may increase buffer size to improve IO speed
        byte[] b = new byte[10];
        while ((r = in.read(b, 0, b.length)) != -1) {
          out.write(b, 0, r);
          bytesRead += r;
          currentValue = (int)(bytesRead * 100 / totalBytes);

          // Update the progress bar
          setProgress(currentValue);
        }
      }
      catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
      finally {
        try {
          if (in != null) in.close();
          if (out != null) out.close();
        }
        catch (Exception ex) {}
      }
    }
  }
}
