package model;

import model.meals.Food;
import model.meals.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsTest {
        private Meals mealsTest;

        @BeforeEach
        public void setup() {
            mealsTest = new Meals("Alvin's Meals");
        }

        @Test
        public void testConstructor() {
            assertEquals(0, mealsTest.returnSizeOfItems());
        }

        @Test
        public void testAddTasks() {
            Food foodOne = new Food("Apple");
            mealsTest.addItem(foodOne);
            assertEquals(1, mealsTest.returnSizeOfItems());
        }

        @Test
        public void testGetFoodName() {
            Food foodOne = new Food("Apple");
            assertEquals("Apple", foodOne.getName());
        }

        @Test
        public void testSetGetFoodDescription() {
            Food foodOne = new Food("Apple");
            foodOne.setFoodDescription("It's an apple from a counter");
            assertEquals("It's an apple from a counter", foodOne.getDescription());
        }



        @Test
        public void testAddTwoTasksAndCheckFieldsWithin() {
            Food foodOne = new Food("Apple");
            Food foodTwo = new Food("Banana");
            mealsTest.addItem(foodOne);
            mealsTest.addItem(foodTwo);
            assertEquals(2, mealsTest.returnSizeOfItems());
            foodOne.setFoodDescription("Banana From Backpack");
            assertEquals("Banana From Backpack", foodOne.getDescription());
        }

        @Test
        public void testRemoveTasks() {
            Food foodOne = new Food("Apple");
            mealsTest.removeItem(foodOne);
            assertEquals(0, mealsTest.returnSizeOfItems());
        }

        @Test
        public void testRemoveTwoTasks() {
            Food foodOne = new Food("Apple");
            Food foodTwo = new Food("Banana");
            mealsTest.addItem(foodOne);
            mealsTest.addItem(foodTwo);
            assertEquals(2, mealsTest.returnSizeOfItems());
            mealsTest.removeItem(foodOne);
            assertEquals(1, mealsTest.returnSizeOfItems());
            mealsTest.removeItem(foodTwo);
            assertEquals(0, mealsTest.returnSizeOfItems());
        }

        @Test
        public void testReturnStringName() {
            Food foodOne = new Food("Apple");
            Food foodTwo = new Food("Banana");
            mealsTest.addItem(foodOne);
            mealsTest.addItem(foodTwo);
            assertEquals(foodOne, mealsTest.returnItemName("Apple"));
            assertEquals(foodTwo, mealsTest.returnItemName("Banana"));
            assertEquals(null, mealsTest.returnItemName("Joker"));
        }

        @Test
        public void testClearMeals() {
            Food foodOne = new Food("Apple");
            Food foodTwo = new Food("Banana");
            mealsTest.addItem(foodOne);
            mealsTest.addItem(foodTwo);
            mealsTest.clearItemList();
            assertEquals(0, mealsTest.returnSizeOfItems());
        }



    }




