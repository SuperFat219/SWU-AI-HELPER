import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 16.24
 */

public class ControlableClock extends JFrame {
    private final Clock clock = new Clock();

    public ControlableClock() {
        JPanel panel = new JPanel();
        JButton startButton = new JButton("Start");
        panel.add(startButton);
        JButton stopButton = new JButton("Stop");
        panel.add(stopButton);

        this.add(clock, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clock.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clock.stop();
            }
        });
    }

    public static void main(String[] args) {
        ControlableClock frame = new ControlableClock();
        frame.setTitle("clock");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    public static class Clock extends StillClock {
        Timer timer = new Timer(1000, new TimerListener());

        public Clock() {
            timer.start();
        }

        public void start() {
            timer.start();
        }

        public void stop() {
            timer.stop();
        }

        private class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                setCurrentTime();
                repaint();
            }
        }
    }
}