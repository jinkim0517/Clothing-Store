package model;

import java.util.ArrayList;
import java.util.List;

public class Bottom extends Clothing {
    private ArrayList<String> waistSizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new bottom clothing piece with a given name and price
    public Bottom(String name, double price) {
        super(name, price);
        waistSizes = new ArrayList<>(List.of("28", "29", "30", "31", "32", "33"));
    }

    @Override
    // REQUIRES: size is in sizes
    // MODIFIES: this
    // EFFECTS: removes availability for a specific size.
    public void removeAvailability(String size) {
        waistSizes.remove(size);
    }


    @Override
    // REQUIRES: size is not already in sizes
    // MODIFIES: this
    // EFFECTS: adds availability for a specific size
    public void addAvailability(String size) {
        waistSizes.add(size);
    }


    // Getters

    @Override
    public ArrayList<String> getAvailabilities() {
        return waistSizes;
    }

    @Override
    public String getType() {
        return "Bottom";
    }
}
