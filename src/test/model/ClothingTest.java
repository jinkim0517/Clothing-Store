package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTest {
    Clothing c1;
    Clothing c2;

    @BeforeEach
    public void setUp() {
        c1 = new Clothing("Polo T-Shirt", "Top", 19.99);
        c2 = new Clothing("Straight-Fit Jeans", "Bottom", 34.99);
    }

    @Test
    public void constructorTest() {
        assertEquals("Polo T-Shirt", c1.getName());
        assertEquals("Straight-Fit Jeans", c2.getName());

        assertEquals("Top", c1.getCategory());
        assertEquals("Bottom", c2.getCategory());

        assertEquals(19.99, c1.getPrice());
        assertEquals(34.99, c2.getPrice());

        assertTrue(c1.isAvailable());
        assertTrue(c2.isAvailable());

        assertEquals(0, c1.getSales());
        assertEquals(0, c2.getSales());
    }

    @Test
    public void getNameTest() {
        assertEquals("Polo T-Shirt", c1.getName());
        assertEquals("Straight-Fit Jeans", c2.getName());
    }

    @Test
    public void getCategoryTest() {
        assertEquals("Top", c1.getCategory());
        assertEquals("Bottom", c2.getCategory());
    }

    @Test
    public void getPriceTest() {
        assertEquals(19.99, c1.getPrice());
        assertEquals(34.99, c2.getPrice());
    }

    @Test
    public void isAvailableTest() {
        assertTrue(c1.isAvailable());
        assertTrue(c2.isAvailable());
    }

    @Test
    public void getSalesTest() {
        assertEquals(0, c1.getSales());
        assertEquals(0, c2.getSales());
    }

    @Test
    public void addSalesTest() {
        assertEquals(0, c1.getSales());
        assertEquals(0, c2.getSales());

        c1.addSale();
        c2.addSale();

        assertEquals(1, c1.getSales());
        assertEquals(1, c2.getSales());

        c1.addSale();
        c1.addSale();
        c2.addSale();
        c2.addSale();

        assertEquals(3, c1.getSales());
        assertEquals(3, c2.getSales());
    }

    @Test
    public void setNameTest() {
        assertEquals("Polo T-Shirt", c1.getName());
        assertEquals("Straight-Fit Jeans", c2.getName());

        c1.setName("Graphic T-Shirt");
        c2.setName("Skinny Jeans");

        assertEquals("Graphic T-Shirt", c1.getName());
        assertEquals("Skinny Jeans", c2.getName());
    }

    @Test
    public void setCategoryTest() {
        assertEquals("Top", c1.getCategory());
        assertEquals("Bottom", c2.getCategory());

        c1.setCategory("Footwear");
        c2.setCategory("Outerwear");

        assertEquals("Footwear", c1.getCategory());
        assertEquals("Outerwear", c2.getCategory());
    }
}
