package model;

import java.util.ArrayList;
import java.util.List;

// A top clothing item with a name, price, category, list of sizes and number of sales
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
    public void removeSize(String size) {
        sizes.remove(size);
    }

    @Override
    // REQUIRES: size is not already in sizes
    // MODIFIES: this
    // EFFECTS: adds availability for a specific size
    public void addSize(String size) {
        sizes.add(size);
    }


    // Getters

    @Override
    public ArrayList<String> getSizes() {
        return sizes;
    }

    @Override
    public String getType() {
        return "Top";
    }
}
