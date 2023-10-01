// Exercise30_3Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_3Client extends JFrame implements ActionListener {
  // Text field for receiving radius
  private JTextField jtfAnnualInterestRate = new JTextField();
  private JTextField jtfNumOfYears = new JTextField();
  private JTextField jtfLoanAmount = new JTextField();
  private JButton jbtSubmit= new JButton("Submit");

  // Text area to display contents
  private JTextArea jta = new JTextArea();

  // IO streams
  private DataOutputStream osToServer;
  private DataInputStream isFromServer;

  public static void main(String[] args) {
    new Exercise30_3Client();
  }

  public Exercise30_3Client() {
    // Panel p1 to hold the labels
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(3, 1));
    p1.add(new JLabel("Annual Interest Rate"));
    p1.add(new JLabel("Number Of Years"));
    p1.add(new JLabel("Loan Amount"));

    // Panel p2 to hold the text fields
    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(3, 1));
    p2.add(jtfAnnualInterestRate);
    p2.add(jtfNumOfYears);
    p2.add(jtfLoanAmount);

    // Panel p to hold p1, p2 and a button
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(p1, BorderLayout.WEST);
    p.add(p2, BorderLayout.CENTER);
    p.add(jbtSubmit, BorderLayout.EAST);

    jtfAnnualInterestRate.setHorizontalAlignment(JTextField.RIGHT);
    jtfNumOfYears.setHorizontalAlignment(JTextField.RIGHT);
    jtfLoanAmount.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jbtSubmit.addActionListener(this); // Register listener

    jta.setWrapStyleWord(true);
    jta.setLineWrap(true);

    setTitle("Exercise30_3Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a socket to connect to the server
      Socket connectToServer = new Socket("localhost", 8000);
      //Socket connectToServer = new Socket("130.254.204.36", 8000);
      //Socket connectToServer = new Socket(
      //  "drake.Armstrong.edu", 8000);

      // Create an input stream to receive data from the server
      isFromServer = new DataInputStream(
        connectToServer.getInputStream());

      // Create an output stream to send data to the server
      osToServer =
        new DataOutputStream(connectToServer.getOutputStream());
    }
    catch (IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  }

  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
    if (e.getSource() instanceof JButton) {
      try {
        // Get the annual interest rate from the text field
        double annualInterestRate =
          Double.parseDouble(jtfAnnualInterestRate.getText().trim());

        // Get the number of years from the text field
        int numOfYears =
          Integer.parseInt(jtfNumOfYears.getText());

        // Get the loan amount from the text field
        double loanAmount =
          Double.parseDouble(jtfLoanAmount.getText().trim());

        // Send the annual interest rate to the server
        osToServer.writeDouble(annualInterestRate);

        // Send the number of years to the server
        osToServer.writeInt(numOfYears);

        // Send the loan amount to the server
        osToServer.writeDouble(loanAmount);

        osToServer.flush();

        // Get monthly payment from the server
        double monthlyPayment = isFromServer.readDouble();

        // Get total payment from the server
        double totalPayment = isFromServer.readDouble();

          jta.append("Annual Interest Rate: " + annualInterestRate +
            " Number of Years: " + numOfYears + " Loan Amount: " +
            loanAmount + "\n");
          jta.append(" monthlyPayment: " + monthlyPayment + " " +
            " totalPayment: " + totalPayment + '\n');
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
