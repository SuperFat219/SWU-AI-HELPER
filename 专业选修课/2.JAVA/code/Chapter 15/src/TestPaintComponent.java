import javax.swing.*;
import java.awt.*;

/**
 * 程序清单15-1
 */

public class TestPaintComponent extends JFrame {
    public TestPaintComponent(){
        add(new NewPanel());
    }

    public static void main(String[] args) {
        TestPaintComponent frame = new TestPaintComponent();
        frame.setVisible(true);
        frame.setTitle("hhh");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,100);
    }
}
class NewPanel extends JPanel {
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(0,0,100,100);
        g.drawString("hhh",0,40);
    }
}