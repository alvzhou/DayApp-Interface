package model;

import model.clothes.Clothing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClothingTest {

    @Test
    public void testConstructor() {
        Clothing clothing = new Clothing("my clothes");
        assertEquals("my clothes", clothing.getName());
        assertEquals("", clothing.getTop());
        assertEquals("", clothing.getBottom());
        assertEquals("", clothing.getFootwear());
        assertEquals("", clothing.getAccessories());
    }
}
