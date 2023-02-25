package model;

import java.util.ArrayList;

// A bottom clothing item with a name, price, category, list of sizes and number of sales
public class Bottom extends Clothing {
    private ArrayList<String> waistSizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new bottom clothing piece with a given name and price
    public Bottom(String name, double price) {
        super(name, price);
        waistSizes = initializeSizes();
    }

    private ArrayList<String> initializeSizes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 28; i <= 33; i++) {
            result.add(Integer.toString(i));
        }
        return result;
    }

    @Override
    // REQUIRES: size is in sizes
    // MODIFIES: this
    // EFFECTS: removes availability for a specific size.
    public void removeSize(String size) {
        waistSizes.remove(size);
    }


    @Override
    // REQUIRES: size is not already in sizes
    // MODIFIES: this
    // EFFECTS: adds availability for a specific size
    public void addSize(String size) {
        waistSizes.add(size);
    }


    // Getters

    @Override
    public ArrayList<String> getSizes() {
        return waistSizes;
    }

    @Override
    public String getType() {
        return "Bottom";
    }
}
