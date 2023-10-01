import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class DatagramServer extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();

  // The byte array for sending and receiving datagram packets
  private byte[] buf = new byte[256];

  public static void main(String[] args) {
    new DatagramServer();
  }

  public DatagramServer() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("DatagramServer");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      DatagramSocket socket = new DatagramSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      // Create a packet for receiving data
      DatagramPacket receivePacket =
        new DatagramPacket(buf, buf.length);

      // Create a packet for sending data
      DatagramPacket sendPacket =
        new DatagramPacket(buf, buf.length);

      while (true) {
        // Initialize buffer for each iteration
        Arrays.fill(buf, (byte)0);

        // Receive radius from the client in a packet
        socket.receive(receivePacket);
        jta.append("The client host name is " +
          receivePacket.getAddress().getHostName() +
          " and port number is " + receivePacket.getPort() + '\n');
        jta.append("Radius received from client is " +
          new String(buf).trim() +  '\n');

        // Compute area
        double radius = Double.parseDouble(new String(buf).trim());
        double area = radius * radius * Math.PI;
        jta.append("Area is " + area + '\n');

        // Send area to the client in a packet
        sendPacket.setAddress(receivePacket.getAddress());
        sendPacket.setPort(receivePacket.getPort());
        sendPacket.setData(new Double(area).toString().getBytes());
        socket.send(sendPacket);
      }
    }
    catch(IOException ex) {
      ex.printStackTrace();
    }
  }
}
