package ui.menus;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A class that represents the screen to remove clothes from the inventory
public class RemoveScreen extends Screen implements ActionListener {
    Inventory inventory;

    JTextField name = new JTextField();
    JTextField price = new JTextField();
    JTextField type = new JTextField();

    JButton submit = new JButton("Submit");

    public RemoveScreen(Inventory inventory) {
        this.inventory = inventory;
        initializeMenu(this);
        initializeTextFields();
        initializeButtons();
    }

    private void initializeTextFields() {
        initializeTextField(name, "Please enter the name", 0);
        initializeTextField(price, "Please enter the price", 1);
        initializeTextField(type, "Please enter the type (Top, Bottom, Outerwear, Footwear)", 2);
    }

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

    private void initializeButtons() {
        submit.setBounds(WIDTH - 200, HEIGHT / 2 - 50, 150, 50);
        submit.setVisible(true);
        submit.addActionListener(this);
        this.add(submit);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                int prevSize = inventory.getSize();
                String nameToRemove = name.getText();
                double priceToRemove = Double.parseDouble(price.getText());
                String typeToRemove = type.getText();
                inventory.removeClothing(nameToRemove, priceToRemove, typeToRemove);

                if (inventory.getSize() == prevSize) {
                    this.dispose();
                    MainScreen backToMain = new MainScreen(inventory);
                    PopUpScreen successScreen = new PopUpScreen("No clothing found, nothing was removed!");
                } else {
                    this.dispose();
                    MainScreen backToMain = new MainScreen(inventory);
                    PopUpScreen successScreen = new PopUpScreen("Clothing removed!");
                }
            } catch (NumberFormatException er) {
                this.dispose();
                MainScreen backToMain = new MainScreen(inventory);
                PopUpScreen errorScreen = new PopUpScreen("The input was not valid, please try again.");
            }
        }
    }
}
