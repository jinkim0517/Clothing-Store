package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// A helper class for tests that check reading/writing clothes
public class CheckClothes {
    Clothing c1;

    // EFFECTS: Checks if a clothing has the same inputted fields
    void checkClothing(Clothing c, String name, double price, String type, ArrayList<String> sizes, int sales) {
        assertEquals(name, c.getName());
        assertEquals(price, c.getPrice());
        assertEquals(type, c.getType());
        assertEquals(sizes, c.getSizes());
        assertEquals(sales, c.getSales());
    }

    @BeforeEach
    // Setup method for tests
    public void setUp() {
        c1 = new Top("T Shirt", 15);
    }

    @Test
    // checks if two clothes have the same specifications
    public void checkSameClothesTest() {
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");

        checkClothing(c1, "T Shirt", 15, "Top", sizes, 0);
    }
}
