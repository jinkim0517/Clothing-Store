package ui.menus;

import exceptions.InvalidTypeException;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A JFrame for adding clothes to an inventory
public class AddScreen extends Screen implements ActionListener {
    Inventory inventory;
    JTextField name = new JTextField();
    JTextField price = new JTextField();
    JTextField type = new JTextField();
    JButton submit = new JButton("Submit");

    // EFFECTS: constructs a JFrame given an inventory
    public AddScreen(Inventory inventory) {
        this.inventory = inventory;
        initializeMenu(this);
        initializeTextFields();
        initializeButtons();
    }

    // EFFECTS: initializes all the text fields in the frame
    private void initializeTextFields() {
        initializeTextField(name, "Please enter the name", 0);
        initializeTextField(price, "Please enter the price", 1);
        initializeTextField(type, "Please enter the type (Top, Bottom, Outerwear, Footwear)", 2);
    }

    // EFFECTS: initializes a single text field
    private void initializeTextField(JTextField textField, String label, int index) {
        JLabel fieldLabel = new JLabel(label);
        if (index == 2) {
            fieldLabel.setBounds(100, HEIGHT / 2 - 50 + 50 * (index - 1), 360, 30);
            textField.setBounds(460, HEIGHT / 2 - 50 + 50 * (index - 1), 250, 30);
        } else {
            fieldLabel.setBounds(100, HEIGHT / 2 - 50 + 50 * (index - 1), 150, 30);
            textField.setBounds(250, HEIGHT / 2 - 50 + 50 * (index - 1), 250, 30);
        }
        this.add(fieldLabel);
        this.add(textField);
    }

    // EFFECTS: initializes all the buttons in the frame
    private void initializeButtons() {
        submit.setBounds(WIDTH - 200, HEIGHT / 2 - 50, 150, 50);
        submit.setVisible(true);
        submit.addActionListener(this);
        this.add(submit);
    }

    // EFFECTS: makes a clothing based on given information
    private Clothing makeClothing(String name, double price, String type) throws InvalidTypeException {
        Clothing newClothing;
        if (type.equals("Top")) {
            newClothing = new Top(name, price);
        } else if (type.equals("Bottom")) {
            newClothing = new Bottom(name, price);
        } else if (type.equals("Outerwear")) {
            newClothing = new Outerwear(name, price);
        } else if (type.equals("Footwear")) {
            newClothing = new Footwear(name, price);
        } else {
            throw new InvalidTypeException();
        }
        return newClothing;
    }

    @Override
    // MODIFIES: inventory
    // EFFECTS: handles button presses
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                String nameToSet = name.getText();
                double priceToSet = Double.parseDouble(price.getText());
                String typeToSet = type.getText();
                inventory.addClothing(makeClothing(nameToSet, priceToSet, typeToSet));
                this.dispose();
                MainScreen backToMain = new MainScreen(inventory);
                PopUpScreen successScreen = new PopUpScreen("Clothing added!");
            } catch (InvalidTypeException | NumberFormatException er) {
                this.dispose();
                MainScreen backToMain = new MainScreen(inventory);
                PopUpScreen errorScreen = new PopUpScreen("The input was not valid, please try again.");
            }
        }
    }
}
