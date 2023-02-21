package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class ClothingStore {
    Scanner userInput;
    Inventory inventory;

    // EFFECTS: launches the clothing store
    public ClothingStore() {
        runUserInterface();
    }

    // EFFECTS: runs the user interface and quits when needed
    // runUserInterface was based on the function runTeller() in the TellerApp class in the model package of this Git
    // repository:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void runUserInterface() {
        inventory = new Inventory();
        boolean active = true;
        String choice;

        while (active) {
            choice = mainMenu();

            if (choice.equals("q")) {
                active = false;
                System.out.println("Goodbye~!");
            } else {
                executeChoice(choice);
            }
        }
    }

    // EFFECTS: prints out the main menu
    private String mainMenu() {
        String choice;
        userInput = new Scanner(System.in);

        System.out.println("Welcome to the Main Menu! Please input an action!");
        System.out.println("\nType 'a' to add a piece of clothing to the inventory.");
        System.out.println("\nType 'r' to remove a piece of clothing from the inventory.");
        System.out.println("\nType 'c' to change a piece of clothing in the inventory.");
        System.out.println("\nType 'v' to view the inventory.");
        System.out.println("\nType 'q' to quit.");

        choice = userInput.nextLine();
        return choice;
    }

    // EFFECTS: chooses a menu based on an input choice
    private void executeChoice(String choice) {
        if (choice.equals("a")) {
            addMenu();
        } else if (choice.equals("r")) {
            removeMenu();
        } else if (choice.equals("c")) {
            changeMenu();
        } else if (choice.equals("v")) {
            viewMenu();
        } else {
            System.out.println("Invalid input! Please make a valid choice.");
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for and creates a new piece of clothing, and adds it to the inventory.
    private void addMenu() {
        String name;
        String price;
        String type;

        userInput = new Scanner(System.in);

        System.out.println("~~~~Add Menu~~~~~\n");

        System.out.println("Please input the name of the clothing product that you would like to add to the store's " +
                "inventory: ");
        name = userInput.nextLine();


        System.out.println("Please input the price of the clothing product that you would like to add to the store's " +
                "inventory: ");
        price = userInput.nextLine();


        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        inventory.addClothing(makeClothing(name, parseDouble(price), type));

        System.out.println("New clothing added!\n");
    }

    // EFFECTS: creates a new clothing instance with given input
    private Clothing makeClothing(String name, Double price, String type) {
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
            newClothing = null;
            System.out.println("ERROR: Invalid type");
        }
        return newClothing;
    }

    // MODIFIES: this
    // EFFECTS: a menu to remove a certain clothing piece
    private void removeMenu() {
        String name;
        String price;
        String type;

        System.out.println("~~~~Remove Menu~~~~");

        System.out.println("Please input the name of the clothing product that you would like to remove");
        name = userInput.nextLine();

        System.out.println("Please input the price of the clothing product that you would like to remove");
        price = userInput.nextLine();

        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: a menu to change certain information of a given clothing item
    private void changeMenu() {
        System.out.println("~~~~Change Menu~~~~");
    }

    // EFFECTS: prints out all the clothing instances with their information
    private void viewMenu() {
        System.out.println("~~~~View Menu~~~~\n");

        if (inventory.getSize() == 0) {
            System.out.println("There are no clothes to view.");
        } else {
            System.out.println("Here are all of the clothes currently in the inventory:");

            for (Clothing c : inventory.getInventory()) {
                String name = c.getName();
                Double price = c.getPrice();
                ArrayList<String> availabilities = c.getAvailabilities();
                String type = c.getType();
                int sales = c.getSales();
                System.out.println(name + ", " + price + ", " + listToString(availabilities) + ", " + type + ", " + sales +
                        " sales.");
            }
        }
    }

    // EFFECTS: concatenates and returns all the elements in a list of strings into a single string
    private String listToString(List<String> availabilities) {
        String result = availabilities.get(0);

        for (int i = 1; i < availabilities.size(); i++) {
            result += availabilities.get(i);
            if (!(i == availabilities.size() - 1)) {
                result += ", ";
            }
        }

        if (result == null) {
            return "No available sizes";
        } else {
            return result;
        }
    }


}
