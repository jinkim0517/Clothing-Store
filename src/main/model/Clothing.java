package model;

public class Clothing {
    private String name;
    private String category;
    private double price;
    private boolean availability;
    private int sales;

    // REQUIRES: non-empty name, non-empty category, 0 <= price
    public Clothing(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availability = true;
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

    // REQUIRES: input is a non-empty string
    // MODIFIES: this
    // EFFECTS: changes the clothing's category to a given string
    public void setCategory(String category) {
        this.category = category;
    }

    // REQUIRES: input is >= 0
    // MODIFIES: this
    // EFFECTS: changes the clothing's price to a given double
    public void setPrice(double price) {
        this.price = price;
    }

    // MODIFIES: this
    // EFFECTS: changes the clothing's availability to a given boolean
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return availability;
    }

    public int getSales() {
        return sales;
    }
}
