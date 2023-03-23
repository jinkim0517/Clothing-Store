package ui.menus;

import javax.swing.*;

// A smaller JFrame that displays a message
public class PopUpScreen extends JFrame {
    protected static final int WIDTH = 500;
    protected static final int HEIGHT = 200;

    // EFFECTS: constructs a small window with a given message
    public PopUpScreen(String text) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLayout(null);

        JLabel message = new JLabel(text);
        message.setBounds(WIDTH / 2 - 175, HEIGHT / 2 - 50, 350, 50);
        message.setVisible(true);
        this.add(message);
    }
}
