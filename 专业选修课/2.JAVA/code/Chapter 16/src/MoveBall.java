/**
 * 16.3
 * 2021.12.25
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveBall extends JFrame {
    private KeyboardPanel keyPanel = new KeyboardPanel();
    public MoveBall() throws HeadlessException {
        add(keyPanel);
        keyPanel.setFocusable(true);

    }

    public static void main(String[] args) {
        MoveBall frame = new MoveBall();
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("ball");
    }
    static class KeyboardPanel extends JPanel {
        private int x = 100;
        private int y = 100;

        public KeyboardPanel() {
            addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_DOWN:y+=10;break;
                        case KeyEvent.VK_UP:y-=10;break;
                        case KeyEvent.VK_LEFT:x-=10;break;
                        case KeyEvent.VK_RIGHT:x+=10;break;
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillOval(x,y,20,20);
        }
    }
}
