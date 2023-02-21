package model;

import java.util.ArrayList;
import java.util.List;

public class Top extends Clothing {
    private ArrayList<String> sizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new top clothing with a given name and price
    public Top(String name, double price) {
        super(name, price);
        sizes = new ArrayList<>(List.of("S", "M", "L", "XL"));
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
        return "Top";
    }
}
