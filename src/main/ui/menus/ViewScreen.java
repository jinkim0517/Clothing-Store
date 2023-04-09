package ui.menus;

import model.Clothing;
import model.Inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// A class that represents a JFrame displaying all the clothes in the inventory
public class ViewScreen extends Screen implements ActionListener {
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_WIDTH = 170;
    private Types currentType;

    Inventory inventory;
    JList clothes;
    JButton mainMenu = new JButton("Main Menu");
    JButton reverseOrder = new JButton("Reverse Order");
    JButton toggleType = new JButton("Current Type: Any");

    // EFFECTS: Constructs a view screen with the data of a given inventory
    public ViewScreen(Inventory inventory) {
        this.inventory = inventory;
        this.currentType = Types.NONE;
        initializeMenu(this);
        initializeTextFields();
        initializeButtons();
        this.clothes = new JList(convertInventory(Types.NONE));
        showInventory(this.currentType);
    }

    // EFFECTS: Initializes all the text fields
    private void initializeTextFields() {
        JLabel header = new JLabel("Here are the clothes in the inventory.");
        header.setBounds(50, 50, 300, 50);
        this.add(header);
    }

    // EFFECTS: Initializes the buttons
    private void initializeButtons() {
        initializeButton(reverseOrder, 0);
        initializeButton(toggleType, 1);
        initializeButton(mainMenu, 2);
    }

    // REQUIRES: index >= 0
    // EFFECTS: Initializes a single button to a given string at a given index
    private void initializeButton(JButton button, int index) {
        button.setBounds(WIDTH / 2 + 250, HEIGHT / 2 - 135 + BUTTON_HEIGHT * index, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.addActionListener(this);
        this.add(button);
    }

    // EFFECTS: Converts the inventory data to a list of strings
    private ListModel<String> convertInventory(Types type) {
        DefaultListModel<String> converted = new DefaultListModel<>();

        for (Clothing c : inventory.getInventory()) {
            if (type.equals(Types.NONE)) {
                converted.addElement(c.getName() + ", " + c.getPrice() + ", sizes: {" + listToString(c.getSizes())
                        + "}, " + c.getType() + ", " + c.getSales() + " sales.");
            } else if (c.getType().equals(enumTypesToString(type))) {
                converted.addElement(c.getName() + ", " + c.getPrice() + ", sizes: {" + listToString(c.getSizes())
                        + "}, " + c.getType() + ", " + c.getSales() + " sales.");
            }
        }

        return converted;
    }

    // EFFECTS: returns a string equivalent to the given type
    private String enumTypesToString(Types type) {
        if (type.equals(Types.NONE)) {
            return "None";
        } else if (type.equals(Types.TOP)) {
            return "Top";
        } else if (type.equals(Types.OUTERWEAR)) {
            return "Outerwear";
        } else if (type.equals(Types.BOTTOM)) {
            return "Bottom";
        } else if (type.equals(Types.FOOTWEAR)) {
            return "Footwear";
        } else {
            System.out.println("Poo");
            return "None";
        }
    }

    // EFFECTS: Concatenates and returns all the elements in a list of strings into a single string
    private String listToString(List<String> sizes) {
        String result = "";

        for (int i = 0; i < sizes.size(); i++) {
            result += sizes.get(i);
            if (!(i == sizes.size() - 1)) {
                result += ", ";
            }
        }

        if (result.equals("")) {
            return "No available sizes";
        } else {
            return result;
        }
    }

    // MODIFIES: clothes
    // EFFECTS: Shows the inventory with the desired filter
    private void showInventory(Types type) {
        if (type.equals(Types.NONE)) {
            clothes.setModel((convertInventory(Types.NONE)));
        } else if (type.equals(Types.TOP)) {
            clothes.setModel((convertInventory(Types.TOP)));
        } else if (type.equals(Types.OUTERWEAR)) {
            clothes.setModel((convertInventory(Types.OUTERWEAR)));
        } else if (type.equals(Types.BOTTOM)) {
            clothes.setModel((convertInventory(Types.BOTTOM)));
        } else if (type.equals(Types.FOOTWEAR)) {
            clothes.setModel((convertInventory(Types.FOOTWEAR)));
        }

        clothes.setBounds(WIDTH / 2 - 200, 50, 400, 500);
        clothes.setVisible(true);
        this.add(clothes);
    }

    // MODIFIES: clothes
    // EFFECTS: reverses the order of clothes
    public void reverseOrder() {
        inventory.reverseOrder();
        clothes.setModel(convertInventory(currentType));
    }

    // MODIFIES: currentType
    // EFFECTS: executes a toggle filter based on a given type
    public void executeToggle() {
        if (currentType.equals(Types.NONE)) {
            showInventory(Types.TOP);
            currentType = Types.TOP;
            toggleType.setText("Current Type: Tops");
        } else if (currentType.equals(Types.TOP)) {
            showInventory(Types.OUTERWEAR);
            currentType = Types.OUTERWEAR;
            toggleType.setText("Current Type: Outerwear");
        } else if (currentType.equals(Types.OUTERWEAR)) {
            showInventory(Types.BOTTOM);
            currentType = Types.BOTTOM;
            toggleType.setText("Current Type: Bottoms");
        } else if (currentType.equals(Types.BOTTOM)) {
            showInventory(Types.FOOTWEAR);
            currentType = Types.FOOTWEAR;
            toggleType.setText("Current Type: Footwear");
        } else if (currentType.equals(Types.FOOTWEAR)) {
            showInventory(Types.NONE);
            currentType = Types.NONE;
            toggleType.setText("Current Type: Any");
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Performs actions based on the button pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenu) {
            this.dispose();
            MainScreen main = new MainScreen(inventory);
        } else if (e.getSource() == toggleType) {
            executeToggle();
        } else if (e.getSource() == reverseOrder) {
            reverseOrder();
        }
    }
}
