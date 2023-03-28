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
public class JsonReaderTest extends CheckClothesTest {
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
            assertEquals(4, inventory.getSize());

            ArrayList<String> firstSizes = new ArrayList<>();
            firstSizes.add("S");
            firstSizes.add("M");
            firstSizes.add("L");
            firstSizes.add("XL");

            ArrayList<String> secondSizes = new ArrayList<>();
            for (int i = 28; i <= 33; i++) {
                secondSizes.add(Integer.toString(i));
            }

            ArrayList<String> defaultShoeSizes = new ArrayList<>();
            for (int i = 6; i <= 14; i++) {
                defaultShoeSizes.add(Integer.toString(i));
            }

            checkClothing(clothes.get(0), "Leather Jacket", 70, "Outerwear", firstSizes, 17);
            checkClothing(clothes.get(1), "Dress Pants", 45, "Bottom", secondSizes, 0);
            checkClothing(clothes.get(2), "Coat", 150, "Outerwear", firstSizes, 0);
            checkClothing(clothes.get(3), "Shoes", 60, "Footwear", defaultShoeSizes, 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
