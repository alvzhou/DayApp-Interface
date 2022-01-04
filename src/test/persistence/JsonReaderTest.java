package persistence;

import model.clothes.Clothes;
import model.meals.Food;
import model.meals.Meals;
import model.tasks.Task;
import model.tasks.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//SOURCE: JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testJsonReaderNonExistentFileTaskList() {
        JsonReader taskListReader = new JsonReader("./data/fake.json");
        try {
            TaskList tl = taskListReader.readTaskList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testJsonReaderNonExistentFileMeals() {
        JsonReader taskListReader = new JsonReader("./data/fake.json");
        try {
            Meals m = taskListReader.readMeals();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testJsonReaderNonExistentFileClothes() {
        JsonReader taskListReader = new JsonReader("./data/fake.json");
        try {
            Clothes c = taskListReader.readClothes();
            fail("IOException expected");
        } catch (IOException c) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMeals() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMeals.json");
        try {
            Meals m = reader.readMeals();
            assertEquals(0, m.returnSizeOfItems());
            assertEquals("my meals", m.getItemListName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyTaskList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskList.json");
        try {
            TaskList tl = reader.readTaskList();
            assertEquals(0, tl.returnSizeOfItems());
            assertEquals("my tasks", tl.getItemListName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyClothes() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyClothes.json");
        try {
            Clothes c = reader.readClothes();
            assertEquals(0, c.returnSizeOfItems());
            assertEquals("my clothes", c.getItemListName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMeal() {
        JsonReader reader = new JsonReader("./data/testReaderMealsOfTwo.json");
        try {
            Meals m = reader.readMeals();
            assertEquals("my meals",  m.getItemListName());
            assertEquals(2, m.returnSizeOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTaskList() {
        JsonReader reader = new JsonReader("./data/testReaderTaskListOfTwo.json");
        try {
            TaskList tl = reader.readTaskList();
            assertEquals("my tasks", tl.getItemListName());
            assertEquals(2, tl.returnSizeOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClothes() {
        JsonReader reader = new JsonReader("./data/testReaderClothesListOfTwo.json");
        try {
            Clothes c = reader.readClothes();
            assertEquals("my clothes", c.getItemListName());
            assertEquals(2, c.returnSizeOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }








}
