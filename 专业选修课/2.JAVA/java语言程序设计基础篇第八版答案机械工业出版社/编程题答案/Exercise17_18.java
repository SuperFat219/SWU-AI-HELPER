import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise17_18 extends JFrame  {
  private JTextArea jta;
  private JButton jbtShowHistogram = new JButton("Show Histogram");
  private HistogramBarChart histogram = new HistogramBarChart();

  /** Create a new frame to hold the histogram panel */
  private JFrame histogramFrame = new JFrame();

  /** Default construct */
  public Exercise17_18() {
    // Store text area in a scroll pane
    JScrollPane scrollPane = new JScrollPane(jta = new JTextArea());
    scrollPane.setPreferredSize(new Dimension(300, 200));
    jta.setWrapStyleWord(true);
    jta.setLineWrap(true);

    // Place scroll pane and button in the frame
    add(scrollPane, BorderLayout.CENTER);
    add(jbtShowHistogram, BorderLayout.SOUTH);

    // Register listener
    jbtShowHistogram.addActionListener(new Listener());

    // Create a new frame to hold the histogram panel
    histogramFrame.add(histogram);
    histogramFrame.pack();
    histogramFrame.setTitle("Bar Chart");
  }

  class Listener implements ActionListener {
	  /** Handle the button action */
	  public void actionPerformed(ActionEvent e) {
	    // Count the letters in the text area
	    int[] count = countLetters();
	
	    double[] data = new double[26];
	    for (int i = 0; i < 26; i++)
	      data[i] = count[i];
	
	    // Set data names
	    String[] letters = new String[26];
	    for (int i = 0; i < 26; i++)
	      letters[i] = "" + (char)('A' + i);
	
	    histogram.setData(letters, data);
	
	    // Show the frame
	    histogramFrame.setLocation(200, 200);
	    histogramFrame.setSize(400, 100);
	    histogramFrame.setVisible(true);
	  }
	
	  /** Count the letters in the text area */
	  private int[] countLetters() {
	    // Count for 26 letters
	    int[] count = new int[26];
	
	    // Get contents from the text area
	    String text = jta.getText();
	
	    // Count occurrence of each letter (case insensitive)
	    for (int i=0; i<text.length(); i++) {
	      char character = text.charAt(i);
	
	      if ((character >= 'A') && (character <= 'Z')) {
	        count[(int)character - 65]++; // The ASCII for 'A' is 65
	      }
	      else if ((character >= 'a') && (character <= 'z')) {
	        count[(int)character - 97]++; // The ASCII for 'a' is 97
	      }
	    }
	
	    return count; // Return the count array
	  }
  }

  /** Main method */
  public static void main(String[] args) {
    Exercise17_18 frame = new Exercise17_18();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise17_18");
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
