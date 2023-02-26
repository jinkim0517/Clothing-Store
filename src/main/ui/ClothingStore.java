package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

// Runs a clothing store with an inventory that can be interacted with the console
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

        System.out.println("~~~~~~~~Welcome to the Main Menu! Please input an action!~~~~~~~~");
        System.out.println("\nType 'a' to add a piece of clothing to the inventory.");
        System.out.println("\nType 'r' to remove a piece of clothing from the inventory.");
        System.out.println("\nType 'u' to go to the update menu.");
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
        } else if (choice.equals("u")) {
            updateMenu();
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

        System.out.println("Please input the name of the clothing product that you would like to add to the store's "
                + "inventory: ");
        name = userInput.nextLine();


        System.out.println("Please input the price of the clothing product that you would like to add to the store's "
                + "inventory: ");
        price = userInput.nextLine();


        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        Clothing newClothing = makeClothing(name, parseDouble(price), type);

        if (newClothing == null) {
            System.out.println("Invalid clothing category.");
        } else {
            inventory.addClothing(newClothing);
            System.out.println("New clothing added!\n");
        }
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

        // Keeps the size of inventory before to track change
        int preSize = inventory.getSize();

        System.out.println("~~~~Remove Menu~~~~");

        System.out.println("Please input the name of the clothing product that you would like to remove");
        name = userInput.nextLine();

        System.out.println("Please input the price of the clothing product that you would like to remove");
        price = userInput.nextLine();

        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        inventory.removeClothing(name, parseDouble(price), type);

        if (preSize == inventory.getSize()) {
            System.out.println("There is no matching clothes. Nothing was removed.");
        } else {
            System.out.println("Clothing removed!");
        }
    }

    // EFFECTS: a menu where methods to update a clothing item can be selected
    private void updateMenu() {
        String choice;
        userInput = new Scanner(System.in);

        System.out.println("~~~~~~~~Welcome to the Update Menu! Please input an action!~~~~~~~~");
        System.out.println("\nType 'a' to add sales to a piece of clothing.");
        System.out.println("\nType 'b' to add or remove sizes for a piece of clothing.");
        System.out.println("\nType 'c' to change the name and/or price of a piece of clothing.");
        System.out.println("\nType 'm' to return to the main menu.");

        choice = userInput.nextLine();

        if (choice.equals("a")) {
            salesMenu();
        } else if (choice.equals("b")) {
            sizesMenu();
        } else if (choice.equals("c")) {
            changeMenu();
        } else if (choice.equals("m")) {
            mainMenu();
        } else {
            System.out.println("Invalid input! Please make a valid choice.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add sales to a piece of clothing
    private void salesMenu() {
        String name;
        String price;
        String type;
        String newSales;
        System.out.println("~~~~Sales Menu~~~~");

        System.out.println("Please enter the follow for the clothing item that you would like to add sales for: ");

        System.out.print("Name: ");
        name = userInput.nextLine();

        System.out.print("Price: ");
        price = userInput.nextLine();

        System.out.print("Type: ");
        type = userInput.nextLine();

        Clothing clothing = inventory.findClothing(name, parseDouble(price), type);

        if (clothing == null) {
            System.out.println("No clothing piece found!");
        } else {
            System.out.println("Please enter the number of sales you would like to add for the clothing item: ");

            newSales = userInput.nextLine();
            clothing.addSales(parseInt(newSales));
            System.out.println("Sales added!");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add or remove sizes for a piece of clothing
    @SuppressWarnings("methodlength")
    private void sizesMenu() {
        String name;
        String price;
        String type;
        String choice;
        String size;
        System.out.println("~~~~Sizes Menu~~~~");

        System.out.println("Please enter the follow for the clothing item that you would like to add sales for: ");

        System.out.print("Name: ");
        name = userInput.nextLine();

        System.out.print("Price: ");
        price = userInput.nextLine();

        System.out.print("Type: ");
        type = userInput.nextLine();

        Clothing clothing = inventory.findClothing(name, parseDouble(price), type);

        if (clothing == null) {
            System.out.println("No clothing piece found!");
        } else {
            System.out.println("Here are the following available sizes: " + listToString(clothing.getSizes()));

            System.out.println("Would you like to add or remove a size? (type 'a' or 'r')");

            choice = userInput.nextLine();

            if (choice.equals("a")) {
                System.out.println("Please enter a size that you would like to add: ");
                size = userInput.nextLine();
                clothing.addSize(size);
                System.out.println("Size " + size + " added!");
            } else if (choice.equals("r")) {
                System.out.println("Please enter a size that you would like to remove: ");
                size = userInput.nextLine();
                clothing.removeSize(size);
                System.out.println("Size " + size + " removed!");
            } else {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: a menu to change the name and price of a given clothing item
    @SuppressWarnings("methodlength")
    private void changeMenu() {
        String name;
        String price;
        String type;

        System.out.println("~~~~Change Menu~~~~\n");

        System.out.println("Please input the name of the clothing product that you would like to change");
        name = userInput.nextLine();

        System.out.println("Please input the price of the clothing product that you would like to change");
        price = userInput.nextLine();

        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        Clothing toChange = inventory.findClothing(name, parseDouble(price), type);

        if (toChange == null) {
            System.out.println("No matching clothes. Please try again.");
        } else {
            String newName;
            String newPrice;

            System.out.println("Please input the updated name of the clothing product that you would like to change");
            newName = userInput.nextLine();

            System.out.println("Please input the updated price of the clothing product that you would like to change");
            newPrice = userInput.nextLine();

            toChange.setName(newName);
            toChange.setPrice(parseDouble(newPrice));

            System.out.println("Changes applied!");
        }
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
                double price = c.getPrice();
                ArrayList<String> availabilities = c.getSizes();
                String type = c.getType();
                int sales = c.getSales();
                System.out.println(name + ", " + price + ", sizes: {" + listToString(availabilities) + "}, " + type
                        + ", " + sales + " sales.");
            }
        }
    }

    // EFFECTS: concatenates and returns all the elements in a list of strings into a single string
    private String listToString(List<String> sizes) {
        String result = "";

        for (int i = 0; i < sizes.size(); i++) {
            result += sizes.get(i);
            if (!(i == sizes.size() - 1)) {
                result += ", ";
            }
        }

        if (result == "") {
            return "No available sizes";
        } else {
            return result;
        }
    }

}
