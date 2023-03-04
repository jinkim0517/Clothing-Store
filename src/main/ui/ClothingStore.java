package ui;

import exceptions.*;
import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

// Runs a clothing store with an inventory that can be interacted with the console
public class ClothingStore {
    private static final String JSON_STORE = "./data/inventory.json";
    Scanner userInput;
    Inventory inventory;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: launches the clothing store
    public ClothingStore() throws FileNotFoundException {
        inventory = new Inventory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runUserInterface();
    }

    // EFFECTS: runs the user interface and quits when needed
    // runUserInterface was based on the function runTeller() in the TellerApp class in the model package of this Git
    // repository:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void runUserInterface() {
        boolean active = true;
        String choice;

        while (active) {
            choice = mainMenu();

            if (choice.equals("q")) {
                active = false;
                System.out.println("Goodbye~!");
            } else {
                try {
                    executeChoice(choice);
                } catch (InvalidInputException e) {
                    System.out.println("Invalid input! Please try again.");
                }
            }
        }
    }

    // EFFECTS: prints out the main menu
    private String mainMenu() {
        String choice;
        userInput = new Scanner(System.in);

        System.out.println("\na - add a piece of clothing to the inventory");
        System.out.println("\nr - remove a piece of clothing from the inventory");
        System.out.println("\nu - go to the update menu");
        System.out.println("\nv - view the inventory");
        System.out.println("\nl - load saved inventory");
        System.out.println("\ns - save current inventory");
        System.out.println("\nq - quit.");

        choice = userInput.nextLine();
        return choice;
    }

    // EFFECTS: chooses a menu based on an input choice
    private void executeChoice(String choice) throws InvalidInputException {
        if (choice.equals("a")) {
            addMenu();
        } else if (choice.equals("r")) {
            removeMenu();
        } else if (choice.equals("u")) {
            updateMenu();
        } else if (choice.equals("v")) {
            viewMenu();
        } else if (choice.equals("l")) {
            loadInventory();
        } else if (choice.equals("s")) {
            saveInventory();
        } else {
            throw new InvalidInputException();
        }
    }

    // EFFECTS: saves the inventory data to /data/inventory.json
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            System.out.println("Saved the inventory to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads in inventory data from /data/inventory.json
    private void loadInventory() {
        try {
            inventory = jsonReader.read();
            System.out.println("Loaded the inventory from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for and creates a new piece of clothing, and adds it to the inventory.
    //          Does not check if price is a double value.
    private void addMenu() {
        String name;
        String price;
        String type;

        userInput = new Scanner(System.in);

        System.out.println("Please input the name of the clothing product that you would like to add to the store's "
                + "inventory: ");
        name = userInput.nextLine();


        System.out.println("Please input the price of the clothing product that you would like to add to the store's "
                + "inventory: ");
        price = userInput.nextLine();



        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        try {
            Clothing newClothing = makeClothing(name, parseDouble(price), type);
            inventory.addClothing(newClothing);
            System.out.println("New clothing added!\n");
        } catch (InvalidTypeException e) {
            System.out.println("Invalid clothing type!");
        }
    }

    // EFFECTS: creates a new clothing instance with given input
    private Clothing makeClothing(String name, Double price, String type) throws InvalidTypeException {
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

    // MODIFIES: this
    // EFFECTS: a menu to remove a certain clothing piece
    private void removeMenu() {
        String name;
        String price;
        String type;

        // Keeps the size of inventory before to track change
        int preSize = inventory.getSize();

        System.out.println("Please input the name of the clothing product that you would like to remove");
        name = userInput.nextLine();

        System.out.println("Please input the price of the clothing product that you would like to remove");
        price = userInput.nextLine();

        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        inventory.removeClothing(name, parseDouble(price), type);

        if (preSize == inventory.getSize()) {
            System.out.println("There are no matching clothes. Nothing was removed.");
        } else {
            System.out.println("Clothing removed!");
        }
    }

    // EFFECTS: a menu where methods to update a clothing item can be selected
    private void updateMenu() {
        String choice;
        userInput = new Scanner(System.in);

        System.out.println("\na - add sales to a piece of clothing");
        System.out.println("\nb - add or remove sizes for a piece of clothing");
        System.out.println("\nc - change the name and/or price of a piece of clothing");
        System.out.println("\nm - return to the main menu");

        choice = userInput.nextLine();

        try {
            executeUpdateChoice(choice);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input! Please make a valid choice.");
        }
    }

    // EFFECTS: executes a choice for the update menu based on a give string
    private void executeUpdateChoice(String choice) throws InvalidInputException {
        if (choice.equals("a")) {
            salesMenu();
        } else if (choice.equals("b")) {
            sizesMenu();
        } else if (choice.equals("c")) {
            changeMenu();
        } else if (choice.equals("m")) {
            mainMenu();
        } else {
            throw new InvalidInputException();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add sales to a piece of clothing
    private void salesMenu() {
        String name;
        String type;
        String newSales;

        System.out.println("Please enter the follow for the clothing item that you would like to add sales for: ");

        System.out.print("Name: ");
        name = userInput.nextLine();

        System.out.print("Type: ");
        type = userInput.nextLine();

        Clothing clothing = inventory.findClothing(name, type);

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
    private void sizesMenu() {
        String name;
        String type;

        System.out.println("Please enter the follow for the clothing item that you would like to add sales for: ");
        System.out.print("Name: ");
        name = userInput.nextLine();

        System.out.print("Type: ");
        type = userInput.nextLine();

        Clothing clothing = inventory.findClothing(name, type);

        if (clothing == null) {
            System.out.println("No clothing piece found!");
        } else {
            executeSizeChange(clothing);
        }
    }

    // MODIFIES: clothing
    // EFFECTS: changes the sizes available for a clothing item based on user input
    private void executeSizeChange(Clothing clothing) {
        String choice;

        System.out.println("Here are the following available sizes: " + listToString(clothing.getSizes()));
        System.out.println("a - add size");
        System.out.println("r - remove size");

        choice = userInput.nextLine();

        try {
            addRemoveSize(choice, clothing);
        } catch (InvalidInputException e) {
            System.out.println("Invalid choice, please try again!");
        }
    }

    // MODIFIES: clothing
    // EFFECTS: executes choice to add/remove sizes
    private void addRemoveSize(String choice, Clothing clothing) throws InvalidInputException {
        String size;
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
            throw new InvalidInputException();
        }
    }

    // MODIFIES: this
    // EFFECTS: a menu to change the name and price of a given clothing item
    private void changeMenu() {
        String name;
        String type;

        System.out.println("Please input the name of the clothing product that you would like to change");
        name = userInput.nextLine();

        System.out.println("What clothing category is it? ('Top', 'Bottom', 'Outerwear', or 'Footwear')");
        type = userInput.nextLine();

        Clothing toChange = inventory.findClothing(name, type);

        if (toChange == null) {
            System.out.println("No matching clothes. Please try again.");
        } else {
            executeChange(toChange);
        }
    }

    // MODIFIES: toChange
    // EFFECTS: changes a clothing's name and price based on the user's input
    private void executeChange(Clothing toChange) {
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

    // EFFECTS: prints out all the clothing instances with their information
    private void viewMenu() {
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

        if (result.equals("")) {
            return "No available sizes";
        } else {
            return result;
        }
    }

}
