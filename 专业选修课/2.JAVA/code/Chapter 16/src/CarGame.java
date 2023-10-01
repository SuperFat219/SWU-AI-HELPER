/**
 * 16.15
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarGame extends JFrame {
    public CarGame() {
        add(new ShowCar());
    }

    public static void main(String[] args) {
        CarGame frame = new CarGame();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setTitle("Car");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
class ShowCar extends JPanel {
    private int x = 300;
    private int y = 300;

    public ShowCar() {
        Timer timer = new Timer(40, new TimerListener());
        timer.restart();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        y = getHeight();
        //车轮
        g.fillOval(x + 10, y - 10, 10, 10);
        g.fillOval(x + 30, y - 10, 10, 10);
        //车身
        g.setColor(Color.gray);
        g.fillRect(x, y - 20, 50, 10);
        //车顶
        g.setColor(Color.DARK_GRAY);
        Polygon p = new Polygon();
        //添加点
        p.addPoint(x + 10, y - 20);
        p.addPoint(x + 20, y - 30);
        p.addPoint(x + 30, y - 30);
        p.addPoint(x + 40, y - 20);
        g.fillPolygon(p);
        x += 5;
        if (x > getWidth())
            x = -50;
    }

    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
}