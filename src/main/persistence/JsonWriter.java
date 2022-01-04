package persistence;

import model.ItemList;
import model.clothes.Clothes;
import model.meals.Meals;
import model.tasks.TaskList;
import org.json.JSONObject;


import java.io.*;

//SOURCE: JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TaskList to file
    public void writeTaskList(TaskList t) {
        JSONObject json = t.toJsonTasks();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Meals to file
    public void writeMeals(Meals m) {
        JSONObject json = m.toJsonMeals();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Clothes to file
    public void writeClothes(Clothes c) {
        JSONObject json = c.toJsonClothes();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
