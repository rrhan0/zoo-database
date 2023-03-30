package controller;

import UI.JWindow;
import javax.swing.SwingUtilities;

public class ZooController {
    public static void main(String[] args) {
        //Run this program on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JWindow frame1 = new JWindow();
        });
    }
}
