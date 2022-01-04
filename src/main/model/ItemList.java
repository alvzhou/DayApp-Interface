package model;

import java.util.ArrayList;
import java.util.List;

import model.clothes.Clothing;
import model.meals.Food;
import model.tasks.Task;
import org.json.JSONArray;

//Abstract Class for TaskList, Meals, and Clothing
public abstract class ItemList {
    List<Item> items;
    List<String> itemNames;
    String itemListName;

    public ItemList(String itemListName) {
        items = new ArrayList<>();
        itemNames = new ArrayList<>();
        this.itemListName = itemListName;

    }

    //MODIFIES: this
    //EFFECTS: adds item i to tasks, logs event
    public void addItem(Item i) {
        items.add(i);
        EventLog.getInstance().logEvent(new Event("Added " + i.getName() + "To" + itemListName + "!"));
    }


    //REQUIRES: items has at least one element
    //MODIFIES: this
    //EFFECTS: removes a item i to the current list of items
    public void removeItem(Item i) {
        items.remove(i);
    }

    //MODIFIES: this
    //EFFECTS: clears the list of items
    public void clearItemList() {
        items.clear();
        EventLog.getInstance().logEvent(new Event(itemListName + "Cleared!"));
    }

    //EFFECTS: returns a task based on the name given, else returns null
    public Item returnItemName(String nameToRemove) {
        for (Item next : items) {
            if (nameToRemove.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: returns the number of tasks remaining
    public int returnSizeOfItems() {
        return items.size();
    }


    //REQUIRES: non-empty list
    //EFFECTS: creates a list of strings, pertaining to the string names of each item in the list
    public List<String> itemToNames() {
        for (Item next : items) {
            itemNames.add(next.getName());
        }
        return itemNames;
    }

    //EFFECTS: returns a task based on the name given, else returns null
    public String getItemListName() {
        return itemListName;
    }

    //EFFECTS: returns things in ItemList as a JSON array
    protected JSONArray arrayToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : items) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }


    //EFFECTS: creates a new String Array List of Food including its fields from Meals
    public List<String> toStringLoadedMeals() {
        List<String> strings = new ArrayList<>();

        for (Item next : items) {
            String description = ((Food) next).getDescription();
            String text = "Meal Name: " + next.getName() + " Description: " + description;
            strings.add(text);
        }
        return strings;
    }

    //EFFECTS: creates a new String Array List of Task including its fields from Clothes
    public List<String> toStringTask() {
        List<String> strings = new ArrayList<>();

        for (Item next : items) {
            String text = "Task Name: " + next.getName() + " Description: " + ((Task) next).getDescription()
                    + " Deadline: " + ((Task) next).getDeadline() + " Priority: " + ((Task) next).getPriority();
            strings.add(text);
        }
        return strings;
    }

    //EFFECTS: creates a new String Array List of Clothing including its fields from Clothes
    public List<String> toStringClothing() {
        List<String> strings = new ArrayList<>();

        for (Item next : items) {
            String text = "Clothing Name: " + next.getName() + " Top: " + ((Clothing) next).getTop()
                    + " Bottom: " + ((Clothing) next).getBottom()
                    + " Accessories: " + ((Clothing) next).getAccessories() + " Footwear: "
                    + ((Clothing) next).getFootwear();
            strings.add(text);
        }
        return strings;
    }



}
