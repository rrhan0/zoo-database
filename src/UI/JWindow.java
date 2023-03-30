package UI;

import javax.swing.JFrame;

public class JWindow extends JFrame {
        public JWindow() {
                initialize();
        }

        public void initialize() {
                setTitle("Zoo Database");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(800, 500);
                setVisible(true);
        }
}
