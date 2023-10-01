import java.awt.*;
import javax.swing.*;

public class Exercise15_14 extends JFrame {
    public Exercise15_14() {
    setLayout(new GridLayout(1, 3, 20, 5));

    BarChart1 chart1 = new BarChart1();
    double[] data1 = {20, 10, 30, 40};
    String[] dataName1 = {"Project -- 20%", "Quizzes -- 10%",
		  "Midtems -- 30%", "Final -- 40%"};
    chart1.setData(dataName1, data1);
    add(chart1);
  }

  public static void main(String[] args) {
    Exercise15_14 frame = new Exercise15_14();
    frame.setTitle("Exercise15_14");
    frame.setSize(500, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class BarChart1 extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue,
		Color.cyan, Color.magenta, Color.orange, Color.pink,
		Color.darkGray};
	String[] dataName;
	double[] data;

	public BarChart1() {
		this.setLayout(borderLayout1);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Find the maximum value in the data
		double max = data[0];
		for (int i=1; i<data.length; i++)
			max = Math.max(max, data[i]);

		int barWidth = (int)((getWidth() - 10.0) / data.length - 10);
		int maxBarHeight = getHeight() - 30;

		g.drawLine(5, getHeight() - 10, getWidth() - 5, getHeight() - 10);

		int x = 15;
		for (int i = 0; i < data.length; i++) {
			g.setColor(colors[i % colors.length]);
			int newHeight = (int)(maxBarHeight * data[i] / max);
			int y = getHeight() - 10 - newHeight;
			g.fillRect(x, y, barWidth, newHeight);
			g.setColor(Color.black);
			g.drawString(dataName[i], x, y - 7);
			x += barWidth + 10;
		}
	}

  public void setData(String[] dataName, double[] data) {
		this.dataName = dataName;
		this.data = data;
  }
}
