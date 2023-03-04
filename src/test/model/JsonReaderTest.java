package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// A test class for the JsonReader class
// NOTE: This test class was heavily derived from the JsonSerializationDemo from EdX:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory inventory = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/emptyInventoryTest.json");
        try {
            Inventory inventory = reader.read();
            assertEquals(0, inventory.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/generalInventoryTest.json");
        try {
            Inventory inventory = reader.read();
            List<Clothing> clothes = inventory.getInventory();
            assertEquals(2, inventory.getSize());

            ArrayList<String> firstSizes = new ArrayList<>();
            firstSizes.add("S");
            firstSizes.add("M");
            firstSizes.add("L");
            firstSizes.add("XL");

            ArrayList<String> secondSizes = new ArrayList<>();
            for (int i = 28; i <= 33; i++) {
                secondSizes.add(Integer.toString(i));
            }

            checkClothing(clothes.get(0), "Leather Jacket", 70, "Outerwear", firstSizes, 17);
            checkClothing(clothes.get(1), "Dress Pants", 45, "Bottom", secondSizes, 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    void checkClothing(Clothing c, String name, double price, String type, ArrayList<String> sizes, int sales) {
        assertEquals(name, c.getName());
        assertEquals(price, c.getPrice());
        assertEquals(type, c.getType());
        assertEquals(sizes, c.getSizes());
        assertEquals(sales, c.getSales());
    }
}
