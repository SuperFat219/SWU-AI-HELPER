// Exercise30_2ClientUsingBufferedReader.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise30_2ClientUsingBufferedReader extends JFrame implements ActionListener {
  private JTextField jtf;
  private JTextArea jta = new JTextArea();
  private JButton jbtStart, jbtStop;
  PrintWriter osToServer;
  BufferedReader isFromServer;

  public static void main(String[] args) {
    new Exercise30_2ClientUsingBufferedReader();
  }

  public Exercise30_2ClientUsingBufferedReader() {
    Panel p1 = new Panel();
    p1.add(new Label("Enter radius"));
    p1.add(jtf = new JTextField(10));

    // It is necessary to show the frame here!
    setTitle("Exercise30_2ClientUsingBufferedReader");
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
      isFromServer = new BufferedReader(
        new InputStreamReader(connectToServer.getInputStream()));

      // Create a buffered output stream to send dajta to the server
      osToServer =
        new PrintWriter(connectToServer.getOutputStream(), true);
    }
    catch (IOException ex) {
      jta.append(ex.toString()+'\n');
    }
  }

  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
    if (e.getSource() instanceof JTextField) {
      try {
        // Read the radius from the keyboard
        double radius = new Double(jtf.getText().trim()).doubleValue();

        // Send radius to the server
        osToServer.println(radius);

        // Get area from the server
        StringTokenizer st = new StringTokenizer(
          isFromServer.readLine());

        // Convert string to double
        double area = new Double(
        st.nextToken ()).doubleValue ();

        // Print area on
        jta.append("Area received from the server is "
          +area + '\n');
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
