package model.tasks;

import model.Event;
import model.EventLog;
import model.Item;
import model.ItemList;
import org.json.JSONObject;

import java.util.List;

// SOURCES: A123-Review, Lab5-HelpDesk-Starter
public class TaskList extends ItemList {
    private String taskListName;

    //EFFECTS: initializes tasks (a Task List), and taskNameList (a List of Strings)
    public TaskList(String taskListName) {
        super(taskListName);
        this.taskListName = taskListName;
    }

    public JSONObject toJsonTasks() {
        JSONObject json = new JSONObject();
        json.put("t", taskListName);
        json.put("tasks", arrayToJson());
        return json;
    }

    //Refer to Specification in ItemList Class
    public void removeItem(Item i) {
        EventLog.getInstance().logEvent(new Event("Removed Task To List"));
    }



}
