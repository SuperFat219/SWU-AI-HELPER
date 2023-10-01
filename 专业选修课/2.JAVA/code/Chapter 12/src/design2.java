import javax.swing.*;
import java.awt.*;

/**
 * 12.6
 */
public class design2 extends JFrame {
    private ImageIcon icon1 = new ImageIcon("/home/god/Code/2021_JAVA/Chpater 12/src/泡芙.jpeg");
    private ImageIcon icon2 = new ImageIcon("/home/god/Code/2021_JAVA/Chpater 12/src/泡芙.jpeg");
    private ImageIcon icon3 = new ImageIcon("/home/god/Code/2021_JAVA/Chpater 12/src/泡芙.jpeg");
    private ImageIcon icon4 = new ImageIcon("/home/god/Code/2021_JAVA/Chpater 12/src/泡芙.jpeg");
    public design2() {
//        JPanel panel = new JPanel();
        JSplitPane SplitPane1 = new JSplitPane();
//        SplitPane1.setDividerLocation(0.5);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(new JLabel(icon1));
        panel1.add(new JLabel(icon2));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        panel2.add(new JLabel(icon3));
        panel2.add(new JLabel(icon4));
        SplitPane1.add(panel1,JSplitPane.LEFT);
        SplitPane1.add(panel2,JSplitPane.RIGHT);
        add(SplitPane1);
    }

    public static void main(String[] args) {
        design2 frame = new design2();
        frame.setTitle("Buttons Panel");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
