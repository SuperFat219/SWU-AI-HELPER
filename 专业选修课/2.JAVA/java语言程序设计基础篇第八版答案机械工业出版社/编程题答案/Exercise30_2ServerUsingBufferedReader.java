// Exercise30_2ServerUsingBufferedReader.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_2ServerUsingBufferedReader extends JFrame {
  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    new Exercise30_2ServerUsingBufferedReader();
  }

  public Exercise30_2ServerUsingBufferedReader() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    // It is necessary to show the frame here!
    setTitle("Exercise30_2ServerUsingBufferedReader");

    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true);

    try {
      // Create a server socket
      ServerSocket s = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      // Label a thread
      int i = 0;

      while (true) {
        // Listen for a new connection request
        Socket connectToClient = s.accept();

        // Print the new connect number on the console
        jta.append("Starting thread " + i + " at " + new Date() + '\n');

        // Create a new thread for the connection
        ThreadHandler  t = new ThreadHandler(connectToClient, i);

        // Start the new thread
        t.start();

        // Increment i to label the next connection
        i++;
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }

  // Inner class
  // Define the thread class for handling new connection
  class ThreadHandler extends Thread {
    private Socket connectToClient; // A connected socket
    private int counter; // Label for the connection
  
    public ThreadHandler(Socket c, int i) {
      connectToClient = c;
      counter = i;
    }
  
    public void run() {
      try {
        // Create data input and print streams
        BufferedReader isFromClient = new BufferedReader(
          new InputStreamReader(connectToClient.getInputStream()));
        PrintWriter osToClient =
          new PrintWriter(connectToClient.getOutputStream(), true);
  
        // Continuously serve the client
        while (true) {
          // Receive data from the client in string
          StringTokenizer st = new StringTokenizer
            (isFromClient.readLine());
  
          // Get radius
          double radius = new Double (st.nextToken ()).doubleValue ();
          jta.append("radius received from client: "+radius+'\n');
  
          // Compute area
          double area = radius*radius*Math.PI;
  
          // Send area back to the client
          osToClient.println(area);
          jta.append("Area found: "+area+'\n');
        }
      }
      catch(IOException e) {
        System.err.println(e);
      }
    }
  }
}
