// Exercise30_4Server.java: Web visit count server
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_4Server extends JFrame implements ActionListener {
  protected JTextArea jta = new JTextArea();
  protected JButton jbtStop = new JButton("Stop");

  protected RandomAccessFile raf;
  protected String countFile = "webcount.dat"; // File to store count
  protected static int count;

  private ServerSocket serverSocket;

  public Exercise30_4Server() {
    // Create a scroll pane to hold text area
    JScrollPane jsp = new JScrollPane(jta);

    // Add panel and scroll panel to the frame
    add(jsp, BorderLayout.CENTER);
    add(jbtStop, BorderLayout.SOUTH);

    // Register listener
    jbtStop.addActionListener(this);

    setTitle("Exercise30_4Server: Web Visit Count Server");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true);

    startup();
  }

  private void startup() {
    try {
      serverSocket = new ServerSocket(8000);
      jta.append("Server started " + "\n\n");

      // Determine whether webcount.dat already exists
      if (new File(countFile).exists()) {
        raf = new RandomAccessFile(countFile, "rw");
        count = raf.readInt(); // Obtain count
      }
      else {
        raf = new RandomAccessFile(countFile, "rw");
        count = 0;
      }

      int i = 0;
      while (true) {
        Socket connectToClient = serverSocket.accept();
        jta.append("Starting thread " + i + "\n");
          jta.append("Client IP " +
          connectToClient.getInetAddress() + "\n");
        WebVisitHandler thread =
          new WebVisitHandler(connectToClient);
        thread.start();
        i++;
      }
    }
    catch(IOException ex) {
      jta.append(ex + "\n");
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jbtStop) {
      try {
        if (raf != null) {
          // Store the count
          raf.seek(0);
          raf.writeInt(count);
          raf.close();
        }
      }
      catch (IOException ex) {
        jta.append(ex + "\n");
      }
    }

    System.exit(0);
  }

  public static void main(String args[]) {
    Exercise30_4Server frame = new Exercise30_4Server();
  }

  public static synchronized int increaseCount() {
    return ++count;
  }

  class WebVisitHandler extends Thread {
    private Socket connectToClient;

    public WebVisitHandler(Socket c) {
      connectToClient = c;
    }

    public void run() {
      try {
        PrintWriter osToClient =
          new PrintWriter(connectToClient.getOutputStream(), true);

        int newCount = increaseCount();

        // Send count to the client
        osToClient.println(newCount);
      }
      catch (IOException ex) {
        jta.append(ex + "\n");
      }
    }
  }
}
