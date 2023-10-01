import javax.swing.*;
import java.awt.*;

/**
 * 15.2
 */
public class TestOvalButton extends JFrame{
    public TestOvalButton(){
        OvalButton button1 = new OvalButton("OK");
        OvalButton button2 = new OvalButton("Cancel");
        Dimension dimension = new Dimension(300,150);
        button1.setPreferredSize(dimension);
        button2.setPreferredSize(dimension);
        setLayout(new FlowLayout());
        add(button1);add(button2);
    }

    public static void main(String[] args) {
        TestOvalButton frame = new TestOvalButton();
        frame.setTitle("OvalButton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class OvalButton extends JButton {
    public OvalButton() {
    }

    public OvalButton(String text) {
        super(text);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawOval(10,10,getWidth()-20,getHeight()-20);
    }

}