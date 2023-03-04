package model;

import org.json.JSONObject;

import java.util.ArrayList;

// A top clothing item with a name, price, category, list of sizes and number of sales
public class Top extends Clothing {
    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new top clothing with a given name and price
    public Top(String name, double price) {
        super(name, price);
        initializeSizes();
    }

    // MODIFIES: this
    // EFFECTS: initializes sizes to appropriate values
    public void initializeSizes() {
        super.addSize("S");
        super.addSize("M");
        super.addSize("L");
        super.addSize("XL");
    }

    // Getters

    @Override
    public String getType() {
        return "Top";
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
