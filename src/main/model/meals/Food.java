package model.meals;

import model.Item;
import org.json.JSONObject;

public class Food extends Item {
    private String description;

    //EFFECTS: Initializes food with name and desc = ""
    public Food(String name) {
        super(name);
        description = "";
    }

    //EFFECTS: returns description
    public String getDescription() {
        return description;
    }


    //MODIFIES: this
    //EFFECTS: modifies food description
    public void setFoodDescription(String description) {
        this.description = description;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("desc", description);
        return json;
    }

}
