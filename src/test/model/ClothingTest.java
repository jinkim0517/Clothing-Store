package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTest {
    Clothing top;
    Clothing bottom;
    Clothing outerwear;
    Clothing footwear;

    @BeforeEach
    public void setUp() {
        top = new Top("Polo T-Shirt", 19.99);
        bottom = new Bottom("Straight-Fit Jeans", 34.99);
        outerwear = new Outerwear("Denim Jacket", 79.99);
        footwear = new Footwear("Jordan 1 Bred Toe", 1500.00);
    }

    @Test
    public void constructorTest() {
        assertEquals("Polo T-Shirt", top.getName());
        assertEquals("Straight-Fit Jeans", bottom.getName());
        assertEquals("Denim Jacket", outerwear.getName());
        assertEquals("Jordan 1 Bred Toe", footwear.getName());

        assertEquals(19.99, top.getPrice());
        assertEquals(34.99, bottom.getPrice());
        assertEquals(79.99, outerwear.getPrice());
        assertEquals(1500.00, footwear.getPrice());

        ArrayList<String> expectedTopAvails = new ArrayList<>(List.of("S", "M", "L", "XL"));
        ArrayList<String> expectedBotAvails = new ArrayList<>(List.of("28", "29", "30", "31", "32", "33"));
        ArrayList<String> expectedFootAvails = new ArrayList<>(List.of("6", "7", "8", "9", "10",
                "11", "12", "13", "14"));

        assertEquals(expectedTopAvails, top.getAvailabilities());
        assertEquals(expectedTopAvails, outerwear.getAvailabilities());
        assertEquals(expectedBotAvails, bottom.getAvailabilities());
        assertEquals(expectedFootAvails, footwear.getAvailabilities());

        assertEquals(0, top.getSales());
        assertEquals(0, bottom.getSales());
        assertEquals(0, outerwear.getSales());
        assertEquals(0, footwear.getSales());
    }

    @Test
    public void addSalesTest() {
        assertEquals(0, top.getSales());
        assertEquals(0, bottom.getSales());
        assertEquals(0, outerwear.getSales());
        assertEquals(0, footwear.getSales());

        top.addSale();
        bottom.addSale();
        outerwear.addSale();
        footwear.addSale();

        assertEquals(1, top.getSales());
        assertEquals(1, bottom.getSales());
        assertEquals(1, outerwear.getSales());
        assertEquals(1, footwear.getSales());

        top.addSale();
        bottom.addSale();
        outerwear.addSale();
        footwear.addSale();

        top.addSale();
        bottom.addSale();
        outerwear.addSale();
        footwear.addSale();

        assertEquals(3, top.getSales());
        assertEquals(3, bottom.getSales());
        assertEquals(3, outerwear.getSales());
        assertEquals(3, footwear.getSales());
    }

    @Test
    public void setNameTest() {
        assertEquals("Polo T-Shirt", top.getName());
        assertEquals("Straight-Fit Jeans", bottom.getName());

        top.setName("Graphic T-Shirt");
        bottom.setName("Skinny Jeans");

        assertEquals("Graphic T-Shirt", top.getName());
        assertEquals("Skinny Jeans", bottom.getName());
    }

    @Test
    public void setPriceTest() {
        assertEquals(19.99, top.getPrice());
        assertEquals(34.99, bottom.getPrice());

        top.setPrice(24.99);
        bottom.setPrice(39.99);

        assertEquals(24.99, top.getPrice());
        assertEquals(39.99, bottom.getPrice());
    }

    @Test
    public void addAvailabilityTest() {
        ArrayList<String> expectedTopAvails = new ArrayList<>(List.of("S", "M", "L", "XL", "XXL"));
        ArrayList<String> expectedBotAvails = new ArrayList<>(List.of("28", "29", "30", "31", "32", "33", "34"));
        ArrayList<String> expectedFootAvails = new ArrayList<>(List.of("6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15"));

        top.addAvailability("XXL");
        outerwear.addAvailability("XXL");
        bottom.addAvailability("34");
        footwear.addAvailability("15");

        assertEquals(expectedTopAvails, top.getAvailabilities());
        assertEquals(expectedTopAvails, outerwear.getAvailabilities());
        assertEquals(expectedBotAvails, bottom.getAvailabilities());
        assertEquals(expectedFootAvails, footwear.getAvailabilities());
    }

    @Test
    public void removeAvailabilityTest() {
        top.removeAvailability("M");
        bottom.removeAvailability("31");
        outerwear.removeAvailability("S");
        footwear.removeAvailability("9");

        assertEquals(new ArrayList<>(List.of("S", "L", "XL")), top.getAvailabilities());
        assertEquals(new ArrayList<>(List.of("M", "L", "XL")), outerwear.getAvailabilities());
        assertEquals(new ArrayList<>(List.of("28", "29", "30", "32", "33")), bottom.getAvailabilities());
        assertEquals(new ArrayList<>(List.of("6", "7", "8", "10", "11", "12", "13", "14")),
                footwear.getAvailabilities());

        top.removeAvailability("S");
        top.removeAvailability("L");
        top.removeAvailability("XL");

        assertFalse(top.isInStock());
    }
}