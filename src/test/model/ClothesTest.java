package model;

import model.clothes.Clothes;
import model.clothes.Clothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClothesTest {
    private Clothes clothesTest;

    @BeforeEach
    public void setup() {
        clothesTest = new Clothes("TestClothes");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, clothesTest.returnSizeOfItems());
    }

    @Test
    public void testAddOutfitAndGettersAndSetters() {
        Clothing outfitOne = new Clothing("Hockey Gear");
        clothesTest.addItem(outfitOne);
        assertEquals(1, clothesTest.returnSizeOfItems());
        outfitOne.setTop("Black Jacket");
        assertEquals("Black Jacket", outfitOne.getTop());
        outfitOne.setBottom("Black Pants");
        assertEquals("Black Pants", outfitOne.getBottom());
        outfitOne.setAccessories("Sunglasses");
        assertEquals("Sunglasses", outfitOne.getAccessories());
        outfitOne.setFootwear("Nike");
        assertEquals("Nike", outfitOne.getFootwear());
    }

    @Test
    public void testAddTwoTasksAndCheckFieldsWithin() {
        Clothing outfitOne = new Clothing("Workout Gear");
        Clothing outfitTwo = new Clothing("Hockey Gear");
        clothesTest.addItem(outfitOne);
        clothesTest.addItem(outfitTwo);
        assertEquals(2, clothesTest.returnSizeOfItems());
    }

    @Test
    public void testRemoveTasks() {
        Clothing outfitOne = new Clothing("Workout Gear");
        clothesTest.removeItem(outfitOne);
        assertEquals(0, clothesTest.returnSizeOfItems());
    }

    @Test
    public void testRemoveTwoTasks() {
        Clothing outfitOne = new Clothing("Workout Gear");
        Clothing outfitTwo = new Clothing("Hockey Gear");
        clothesTest.addItem(outfitOne);
        clothesTest.addItem(outfitTwo);
        assertEquals(2, clothesTest.returnSizeOfItems());
        clothesTest.removeItem(outfitOne);
        assertEquals(1, clothesTest.returnSizeOfItems());
        clothesTest.removeItem(outfitTwo);
        assertEquals(0, clothesTest.returnSizeOfItems());
    }

    @Test
    public void testReturnClothingBasedOnName() {
        Clothing outfitOne = new Clothing("Workout Gear");
        Clothing outfitTwo = new Clothing("Hockey Gear");
        clothesTest.addItem(outfitOne);
        clothesTest.addItem(outfitTwo);
        assertEquals(outfitOne, clothesTest.returnItemName("Workout Gear"));
        assertEquals(outfitTwo, clothesTest.returnItemName("Hockey Gear"));
        assertEquals(null, clothesTest.returnItemName("Null"));
    }

    @Test
    public void testClearClothing() {
        Clothing outfitOne = new Clothing("Workout Gear");
        Clothing outfitTwo = new Clothing("Hockey Gear");
        clothesTest.addItem(outfitOne);
        clothesTest.addItem(outfitTwo);
        clothesTest.clearItemList();
        assertEquals(0, clothesTest.returnSizeOfItems());
    }
}
