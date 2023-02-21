package model;

import java.util.ArrayList;

// TODO: add class level declarations for all classes
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
    // EFFECTS: increments sales by 1
    public void addSale() {
        sales++;
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

    // MODIFIES: this
    // EFFECTS: removes availability for a size of clothing
    public abstract void removeAvailability(String size);

    // MODIFIES: this
    // EFFECTS: adds availability for a size of clothing
    public abstract void addAvailability(String size);

    // Getters

    public String getName() {
        return name;
    }

    public double getPrice() { return price; }

    public int getSales() {
        return sales;
    }

    public boolean isInStock() {
        return !(getAvailabilities().isEmpty());
    }

    public abstract ArrayList<String> getAvailabilities();

    public abstract String getType();
}
