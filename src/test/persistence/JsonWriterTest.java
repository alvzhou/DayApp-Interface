package persistence;

import model.clothes.Clothes;
import model.clothes.Clothing;
import model.meals.Food;
import model.meals.Meals;
import model.tasks.Task;
import model.tasks.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//SOURCE: JsonSerializationDemo
public class JsonWriterTest {

    @Test
    void testWriterInvalidFileTaskList() {
        try {
            TaskList tl = new TaskList("my tasks");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileMeals() {
        try {
            Meals m = new Meals("my meals");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileOutfits() {
        try {
            Clothes c = new Clothes("my clothes");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMeals() {
        try {
            Meals m = new Meals("my meals");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.writeMeals(m);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            m = reader.readMeals();
            assertEquals("my meals", m.getItemListName());
            assertEquals(0, m.returnSizeOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyTasks() {
        try {
            TaskList tl = new TaskList("my tasks");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroomTaskList.json");
            writer.open();
            writer.writeTaskList(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroomTaskList.json");
            tl = reader.readTaskList();
            assertEquals("my tasks", tl.getItemListName());
            assertEquals(0, tl.returnSizeOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyClothes() {
        try {
            Clothes c = new Clothes("my clothes");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroomClothes.json");
            writer.open();
            writer.writeClothes(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroomClothes.json");
            c = reader.readClothes();
            assertEquals("my clothes", c.getItemListName());
            assertEquals(0, c.returnSizeOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMealWorkroom() {
        try {
            Meals m = new Meals("my meals");
            Food f = new Food("Apple");
            Food fTwo = new Food("Banana");
            f.setFoodDescription("Breakfast");
            m.addItem(f);
            fTwo.setFoodDescription("Lunch");
            m.addItem(fTwo);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealWorkroom.json");
            writer.open();
            writer.writeMeals(m);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealWorkroom.json");
            m = reader.readMeals();
            assertEquals("my meals", m.getItemListName());
            assertEquals(2, m.returnSizeOfItems());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTasksWorkroom() {
        try {
            TaskList tl = new TaskList("my tasks");
            Task taskOne = new Task("Homework");
            taskOne.setDescription("Math");
            try {
                taskOne.setDeadline("10/10/2021");
            } catch (ParseException e) {
                System.out.println("Try Again");;
            }
            taskOne.setPriority("High");
            tl.addItem(taskOne);
            Task taskTwo = new Task("Fishing");
            taskOne.setDescription("Steveston");
            try {
                taskOne.setDeadline("15/10/2021");
            } catch (ParseException e) {
                System.out.println("Try Again");;
            }
            taskOne.setPriority("Low");
            tl.addItem(taskTwo);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskListWorkroom.json");
            writer.open();
            writer.writeTaskList(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskListWorkroom.json");
            tl = reader.readTaskList();
            assertEquals("my tasks", tl.getItemListName());
            assertEquals(2, tl.returnSizeOfItems());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralClothesWorkroom() {
        try {
            Clothes c = new Clothes("my clothes");
            Clothing cOne = new Clothing("Outfit One");
            cOne.setTop("shirt");
            cOne.setBottom("jeans");
            cOne.setFootwear("shoes");
            cOne.setAccessories("watch");
            c.addItem(cOne);
            Clothing cTwo = new Clothing("Outfit Two");
            cOne.setTop("jacket");
            cOne.setBottom("shorts");
            cOne.setFootwear("slides");
            cOne.setAccessories("none");
            c.addItem(cTwo);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralClothesWorkroom.json");
            writer.open();
            writer.writeClothes(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralClothesWorkroom.json");
            c = reader.readClothes();
            assertEquals("my clothes", c.getItemListName());
            assertEquals(2, c.returnSizeOfItems());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
