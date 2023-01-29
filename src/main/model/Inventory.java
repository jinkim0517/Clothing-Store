package model;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Clothing> inventory;

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
    // EFFECTS: removes a piece of clothing from the inventory
    public void removeClothing(Clothing c) {
        inventory.remove(c);
    }

    // Getters

    public ArrayList<Clothing> getInventory() {
        return inventory;
    }


}
