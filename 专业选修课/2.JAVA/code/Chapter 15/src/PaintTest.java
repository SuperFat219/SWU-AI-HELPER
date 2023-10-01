import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintTest extends JFrame {
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    PaintTest() {
        setLayout(new FlowLayout());

        add(button1);
        add(button2);
        add(button3);
        buttonListener listener = new buttonListener();
        button2.addActionListener(listener);
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("1");
            }
                                  }
        );
    }
    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == button1){
                System.out.println("1");
            }
            else if(e.getSource() == button2){
                System.out.println("2");
            }
        }

    }
    public static void main(String[] args) {
        PaintTest frame = new PaintTest();
        frame.setTitle("buttons");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
