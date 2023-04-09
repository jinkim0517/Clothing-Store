package model;

import org.json.JSONObject;

// An outerwear clothing item with a name, price, category, list of sizes and number of sales
public class Outerwear extends Clothing {
    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new outerwear clothing with a given name and price
    public Outerwear(String name, double price) {
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

    // Getters

    @Override
    public String getType() {
        return "Outerwear";
    }
}