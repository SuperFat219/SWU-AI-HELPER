// Exercise30_3Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_3Server extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    new Exercise30_3Server();
  }

  public Exercise30_3Server() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jta.setWrapStyleWord(true);
    jta.setLineWrap(true);

    setTitle("Exercise30_3Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("Exercise30_3Server started at " + new Date() + '\n');

      // Number a client
      int clientNo = 1;

      while (true) {
        // Listen for a new connection request
        Socket connectToClient = serverSocket.accept();

        // Display the client number
        jta.append("Starting thread for client " + clientNo +
          " at " + new Date() + '\n');

        // Find the client's host name, and IP address
        InetAddress clientInetAddress =
          connectToClient.getInetAddress();
        jta.append("Client " + clientNo + "'s host name is "
          + clientInetAddress.getHostName() + "\n");
        jta.append("Client " + clientNo + "'s IP Address is "
          + clientInetAddress.getHostAddress() + "\n");

        // Create a new thread for the connection
        HandleAClient thread = new HandleAClient(connectToClient);

        // Start the new thread
        thread.start();

        // Increment clientNo
        clientNo++;
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }

  // Inner class
  // Define the thread class for handling new connection
  class HandleAClient extends Thread {
    private Socket connectToClient; // A connected socket

    /** Construct a thread */
    public HandleAClient(Socket socket) {
      connectToClient = socket;
    }

    /** Run a thread */
    public void run() {
      try {
        // Create data input and output streams
        DataInputStream isFromClient = new DataInputStream(
          connectToClient.getInputStream());
        DataOutputStream osToClient = new DataOutputStream(
          connectToClient.getOutputStream());

        // Continuously serve the client
        while (true) {
          // Receive annual interest rate from the client
          double annualInterestRate = isFromClient.readDouble();

          // Receive number of years f6hhurom the client
          int numOfYears = isFromClient.readInt();

          // Receive loan from the client
          double loanAmount = isFromClient.readDouble();

          // Compute monthly payment and total payment
          Loan mortgage = new Loan(
            annualInterestRate, numOfYears, loanAmount);
          double monthlyPayment = mortgage.getMonthlyPayment();
          double totalPayment = mortgage.getTotalPayment();

          // Send results back to the client
          osToClient.writeDouble(monthlyPayment);
          osToClient.writeDouble(totalPayment);

          jta.append("Annual Interest Rate: " + annualInterestRate +
            " Number of Years: " + numOfYears + " Loan Amount: " +
            loanAmount + "\n");
          jta.append(" monthlyPayment: " + monthlyPayment + " " +
            " totalPayment: " + totalPayment + '\n');
        }
      }
      catch(IOException e) {
        System.err.println(e);
      }
    }
  }
}
