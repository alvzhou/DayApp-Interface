package model.meals;

import model.Event;
import model.EventLog;
import model.Item;
import model.ItemList;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// SOURCES: A123-Review, Lab5-HelpDesk-Starter
public class Meals extends ItemList {
    private String nameOfMeals;


    //EFFECTS: constructs a new Array List for meals
    public Meals(String nameOfMeals) {
        super(nameOfMeals);
        this.nameOfMeals = nameOfMeals;
    }

    public JSONObject toJsonMeals() {
        JSONObject json = new JSONObject();
        json.put("n", nameOfMeals);
        json.put("meals", arrayToJson());
        return json;
    }


    //Refer to Specification in ItemList Class
    public void removeItem(Item i) {
        EventLog.getInstance().logEvent(new Event("Removed Meal To List"));
    }

}
