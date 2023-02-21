package model;

import java.util.ArrayList;
import java.util.List;

public class Footwear extends Clothing{
    // maybe change to an array list of doubles
    private ArrayList<String> sizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new piece of footwear with a given name and price
    public Footwear(String name, double price) {
        super(name, price);
        sizes = new ArrayList<>(List.of("6", "7", "8", "9", "10", "11", "12", "13", "14"));
    }

    @Override
    // REQUIRES: size is in sizes
    // MODIFIES: this
    // EFFECTS: removes availability for a specific size.
    public void removeAvailability(String size) {
        sizes.remove(size);
    }

    @Override
    // REQUIRES: size is not already in sizes
    // MODIFIES: this
    // EFFECTS: adds availability for a specific size
    public void addAvailability(String size) {
        sizes.add(size);
    }

    // Getters

    @Override
    public ArrayList<String> getAvailabilities() {
        return sizes;
    }

    @Override
    public String getType() {
        return "Footwear";
    }
}
