package persistence;

import model.Item;
import model.ItemList;
import model.clothes.Clothes;
import model.clothes.Clothing;
import model.meals.Food;
import model.meals.Meals;
import model.tasks.Task;
import model.tasks.TaskList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//SOURCE: JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructor
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads mealSave and returns it;
    // throws IOException if an error occurs reading data from file
    public Meals readMeals() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObjectForMeals = new JSONObject(jsonData);
        return parseMeals(jsonObjectForMeals);
    }

    // EFFECTS: parses meals from JSON object and returns it
    private Meals parseMeals(JSONObject jsonObjectForMeals) {
        String name = jsonObjectForMeals.getString("n");
        Meals m = new Meals(name);
        addMeals(m, jsonObjectForMeals);
        return m;
    }

    // MODIFIES: m
    // EFFECTS: parses meals from JSON object and adds them to MealList
    private void addMeals(Meals m, JSONObject jsonObjectForMeals) {
        JSONArray jsonArray = jsonObjectForMeals.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addFood(m, nextMeal);
        }
    }

    // MODIFIES: meals
    // EFFECTS: parses meal from JSON object and adds it to mealList
    private void addFood(Meals m, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String desc = jsonObject.getString("desc");
        Food food = new Food(name);
        food.setFoodDescription(desc);
        m.addItem(food);
    }


    // EFFECTS: reads TaskList and returns the jsonObject
    public TaskList readTaskList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObjectForTaskList = new JSONObject(jsonData);
        return parseTaskList(jsonObjectForTaskList);
    }

    // EFFECTS: parses TaskList from JSON object and returns it
    private TaskList parseTaskList(JSONObject jsonObjectForTaskList) {
        String name = jsonObjectForTaskList.getString("t");
        TaskList itOne = new TaskList(name);
        addTasks(itOne, jsonObjectForTaskList);
        return itOne;
    }

    // MODIFIES: itOne
    // EFFECTS: parses tasks from JSON object and adds them to TaskList
    private void addTasks(TaskList itOne, JSONObject jsonObjectForTaskList) {
        JSONArray jsonArray = jsonObjectForTaskList.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(itOne, nextTask);
        }
    }

    // MODIFIES: tasks
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTask(TaskList itOne, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String deadline = jsonObject.getString("deadline");
        String priority = jsonObject.getString("priority");
        String desc = jsonObject.getString("desc");
        Task task = new Task(name);
        while (true) {
            try {
                task.setDeadline(deadline);
                //problem is deadline is in the form sat feb 01, when it should be in the form 00/02/2002
                break;
            } catch (ParseException e) {
                System.out.println("Wrong Date Implemented. Please try again.");
            }
        }
        task.setPriority(priority);
        task.setDescription(desc);
        itOne.addItem(task);
    }

    // EFFECTS: reads Clothes and returns the jsonObject
    public Clothes readClothes() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseClothes(jsonObject);
    }

    // EFFECTS: parses Clothes from JSON object and returns it
    private Clothes parseClothes(JSONObject jsonObject) {
        String name = jsonObject.getString("o");
        Clothes c = new Clothes(name);
        addClothes(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses Clothes from JSON object and adds them to ClothingList
    private void addClothes(Clothes c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("outfits");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addClothing(c, nextClothing);
        }
    }

    // MODIFIES: ClothingList
    // EFFECTS: parses clothes from JSON object and adds it to ClothingList
    private void addClothing(Clothes c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String footwear = jsonObject.getString("footwear");
        String top = jsonObject.getString("top");
        String bottom = jsonObject.getString("bottom");
        String accessories = jsonObject.getString("accessories");
        Clothing clothing = new Clothing(name);
        clothing.setFootwear(footwear);
        clothing.setTop(top);
        clothing.setAccessories(accessories);
        clothing.setBottom(bottom);
        c.addItem(clothing);
    }




}





