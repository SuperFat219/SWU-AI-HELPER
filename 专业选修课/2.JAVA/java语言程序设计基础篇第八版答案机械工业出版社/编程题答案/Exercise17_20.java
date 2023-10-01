import java.awt.event.*;
import javax.swing.*;

public class Exercise17_20 extends JFrame {
  // Number of the slides specified in the HTML page
  private final static int NUMBER_OF_SLIDES = 10;
  private final static int SHOW_TIME_PER_SLIDE = 3000;

  private int current = 0;
  private JTextArea jtaSlide = new JTextArea();
  private String[] slides = new String[NUMBER_OF_SLIDES];

  public Exercise17_20() {
    add(jtaSlide);

		jtaSlide.setWrapStyleWord(true);
		jtaSlide.setLineWrap(true);

    // Load slide from files to strings
    loadTextToSlides();

    jtaSlide.setText(slides[0]);
    Timer timer = new Timer(SHOW_TIME_PER_SLIDE, new Listener());
    timer.start();
  }

  private void loadTextToSlides() {
    for (int i = 0; i < NUMBER_OF_SLIDES; i++) {
      slides[i] = readAFile("text/slide" + i + ".txt");
    }
  }

  private String readAFile(String file) {
		String text = "";
		try {
      java.util.Scanner input = new java.util.Scanner(
        new java.io.File(file));
      while (input.hasNext())
        text += input.nextLine() + "\n";
    }
		catch (Exception ex) {
			ex.printStackTrace();
		}

    return text;
  }

  class Listener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
	    jtaSlide.setText(slides[current]);
	    current = (current + 1) % NUMBER_OF_SLIDES;
	  }
  }

  // Main method
  public static void main(String[] args) {
    // Create a frame
    Exercise17_20 frame = new Exercise17_20();
    frame.setTitle("Exercise17_20: Slides Show");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the frame
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
