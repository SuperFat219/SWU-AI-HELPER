import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class HandleEvent extends JFrame {
    public HandleEvent() throws HeadlessException {
        JButton jbkok = new JButton("OK");
        JButton jbkcancel = new JButton("CANCEL");
        JPanel panel = new JPanel();
        panel.add(jbkok);
        panel.add(jbkcancel);
        add(panel);
        OKlistenerClass listener = new OKlistenerClass();
        jbkok.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new HandleEvent();
        frame.setTitle("frame");
        frame.setSize(200,150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class OKlistenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e){
        System.out.println("Ok");
    }
}
class CancelListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        System.out.println("Cancel");
    }
}
