import javax.swing.*;
import java.awt.*;

/**
 * 15.4
 */
public class PaintTable extends JFrame {
    public PaintTable() throws HeadlessException {
        add(new Table());
    }

    public static void main(String[] args) {
        PaintTable frame = new PaintTable();
        frame.setSize(400, 400);
        frame.setTitle("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
class Table extends JPanel{
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 40;
        StringBuilder s = new StringBuilder();
        int i = 0;

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
        g.drawString("Multiplication Table", x+55, y);

        y += 20;
        for (i = 1; i < 10; i++)
            g.drawString(" " + i, x + 10, y + 10 + i * 20);

        x += 40;
        for (i = 1; i < 10; i++) {
            s.append("   ").append(i);
        }
        g.drawString(s.toString(), x, y);

        y += 10;
        g.drawRect(x, y, 300, 300);

        s = new StringBuilder();
        y += 20;

        for (i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i*j < 10)
                    s.append("   ").append(i * j);
                else
                    s.append(" ").append(i * j);
            }

            g.drawString(s.toString(), x, y);
            s = new StringBuilder();
            y += 20;
        }
    }
}
