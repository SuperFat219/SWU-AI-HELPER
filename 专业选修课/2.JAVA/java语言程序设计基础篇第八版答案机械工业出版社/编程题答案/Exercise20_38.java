import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise20_38 extends JApplet {
	private JTextField jtfDepth = new JTextField(5);
	private TreePanel treePanel = new TreePanel();

	public Exercise20_38() {
		// Panel to hold label, text field, and a button
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter the depth: "));
		panel.add(jtfDepth);
		jtfDepth.setHorizontalAlignment(SwingConstants.RIGHT);

		// Add a H-tree panel to the applet
		add(treePanel);
		add(panel, BorderLayout.SOUTH);

		// Register a listener
		jtfDepth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				treePanel.setDepth(Integer.parseInt(jtfDepth.getText().trim()));
			}
		});
	}

	class TreePanel extends JPanel {
		private int depth = 0;

		public void setDepth(int depth) {
			this.depth = depth;
			repaint();
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			paintBranch(g, depth, getWidth() / 2, getHeight() - 10,
					getHeight() / 3, Math.PI / 2);
		}

		double angleFactor = Math.PI / 5;
		double sizeFactor = 0.58;

		public void paintBranch(Graphics g, int depth, int x1, int y1,
				double length, double angle) {
			if (depth >= 0) {
				int x2 = (int) (x1 + Math.cos(angle) * length);
				int y2 = (int) (y1 - Math.sin(angle) * length);

				// Draw the line
				g.drawLine(x1, y1, x2, y2);

				// Draw the left branch
				paintBranch(g, depth - 1, x2, y2, length * sizeFactor, angle
						+ angleFactor);
				// Draw the right branch
				paintBranch(g, depth - 1, x2, y2, length * sizeFactor, angle
						- angleFactor);
			}
		}
	}

	/** Main method */
	public static void main(String[] args) {
		JApplet applet = new Exercise20_38();
		applet.init();
		applet.start();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise20_38");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
