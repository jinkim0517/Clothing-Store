package model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// A helper class for tests that check reading/writing clothes
public class CheckClothes {
    // EFFECTS: Checks if a clothing has the same inputted fields
    void checkClothing(Clothing c, String name, double price, String type, ArrayList<String> sizes, int sales) {
        assertEquals(name, c.getName());
        assertEquals(price, c.getPrice());
        assertEquals(type, c.getType());
        assertEquals(sizes, c.getSizes());
        assertEquals(sales, c.getSales());
    }
}
