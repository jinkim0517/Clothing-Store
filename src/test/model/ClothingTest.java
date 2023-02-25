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

        assertEquals("S", top.getSizes().get(0));
        assertEquals("M", top.getSizes().get(1));
        assertEquals("L", top.getSizes().get(2));
        assertEquals("XL", top.getSizes().get(3));

        for (int i = 28; i <= 33; i++) {
            assertEquals(Integer.toString(i), bottom.getSizes().get(i - 28));
        }

        assertEquals("S", outerwear.getSizes().get(0));
        assertEquals("M", outerwear.getSizes().get(1));
        assertEquals("L", outerwear.getSizes().get(2));
        assertEquals("XL", outerwear.getSizes().get(3));


        for (int i = 6; i <= 14; i++) {
            assertEquals(Integer.toString(i), footwear.getSizes().get(i - 6));
        }

        assertEquals(0, top.getSales());
        assertEquals(0, bottom.getSales());
        assertEquals(0, outerwear.getSales());
        assertEquals(0, footwear.getSales());

        assertTrue(top.isInStock());
        assertTrue(outerwear.isInStock());
        assertTrue(bottom.isInStock());
        assertTrue(footwear.isInStock());
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
    public void addSizesTest() {
        top.addSize("XXL");
        outerwear.addSize("XXL");
        bottom.addSize("34");
        footwear.addSize("15");

        assertEquals(5, top.getSizes().size());
        assertEquals("XXL", top.getSizes().get(4));

        assertEquals(5, outerwear.getSizes().size());
        assertEquals("XXL", outerwear.getSizes().get(4));

        assertEquals(7, bottom.getSizes().size());
        assertEquals("34", bottom.getSizes().get(6));

        assertEquals(10, footwear.getSizes().size());
        assertEquals("15", footwear.getSizes().get(9));
    }

    @Test
    public void removeAvailabilityTest() {
        top.removeSize("M");
        outerwear.removeSize("S");
        bottom.removeSize("31");
        footwear.removeSize("9");

        assertEquals(3, top.getSizes().size());
        assertFalse(top.getSizes().contains("M"));

        assertEquals(3, outerwear.getSizes().size());
        assertFalse(outerwear.getSizes().contains("S"));

        assertEquals(5, bottom.getSizes().size());
        assertFalse(bottom.getSizes().contains("31"));

        assertEquals(8, footwear.getSizes().size());
        assertFalse(footwear.getSizes().contains("9"));

        top.removeSize("S");
        top.removeSize("L");
        top.removeSize("XL");

        assertFalse(top.isInStock());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Top", top.getType());
        assertEquals("Outerwear", outerwear.getType());
        assertEquals("Bottom", bottom.getType());
        assertEquals("Footwear", footwear.getType());

    }
}