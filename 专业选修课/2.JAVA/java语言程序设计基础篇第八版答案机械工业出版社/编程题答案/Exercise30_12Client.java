// Exercise30_12Client.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise30_12Client extends JFrame {
  // Text area for entering server text
  private JTextArea jtaServer = new JTextArea();

  // Text area for displaying client text
  private JTextArea jtaClient = new JTextArea();

  private PrintWriter output;

  public static void main(String[] args) {
    new Exercise30_12Client();
  }

  public Exercise30_12Client() {
    // Place text area on the frame
    setLayout(new GridLayout(2, 1));
    JScrollPane jScrollPane1 = new JScrollPane(jtaServer);
    JScrollPane jScrollPane2 = new JScrollPane(jtaClient);
    jScrollPane1.setBorder(new TitledBorder("Server"));
    jScrollPane2.setBorder(new TitledBorder("Client"));
    add(jScrollPane2, BorderLayout.CENTER);
    add(jScrollPane1, BorderLayout.CENTER);

    jtaServer.setWrapStyleWord(true);
    jtaServer.setLineWrap(true);
    jtaClient.setWrapStyleWord(true);
    jtaClient.setLineWrap(true);
//    jtaClient.setEditable(false);

    setTitle("Exercise30_12Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      Socket connectToServer = new Socket("localhost", 8000);

      output = new
        PrintWriter(connectToServer.getOutputStream());
      new SendThread(connectToServer).start();
      new ReceiveThread(connectToServer).start();
    }
    catch(IOException ex) {
      System.err.println(ex);
    }

    jtaServer.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
          output.print(jtaServer.getText());
        }
      }
    });
  }

  class SendThread extends Thread {
    SendThread(Socket socket) {
      // Keep sending to the client

    }
  }

  class ReceiveThread extends Thread {
    ReceiveThread(Socket socket) {
      // Keep receiving from the client

    }
  }
}
