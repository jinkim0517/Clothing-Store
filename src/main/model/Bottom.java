package model;

import java.util.ArrayList;

// A bottom clothing item with a name, price, category, list of sizes and number of sales
public class Bottom extends Clothing {
    private ArrayList<String> waistSizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new bottom clothing piece with a given name and price
    public Bottom(String name, double price) {
        super(name, price);
        waistSizes = new ArrayList<>();
        initializeSizes();
    }

    // MODIFIES: this
    // EFFECTS: initializes sizes to appropriate values
    private void initializeSizes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 28; i <= 33; i++) {
            waistSizes.add(Integer.toString(i));
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes availability for a specific size.
    public void removeSize(String size) {
        waistSizes.remove(size);
    }


    @Override
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
