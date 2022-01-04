package model.tasks;

import model.Item;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends Item {
    private String desc;
    private Date deadline;
    private String deadlineString;
    private String priority;

    // REQUIRES: date must be in the form "dd/MM/yyyy", Priority must be HIGH, MEDIUM, or LOW
    // EFFECTS: constructs initial task to be "", 0, and ""
    public Task(String name) {
        super(name);
        desc = "";
        deadline = null;
        priority = "";
    }
//
//    //EFFECTS: retrieves name of the Task
//    public String getName() {
//        return name;
//    }

    //MODIFIES: this
    //EFFECTS: modifies description
    public void setDescription(String desc) {
        this.desc = desc;
    }

    //EFFECTS: returns desc
    public String getDescription() {
        return desc;
    }

    //EFFECTS: Sets a Deadline for the task, inputted by String in the form dd/MM/yyyy"
    //SOURCE: https://www.javatpoint.com/java-string-to-date
    public void setDeadline(String deadline) throws ParseException {
        deadlineString = deadline;
        this.deadline = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);
    }

    //EFFECTS: returns deadline
    public Date getDeadline() {
        return deadline;
    }

    //MODIFIES: this
    //EFFECTS: modifies priority
    public void setPriority(String priority) {
        this.priority = priority;
    }

    //EFFECTS: returns priority
    public String getPriority() {
        return priority;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("desc", desc);
        json.put("deadline", deadlineString);
        json.put("priority", priority);
        return json;
    }

}
