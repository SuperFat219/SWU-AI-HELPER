import javax.swing.*;
import java.awt.*;
/**
 * 12.1
 */
public class design1 extends JFrame {
    public design1(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        for (int i=1;i<=3;i++){
            panel1.add(new JButton("Button"+i));
        }
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        for (int i=4;i<=6;i++){
            panel2.add(new JButton("Button"+i));
        }
        add(panel1);
        add(panel2);
    }

    public static void main(String[] args) {
        design1 design = new design1();
//        design.setLayout(new FlowLayout(FlowLayout.CENTER));

        design.setTitle("Buttons Panel");
        design.setSize(400,250);
        design.setLocationRelativeTo(null);
        design.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        design.setVisible(true);
    }
}