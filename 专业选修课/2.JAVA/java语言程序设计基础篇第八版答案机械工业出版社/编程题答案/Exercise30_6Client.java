// Exerise28_6Client.java
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise30_6Client extends JFrame implements ActionListener {
  // Specify the size of five string fields in the record
  final static int NAME_SIZE = 32;
  final static int STREET_SIZE = 32;
  final static int CITY_SIZE = 20;
  final static int STATE_SIZE = 2;
  final static int ZIP_SIZE = 5;
  final static int RECORD_SIZE =
    (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);

  // Access address.dat using RandomAccessFile
  private RandomAccessFile raf;

  // Text fields
  private JTextField jtfName = new JTextField(NAME_SIZE);
  private JTextField jtfStreet = new JTextField(STREET_SIZE);
  private JTextField jtfCity = new JTextField(CITY_SIZE);
  private JTextField jtfState = new JTextField(ZIP_SIZE);
  private JTextField jtfZip = new JTextField(ZIP_SIZE);

  // Buttons
  private JButton jbtAdd = new JButton("Add");
  private JButton jbtFirst = new JButton("First");
  private JButton jbtNext = new JButton("Next");
  private JButton jbtPrevious = new JButton("Previous");
  private JButton jbtLast = new JButton("Last");

  public Exercise30_6Client() {
    // Open or create a random access file
    try {
      raf = new RandomAccessFile("address.dat", "rw");
    }
    catch(IOException ex) {
      System.out.print("Error: " + ex);
      System.exit(0);
    }

    // Panel p1 for holding labels Name, Street, and City
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(3, 1));
    p1.add(new JLabel("Name"));
    p1.add(new JLabel("Street"));
    p1.add(new JLabel("City"));

    // Panel jpState for holding state
    JPanel jpState = new JPanel();
    jpState.setLayout(new BorderLayout());
    jpState.add(new JLabel("State"), BorderLayout.WEST);
    jpState.add(jtfState, BorderLayout.CENTER);

    // Panel jpZip for holding zip
    JPanel jpZip = new JPanel();
    jpZip.setLayout(new BorderLayout());
    jpZip.add(new JLabel("Zip"), BorderLayout.WEST);
    jpZip.add(jtfZip, BorderLayout.CENTER);

    // Panel p2 for holding jpState and jpZip
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(jpState, BorderLayout.WEST);
    p2.add(jpZip, BorderLayout.CENTER);

    // Panel p3 for holding jtfCity and p2
    JPanel p3 = new JPanel();
    p3.setLayout(new BorderLayout());
    p3.add(jtfCity, BorderLayout.CENTER);
    p3.add(p2, BorderLayout.EAST);

    // Panel p4 for holding jtfName, jtfStreet, and p3
    JPanel p4 = new JPanel();
    p4.setLayout(new GridLayout(3, 1));
    p4.add(jtfName);
    p4.add(jtfStreet);
    p4.add(p3);

    // Place p1 and p4 into jpAddress
    JPanel jpAddress = new JPanel(new BorderLayout());
    jpAddress.add(p1, BorderLayout.WEST);
    jpAddress.add(p4, BorderLayout.CENTER);

    // Set the panel with line border
    jpAddress.setBorder(new BevelBorder(BevelBorder.RAISED));

    // Add buttons to a panel
    JPanel jpButton = new JPanel();
    jpButton.add(jbtAdd);
    jpButton.add(jbtFirst);
    jpButton.add(jbtNext);
    jpButton.add(jbtPrevious);
    jpButton.add(jbtLast);

    // Add jpAddress and jpButton to the frame
    add(jpAddress, BorderLayout.CENTER);
    add(jpButton, BorderLayout.SOUTH);

    jbtAdd.addActionListener(this);
    jbtFirst.addActionListener(this);
    jbtNext.addActionListener(this);
    jbtPrevious.addActionListener(this);
    jbtLast.addActionListener(this);

    // Display the first record if exists
    try {
      if (raf.length() > 0) readAddress(0);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /** Write a record at the end of the file */
  public void writeAddress() throws IOException {
    raf.seek(raf.length());
    FixedLengthStringIO.writeFixedLengthString(
      jtfName.getText(), NAME_SIZE, raf);
    FixedLengthStringIO.writeFixedLengthString(
      jtfStreet.getText(), STREET_SIZE, raf);
    FixedLengthStringIO.writeFixedLengthString(
      jtfCity.getText(), CITY_SIZE, raf);
    FixedLengthStringIO.writeFixedLengthString(
      jtfState.getText(), STATE_SIZE, raf);
    FixedLengthStringIO.writeFixedLengthString(
      jtfZip.getText(), ZIP_SIZE, raf);
  }

  /** Read a record at the specified position */
  public void readAddress(long position) throws IOException {
    raf.seek(position);
    String name = FixedLengthStringIO.readFixedLengthString(
      NAME_SIZE, raf);
    String street = FixedLengthStringIO.readFixedLengthString(
      STREET_SIZE, raf);
    String city = FixedLengthStringIO.readFixedLengthString(
      CITY_SIZE, raf);
    String state = FixedLengthStringIO.readFixedLengthString(
      STATE_SIZE, raf);
    String zip = FixedLengthStringIO.readFixedLengthString(
      ZIP_SIZE, raf);

    jtfName.setText(name);
    jtfStreet.setText(street);
    jtfCity.setText(city);
    jtfState.setText(state);
    jtfZip.setText(zip);
  }

  /** Handle button actions */
  public void actionPerformed(ActionEvent e) {
    try {
      if (e.getSource() == jbtAdd) {
        writeAddress();
      }
      else if (e.getSource() == jbtFirst) {
        if (raf.length() > 0) readAddress(0);
        }
      else if (e.getSource() == jbtNext) {
        long currentPosition = raf.getFilePointer();
        if (currentPosition < raf.length())
          readAddress(currentPosition);
      }
      else if (e.getSource() == jbtPrevious) {
        long currentPosition = raf.getFilePointer();
        if (currentPosition -  2 * RECORD_SIZE > 0)
          // Why 2 * 2 * RECORD_SIZE? See the follow-up remarks
          readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
        else
          readAddress(0);
      }
      else if (e.getSource() == jbtLast) {
          long lastPosition = raf.length();
          if (lastPosition > 0)
            // Why 2 * RECORD_SIZE? See the follow-up remarks
            readAddress(lastPosition - 2 * RECORD_SIZE);
      }
    }
    catch(IOException ex) {
      System.out.print("Error: " + ex);
    }
  }

  public static void main(String[] args) {
    Exercise30_6Client frame = new Exercise30_6Client();
    frame.pack();
    frame.setTitle("Exercise30_6Client");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
