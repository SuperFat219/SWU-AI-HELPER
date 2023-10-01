import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import java.util.*;

public class Exercise36_4 extends JApplet {
  //Construct the applet
  public Exercise36_4() {
    // Load the data from the file to the vectors
    Vector columnHeader = new Vector();
    Vector rows = new Vector();
    try {
      BufferedReader in = new BufferedReader(
        new FileReader("Exercise36_4Table.txt"));

      // Read the first line as header
      String line = in.readLine();
      StringTokenizer tokens = new StringTokenizer(line, ",\n\r");
      while (tokens.hasMoreTokens())
        columnHeader.addElement(tokens.nextToken());

      while ((line = in.readLine()) != null) {
        Vector row = new Vector();
        tokens = new StringTokenizer(line, ",\n\r");
        while (tokens.hasMoreTokens())
          row.addElement(tokens.nextToken());
        rows.addElement(row);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    JTable jTable1 = new JTable(rows, columnHeader);
    jTable1.setRowHeight(25);
    add(new JScrollPane(jTable1), BorderLayout.CENTER);
  }

  //Main method
  public static void main(String[] args) {
    Exercise36_4 applet = new Exercise36_4();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(3);
    frame.setTitle("Exercise36_4");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
