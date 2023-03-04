package model;

import org.json.JSONObject;

import java.util.ArrayList;

// A bottom clothing item with a name, price, category, list of sizes and number of sales
public class Bottom extends Clothing {
    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new bottom clothing piece with a given name and price
    public Bottom(String name, double price) {
        super(name, price);
        initializeSizes();
    }


    // MODIFIES: this
    // EFFECTS: initializes sizes to appropriate values
    public void initializeSizes() {
        for (int i = 28; i <= 33; i++) {
            super.addSize(Integer.toString(i));
        }
    }

    // Getters

    @Override
    public String getType() {
        return "Bottom";
    }

    @Override
    // EFFECTS: creates a Json equivalent of this clothing object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", super.getName());
        json.put("price", super.getPrice());
        json.put("type", getType());
        json.put("sizes", getSizes());
        json.put("sales", super.getSales());

        return json;
    }
}
