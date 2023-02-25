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

        assertEquals(expectedTopAvails, top.getSizes());
        assertEquals(expectedTopAvails, outerwear.getSizes());
        assertEquals(expectedBotAvails, bottom.getSizes());
        assertEquals(expectedFootAvails, footwear.getSizes());

        assertEquals(0, top.getSales());
        assertEquals(0, bottom.getSales());
        assertEquals(0, outerwear.getSales());
        assertEquals(0, footwear.getSales());
    }

    @Test
    public void addSalesTest() {
        top.addSales(0);
        bottom.addSales(0);
        outerwear.addSales(0);
        footwear.addSales(0);

        assertEquals(0, top.getSales());
        assertEquals(0, bottom.getSales());
        assertEquals(0, outerwear.getSales());
        assertEquals(0, footwear.getSales());

        top.addSales(1);
        bottom.addSales(1);
        outerwear.addSales(1);
        footwear.addSales(1);

        assertEquals(1, top.getSales());
        assertEquals(1, bottom.getSales());
        assertEquals(1, outerwear.getSales());
        assertEquals(1, footwear.getSales());

        top.addSales(6);
        bottom.addSales(6);
        outerwear.addSales(6);
        footwear.addSales(6);

        assertEquals(7, top.getSales());
        assertEquals(7, bottom.getSales());
        assertEquals(7, outerwear.getSales());
        assertEquals(7, footwear.getSales());
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

        top.addSize("XXL");
        outerwear.addSize("XXL");
        bottom.addSize("34");
        footwear.addSize("15");

        assertEquals(expectedTopAvails, top.getSizes());
        assertEquals(expectedTopAvails, outerwear.getSizes());
        assertEquals(expectedBotAvails, bottom.getSizes());
        assertEquals(expectedFootAvails, footwear.getSizes());
    }

    @Test
    public void removeAvailabilityTest() {
        top.removeSize("M");
        bottom.removeSize("31");
        outerwear.removeSize("S");
        footwear.removeSize("9");

        assertEquals(new ArrayList<>(List.of("S", "L", "XL")), top.getSizes());
        assertEquals(new ArrayList<>(List.of("M", "L", "XL")), outerwear.getSizes());
        assertEquals(new ArrayList<>(List.of("28", "29", "30", "32", "33")), bottom.getSizes());
        assertEquals(new ArrayList<>(List.of("6", "7", "8", "10", "11", "12", "13", "14")),
                footwear.getSizes());

        top.removeSize("S");
        top.removeSize("L");
        top.removeSize("XL");

        assertFalse(top.isInStock());
    }
}