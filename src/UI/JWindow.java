package UI;

import javax.swing.*;
import java.awt.*;

public class JWindow {
        private JFrame frame;
        public JWindow() {
                initialize();
                show();
        }

        public void initialize() {
                //frame creation
                frame = new JFrame();
                frame.setTitle("Zoo Database");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(800, 500);
                frame.setLocationRelativeTo(null);

                //panel1 creation
                JPanel panel1 = new JPanel();
                panel1.setLayout(new FlowLayout());
                panel1.setBackground(Color.DARK_GRAY);

                //button creation
                Button button1 = new Button("Button");
                button1.setFocusable(false);

                panel1.add(button1);

                frame.add(panel1);
        }

        public void show() {
                this.frame.setVisible(true);
        }
}
