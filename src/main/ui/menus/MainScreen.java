package ui.menus;

import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// A JFrame screen that represents the home screen for the UI
public class MainScreen extends Screen implements ActionListener {
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_WIDTH = 170;
    private static final String JSON_STORE = "./data/inventory.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Inventory inventory;

    // For the main menu
    private JButton toAddMenu = new JButton();
    private JButton toRemoveMenu = new JButton();
    private JButton toViewMenu = new JButton();
    private JButton toLoad = new JButton();
    private JButton toSave = new JButton();
    private JButton toQuit = new JButton();
    JLabel message;
    private ImageIcon image = new ImageIcon("./src/main/ui/images/poloTImage.jpeg");


    // EFFECTS: Initializes the main screen with the data of a given inventory
    public MainScreen(Inventory inventory) {
        this.inventory = inventory;
        initializeMessage();
        initializeButtons();
        initializeMenu(this);
        initializeImages();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // EFFECTS: Initializes the messages
    private void initializeMessage() {
        message = new JLabel();
        message.setText("Welcome to the Main Menu, please select an option.");
        message.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        message.setBounds(85, 100, 500, 50);
        this.add(message);
    }

    // EFFECTS: Initializes all the buttons
    private void initializeButtons() {
        initializeButton(toAddMenu, "Add Clothing", 0);
        initializeButton(toRemoveMenu, "Remove Clothing", 1);
        initializeButton(toViewMenu, "View Clothing", 2);
        initializeButton(toLoad, "Load Saved Inventory", 3);
        initializeButton(toSave, "Save Current Inventory", 4);
        initializeButton(toQuit, "Quit", 5);

    }

    // REQUIRES: index >= 0
    // EFFECTS: Initializes a single button to a given string at a given index
    private void initializeButton(JButton button, String label, int index) {
        button.setText(label);
        button.setBounds(WIDTH / 2 + 150, 110 + BUTTON_HEIGHT * index, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.addActionListener(this);
        this.add(button);
    }

    // EFFECTS: initializes images to be displayed
    private void initializeImages() {
        JLabel imagePlacement = new JLabel(image);
        imagePlacement.setBounds(140, HEIGHT / 2 - 200, 400, 400);
        this.add(imagePlacement);
    }

    // MODIFIES: inventory
    // EFFECTS: loads inventory dataset from JSON_STORE
    private void load() {
        try {
            inventory = jsonReader.read();
            PopUpScreen success = new PopUpScreen("Loaded the inventory from " + JSON_STORE);
        } catch (IOException e) {
            PopUpScreen fail = new PopUpScreen("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: inventory.json
    // EFFECTS: saves inventory dataset to JSON_STORE
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            PopUpScreen success = new PopUpScreen("Saved the inventory to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            PopUpScreen fail = new PopUpScreen("Unable to write to file: " + JSON_STORE);
        }
    }


    @Override
    // EFFECTS: Performs the actions based on the button pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toAddMenu) {
            this.dispose();
            AddScreen addScreen = new AddScreen(inventory);
        } else if (e.getSource() == toRemoveMenu) {
            this.dispose();
            RemoveScreen removeScreen = new RemoveScreen(inventory);
        } else if (e.getSource() == toViewMenu) {
            ViewScreen viewScreen = new ViewScreen(inventory);
        } else if (e.getSource() == toLoad) {
            load();
        } else if (e.getSource() == toSave) {
            save();
        } else if (e.getSource() == toQuit) {
            System.exit(0);
        }
    }
}
