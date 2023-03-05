package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends CheckClothes {
    @Test
    void testWriterInvalidFile() {
        try {
            Inventory inventory = new Inventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInventory() {
        try {
            Inventory inventory = new Inventory();
            JsonWriter writer = new JsonWriter("./data/emptyInventoryWriteTest.json");
            writer.open();
            writer.write(inventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyInventoryWriteTest.json");
            inventory = reader.read();
            assertEquals(0, inventory.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Inventory inventory = new Inventory();
            inventory.addClothing(new Top("Polo Shirt", 15));
            inventory.addClothing(new Bottom("Jeans", 25));
            inventory.addClothing(new Outerwear("Coat", 150));
            inventory.addClothing(new Footwear("Shoes", 60));
            JsonWriter writer = new JsonWriter("./data/generalInventoryWriteTest.json");
            writer.open();
            writer.write(inventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/generalInventoryWriteTest.json");
            inventory = reader.read();
            List<Clothing> clothes = inventory.getInventory();
            assertEquals(4, clothes.size());

            ArrayList<String> defaultTopSizes = new ArrayList<>();
            defaultTopSizes.add("S");
            defaultTopSizes.add("M");
            defaultTopSizes.add("L");
            defaultTopSizes.add("XL");

            ArrayList<String> defaultBotSizes = new ArrayList<>();
            for (int i = 28; i <= 33; i++) {
                defaultBotSizes.add(Integer.toString(i));
            }

            ArrayList<String> defaultShoeSizes = new ArrayList<>();
            for (int i = 6; i <= 14; i++) {
                defaultShoeSizes.add(Integer.toString(i));
            }

            checkClothing(clothes.get(0), "Polo Shirt", 15, "Top", defaultTopSizes, 0);
            checkClothing(clothes.get(1), "Jeans", 25, "Bottom", defaultBotSizes, 0);
            checkClothing(clothes.get(2), "Coat", 150, "Outerwear", defaultTopSizes, 0);
            checkClothing(clothes.get(3), "Shoes", 60, "Footwear", defaultShoeSizes, 0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
