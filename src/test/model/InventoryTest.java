package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory inventory;
    Clothing c1;
    Clothing c2;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        c1 = new Top("Relaxed Cardigan", 24.99);
        c2 = new Footwear("Nike Sacai Waffle", 350.00);
    }

    @Test
    public void constructorTest() {
        assertEquals(0, inventory.getSize());
    }

    @Test
    public void addClothingTest() {
        inventory.addClothing(c1);

        assertEquals(1, inventory.getSize());
        assertEquals(c1, inventory.getClothingAt(0));

        inventory.addClothing(c2);

        assertEquals(2, inventory.getSize());
        assertEquals(c2, inventory.getClothingAt(1));
    }

    @Test
    public void findClothingTest() {
        assertEquals(null, inventory.findClothing("Relaxed Cardigan", 24.99, "Top"));

        inventory.addClothing(c1);
        inventory.addClothing(c2);

        assertEquals(c1, inventory.findClothing("Relaxed Cardigan", 24.99, "Top"));
        assertEquals(c2, inventory.findClothing("Nike Sacai Waffle", 350.00, "Footwear"));

        assertEquals(null, inventory.findClothing("Relaxed Cardigan", 3, "Top"));
        assertEquals(null, inventory.findClothing("Relaxed Cardigan", 24.99, "Bottom"));
        assertEquals(null, inventory.findClothing("Relaxed Hoodie", 24.99, "Top"));
    }

    @Test
    public void removeClothingTest() {
        inventory.addClothing(c1);
        inventory.addClothing(c2);

        inventory.removeClothing(c2.getName(), c2.getPrice(), c2.getType());

        assertEquals(1, inventory.getInventory().size());
        assertEquals(c1, inventory.getClothingAt(0));

        inventory.removeClothing(c1.getName(), c1.getPrice(), c1.getType());

        assertEquals(0, inventory.getInventory().size());
    }

    @Test
    public void getClothingAtTest() {
        inventory.addClothing(c1);
        inventory.addClothing(c2);

        assertEquals(c1, inventory.getClothingAt(0));
        assertEquals(c2, inventory.getClothingAt(1));
    }
}
