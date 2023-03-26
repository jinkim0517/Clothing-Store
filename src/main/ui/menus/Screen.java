package ui.menus;

import javax.swing.*;

// A superclass for all screens
public class Screen extends JFrame {
    protected static final int WIDTH = 1000;
    protected static final int HEIGHT = 700;

    // EFFECTS: initializes a JFrame to a standardized size and setting
    protected void initializeMenu(JFrame menu) {
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setLayout(null);
        menu.setSize(WIDTH, HEIGHT);
        menu.setVisible(true);
        menu.setResizable(false);
    }
}
