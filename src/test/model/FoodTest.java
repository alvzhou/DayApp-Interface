package model;

import model.meals.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {

    @Test
    public void testConstructor() {
        Food food = new Food("Apple");
        assertEquals("Apple", food.getName());
        assertEquals("", food.getDescription());
    }
}
