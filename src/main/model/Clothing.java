package model;

import java.util.ArrayList;

// A clothing item with a name, price, category, and number of sales
public abstract class Clothing {
    private String name;
    private double price;
    private int sales;

    // REQUIRES: non-empty name, non-empty category, 0 <= price
    public Clothing(String name, double price) {
        this.name = name;
        this.price = price;
        this.sales = 0;
    }

    // MODIFIES: this
    // EFFECTS: removes availability for a size of clothing
    public abstract void removeSize(String size);

    // MODIFIES: this
    // EFFECTS: adds availability for a size of clothing
    public abstract void addSize(String size);

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

    public abstract ArrayList<String> getSizes();

    public abstract String getType();
}
