import javax.swing.*;
import java.awt.*;

/**
 * 15.18
 */
public class PaintClock extends JFrame {
    public PaintClock() {
        setLayout(new GridLayout(1, 2));
        StillClock stillClock = new StillClock(4, 20, 45);
        StillClock stillClock1 = new StillClock(22, 46, 15);
        add(stillClock);
        add(stillClock1);
    }

    public static void main(String[] args) {
        PaintClock frame = new PaintClock();
        frame.setTitle("clock");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
