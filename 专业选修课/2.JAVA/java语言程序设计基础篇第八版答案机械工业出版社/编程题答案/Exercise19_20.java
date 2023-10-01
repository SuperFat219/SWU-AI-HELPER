import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Exercise19_20 extends JFrame {
  private JTextField jtfFilename = new JTextField();
  private JButton jbtSave = new JButton("Save the bits to the file");
  private JTextArea jtaBits = new JTextArea();
  
  public Exercise19_20() {
    JPanel panel1 = new JPanel(new BorderLayout());
    panel1.add(new JLabel("Enter a file"), BorderLayout.WEST);
    panel1.add(jtfFilename, BorderLayout.CENTER);

    add(panel1, BorderLayout.NORTH);
    jtaBits.setLineWrap(true);   
    add(new JScrollPane(jtaBits));
    add(jbtSave, BorderLayout.SOUTH);
    
    jtfFilename.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          FileInputStream in = new FileInputStream(jtfFilename.getText().trim());
          
          String s = "";
          int value;
          while ((value = in.read()) != -1) {
            s += getBits(value);
          }
          
          in.close();
          jtaBits.setText(s);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    
    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          BitOutputStream output = new BitOutputStream(new File(jtfFilename.getText().trim()));
          output.writeBit(jtaBits.getText().trim());
          output.close();    
        } 
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
  }
  
  public static String getBits(int value) {
    String result = "";
    
    int mask = 1;
    for (int i = 7; i >= 0; i--) {
      int temp = value >> i;
      int bit = temp & mask;
      result = result + bit;
    }
    return result;
  }
  
  public static class BitOutputStream {
    private FileOutputStream output;
    private int value;
    private int count = 0;
    private int mask = 1; // The bits are all zeros except the last one
    
    public BitOutputStream(File file) throws IOException {
      output = new FileOutputStream(file);
    }
    
    public void writeBit(char bit) throws IOException {
      count++;
      value = value << 1;
      
      if (bit == '1') 
        value = value | mask;
      
      if (count == 8) {
        output.write(value);
        count = 0;
      }
    }
    
    public void writeBit(String bitString) throws IOException {
      for (int i = 0; i < bitString.length(); i++)
        writeBit(bitString.charAt(i));
    }
    
    /** Write the last byte and close the stream. If the last byte is not full, right-shfit with zeros */
    public void close() throws IOException {     
      if (count > 0) {
        value = value << (8 - count);
        output.write(value);
      }
      
      output.close();
    }   
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new Exercise19_20();
    frame.setTitle("Exercise19_20");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
