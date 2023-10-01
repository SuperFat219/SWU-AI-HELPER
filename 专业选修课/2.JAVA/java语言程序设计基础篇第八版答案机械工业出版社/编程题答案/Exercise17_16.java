import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class Exercise17_16 extends JFrame {
	private JSlider jscbRed, jscbGreen, jscbBlue;

	// Create a label
	private JLabel jlbl = new JLabel("Show Colors", JLabel.CENTER);

	// Declare color component values
	private int redValue, greenValue, blueValue;

	// Main method
	public static void main(String[] args) {
		Exercise17_16 frame = new Exercise17_16();
		frame.setSize(300, 200);
		frame.setTitle("Exercise17_16");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	public Exercise17_16() {
		// Panel p1 to hold labels
		JPanel p1 = new JPanel(new GridLayout(3, 1));
		p1.add(new JLabel("Red"));
		p1.add(new JLabel("Green"));
		p1.add(new JLabel("Blue"));

		// Panel p2 to hold labels
		JPanel p2 = new JPanel(new GridLayout(3, 1));
		p2.add(jscbRed = new JSlider());
		jscbRed.setOrientation(JSlider.HORIZONTAL);
		jscbRed.setMaximum(255);

		p2.add(jscbGreen = new JSlider());
		jscbGreen.setOrientation(JSlider.HORIZONTAL);
		jscbGreen.setMaximum(255);

		p2.add(jscbBlue = new JSlider());
		jscbBlue.setOrientation(JSlider.HORIZONTAL);
		jscbBlue.setMaximum(255);

		// Create a panel to hold p1 and p2
		JPanel p = new JPanel(new BorderLayout(10, 10));
		p.add(p1, BorderLayout.WEST);
		p.add(p2, BorderLayout.CENTER);

		add(jlbl, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);

		// Register listener for the scroll bars
		jscbRed.addChangeListener(new Listener());
		jscbGreen.addChangeListener(new Listener());
		jscbBlue.addChangeListener(new Listener());

		p.setBorder(new CompoundBorder(new TitledBorder("Choose colors"),
			new EmptyBorder(2, 2, 2, 2)));
	}

	class Listener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == jscbRed)
				redValue = jscbRed.getValue();
			else if (e.getSource() == jscbGreen)
				greenValue = jscbGreen.getValue();
			else
				blueValue = jscbBlue.getValue();
	
			Color color = new Color(redValue, greenValue, blueValue);
			jlbl.setForeground(color);
		}
	}
}
