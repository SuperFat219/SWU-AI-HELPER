// Exercise30_2Client.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_2Client extends JFrame implements ActionListener {
  private JTextField jtf;
  private JTextArea jta = new JTextArea();
  PrintWriter osToServer;
  Scanner isFromServer;

  public static void main(String[] args) {
    new Exercise30_2Client();
  }

  public Exercise30_2Client() {
    Panel p1 = new Panel();
    p1.add(new Label("Enter radius"));
    p1.add(jtf = new JTextField(10));

    // It is necessary to show the frame here!
    setTitle("Exercise30_2Client");
    setSize(500, 300);

    setLayout(new BorderLayout());
    add(p1, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jtf.addActionListener(this);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true);

    try {
      // Create a socket to connect to the server
      Socket connectToServer = new Socket("localhost",8000);

      // Create a buffered input stream to receive dajta
      // from the server*/
      isFromServer = new Scanner(connectToServer.getInputStream());

      // Create a buffered output stream to send dajta to the server
      osToServer =
        new PrintWriter(connectToServer.getOutputStream(), true);
    }
    catch (IOException ex) {
      jta.append(ex.toString()+'\n');
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JTextField) {
      try {
        // Read the radius from the keyboard
        double radius = new Double(jtf.getText().trim()).doubleValue();

        // Send radius to the server
        osToServer.println(radius);

        // Get area from the server
        double area = isFromServer.nextDouble();

        // Print area on
        jta.append("Area received from the server is "
          +area + '\n');
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
  }
}
