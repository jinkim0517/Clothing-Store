package model;

import org.json.JSONObject;

import java.util.ArrayList;

// A footwear clothing item with a name, price, category, list of sizes and number of sales
public class Footwear extends Clothing {
    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new piece of footwear with a given name and price
    public Footwear(String name, double price) {
        super(name, price);
        initializeSizes();
    }

    // MODIFIES: this
    // EFFECTS: initializes sizes to appropriate values
    public void initializeSizes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 6; i <= 14; i++) {
            super.addSize(Integer.toString(i));
        }
    }

    // Getters

    @Override
    public String getType() {
        return "Footwear";
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
