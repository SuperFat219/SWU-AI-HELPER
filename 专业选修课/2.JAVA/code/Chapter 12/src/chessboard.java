import javax.swing.*;
import java.awt.*;

/**
 *12.10
 */
public class chessboard extends JFrame {
    public chessboard() {
        this.setLayout(new GridLayout(8, 8));
        int flag = 1;
        for (int j = 0; j < 8; j++) {
            if (j % 2 == 0) {
                flag = -1;
            } else {
                flag = 1;
            }
            for (int i = 0; i < 8; i++) {
                JButton button = new JButton();
                if (flag == 1) {
                    button.setBackground(Color.BLACK);
                    add(button);
                    flag = flag * (-1);
                } else {
                    button.setBackground(Color.WHITE);
                    add(button);
                    flag = flag * (-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        chessboard frame = new chessboard();
        frame.setTitle("Chess");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
