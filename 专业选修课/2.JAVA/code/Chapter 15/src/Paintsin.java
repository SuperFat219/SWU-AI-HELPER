import javax.swing.*;
import java.awt.*;

/**
 * 15.12
 */
public class Paintsin extends JFrame {
    public Paintsin() {
        setLayout(new BorderLayout());
        add(new Sin(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Paintsin frame = new Paintsin();
        frame.setSize(300, 300);
        frame.setTitle("sin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class Sin extends JPanel {
    double f(double x) {
        return Math.sin(x);
    }

    public void drawFunction() {
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //绘制坐标轴
        g.drawLine(10, 150, 290, 150);
        g.drawLine(150, 10, 150, 290);

        g.drawLine(155, 20, 150, 10);
        g.drawLine(145, 20, 150, 10);
        g.drawLine(285, 145, 290, 150);
        g.drawLine(285, 155, 290, 150);

        g.drawString("X", 290, 140);
        g.drawString("Y", 155,20);

        Polygon p = new Polygon();

        for (int x = -190; x <= 90; x++) {
            p.addPoint(x + 200, 150 + (int) (50 * f((x / 100.0) * 2 * Math.PI)));
        }

        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        g.drawString("-2\u03c0", 30, 160);
        g.drawString("2\u03c0", 235, 160);
        g.drawString("0", 152, 150);
    }
}