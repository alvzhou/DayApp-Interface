package model;

import org.json.JSONObject;

//Abstract Class for Food, Clothing, and Task
public abstract class Item {
    protected String name;

    //EFFECTS: Constructor of Item
    public Item(String name) {
        this.name = name;
    }

    //EFFECTS: Retrieves name from item
    public String getName() {
        return name;
    }

    public abstract JSONObject toJson();

}
