package model;

import java.util.ArrayList;

// An inventory of clothing with a list of clothes
public class Inventory {
    ArrayList<Clothing> inventory;

    // Creates a new list of clothes to be used as an inventory.
    public Inventory() {
        inventory = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a clothing item to the inventory
    public void addClothing(Clothing c) {
        inventory.add(c);
    }

    // REQUIRES: clothing to be removed must be in the inventory already
    // MODIFIES: this
    // EFFECTS: searches for and removes a piece of clothing from the inventory
    public void removeClothing(String name, double price, String type) {
        Clothing toRemove = findClothing(name, price, type);
        inventory.remove(toRemove);
    }

    public Clothing findClothing(String name, double price, String type) {
        Clothing result = null;
        for (Clothing c: inventory) {
            if (name.equals(c.getName()) && (price == c.getPrice()) && type.equals(c.getType())) {
                result = c;
            }
        }
        return result;
    }

    // Getters
    public Clothing getClothingAt(int index) {
        return inventory.get(index);
    }

    public ArrayList<Clothing> getInventory() {
        return inventory;
    }

    public int getSize() {
        return inventory.size();
    }
}
