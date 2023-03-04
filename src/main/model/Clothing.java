package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// A clothing item with a name, price, category, and number of sales
public abstract class Clothing implements Writable {
    private String name;
    private double price;
    private int sales;
    private ArrayList<String> sizes;

    // REQUIRES: non-empty name, non-empty category, 0 <= price
    public Clothing(String name, double price) {
        this.name = name;
        this.price = price;
        this.sales = 0;
        this.sizes = new ArrayList<>();
    }

    public abstract void initializeSizes();

    // MODIFIES: this
    // EFFECTS: removes availability for a size of clothing
    public void removeSize(String size) {
        sizes.remove(size);
    }

    // MODIFIES: this
    // EFFECTS: adds availability for a size of clothing
    public void addSize(String size) {
        sizes.add(size);
    }

    // MODIFIES: this
    // EFFECTS: sets the available sizes for this clothing piece
    public void setSizes(ArrayList<String> sizes) {
        this.sizes = sizes;
    }

    // MODIFIES: this
    // EFFECTS: increments sales by 1
    public void addSales(int amount) {
        sales += amount;
    }

    // Setters

    // REQUIRES: input is a non-empty string
    // MODIFIES: this
    // EFFECTS: changes the clothing's name to a given string
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: input is >= 0
    // MODIFIES: this
    // EFFECTS: changes the clothing's price to a given double
    public void setPrice(double price) {
        this.price = price;
    }

    // Getters

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public boolean isInStock() {
        return !(getSizes().isEmpty());
    }

    public ArrayList<String> getSizes() {
        return sizes;
    }

    public abstract String getType();

    public abstract JSONObject toJson();
}
